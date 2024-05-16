package com.bme.pricebckservice.domain.service;

import com.bme.pricebckservice.domain.model.Price;
import com.bme.pricebckservice.domain.model.dto.ContractDTO;
import com.bme.pricebckservice.feignclients.ContractFeignClient;
import com.bme.pricebckservice.repository.MongoDBRepository;
import com.bme.pricebckservice.repository.PostgresRepository;
import com.mongodb.client.MongoClient;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
public class PriceService {

    private final PostgresRepository postgresRepository;
    private final MongoDBRepository mongoDBRepository;
    private final MongoClient mongo;

    @Autowired
    ContractFeignClient contractFeignClient;

    @Autowired
    public PriceService(PostgresRepository postgresRepository, MongoDBRepository mongoDBRepository, MongoClient mongo) {
        this.postgresRepository = postgresRepository;
        this.mongoDBRepository = mongoDBRepository;
        this.mongo = mongo;
    }

    public List<Price> getPrices() {
        List<Price> prices = postgresRepository.findAll();
        if(prices.isEmpty()){
            return null;
        }else{
            return prices;
        }
    }

    public List<Price> getPricesMongo() {
        List<Price> prices = mongoDBRepository.findAll();
        if(prices.isEmpty()){
            return null;
        }else{
            return prices;
        }
    }

    public List<Price> getPricesByContracts(Long idContract) {
        List<Price> prices = new ArrayList<>();
        List<Price> allPrices = postgresRepository.findAll();
        if(allPrices.isEmpty()){
            return null;
        }else{
            for(Price p : allPrices){
                if(p.getIdContract().equals(idContract)){
                    prices.add(p);
                }
            }
            return prices;
        }
    }

    public List<Price> getPricesByContractsMongo(Long idContract) {
        List<Price> prices = new ArrayList<>();
        List<Price> allPrices = mongoDBRepository.findAll();
        if(allPrices.isEmpty()){
            return null;
        }else{
            for(Price p : allPrices){
                if(p.getIdContract().equals(idContract)){
                    prices.add(p);
                }
            }
            return prices;
        }
    }

    public int addPrice(Price price) {
        List<Price> prices = postgresRepository.findAll();
        int response = 0;
        if(prices.isEmpty()){
            postgresRepository.save(price);
            response = 200;
        }else{
            for(Price p : prices){
                if (p.getId().equals(price.getId())) {
                    response = 422;
                    break;
                }else{
                    postgresRepository.save(price);
                    response = 200;
                }
            }
        }
        return response;
    }

    public int addPriceMongo(Price price) {
        List<Price> prices = mongoDBRepository.findAll();
        int response = 0;
        if(prices.isEmpty()){
            mongoDBRepository.save(price);
            response = 200;
        }else{
            for(Price p : prices){
                if (p.getId().equals(price.getId())) {
                    response = 422;
                    break;
                }else{
                    mongoDBRepository.save(price);
                    response = 200;
                }
            }
        }
        return response;
    }

    public int deletePrice(Long id) {
        int response = 404;
        List<Price> prices = postgresRepository.findAll();
        if(prices.isEmpty()){
            response = 400;
        }else{
            for(Price p : prices){
                if(p.getId().equals(id)){
                    postgresRepository.delete(p);
                    response = 200;
                }
            }
        }
        return response;
    }

    public int deletePriceMongo(Long id) {
        int response = 404;
        List<Price> prices = mongoDBRepository.findAll();
        if(prices.isEmpty()){
            response = 400;
        }else{
            for(Price p : prices){
                if(p.getId().equals(id)){
                    mongoDBRepository.delete(p);
                    response = 200;
                }
            }
        }
        return response;
    }

    public int updatePrice(Price price) {
        int response = 404;
        Long id = price.getId();
        List<Price> prices = postgresRepository.findAll();
        if(prices.isEmpty()){
            response = 400;
        }else{
            for(Price p : prices){
                if(p.getId().equals(id)){
                    postgresRepository.save(price);
                    response = 200;
                }
            }
        }
        return response;
    }

    public int updatePriceMongo(Price price) {
        int response = 404;
        Long id = price.getId();
        List<Price> prices = mongoDBRepository.findAll();
        if(prices.isEmpty()){
            response = 400;
        }else{
            for(Price p : prices){
                if(p.getId().equals(id)){
                    mongoDBRepository.save(price);
                    response = 200;
                }
            }
        }
        return response;
    }

    @Async
    public CompletableFuture<Void> localCsv(MultipartFile file) throws IOException, CsvException {

        CompletableFuture<Void> future = new CompletableFuture<>();

        log.info("Thread : " + Thread.currentThread().getName() + "- Filename: " + file.getOriginalFilename());

        Path tempDir = Paths.get(System.getProperty("java.io.tmpdir"), "myapp-temp");
        Files.createDirectories(tempDir);

        Path tempFile = Files.createTempFile(tempDir,"temp", ".csv");
        file.transferTo(tempFile.toFile());

        try (BufferedReader br = Files.newBufferedReader(tempFile,
                StandardCharsets.UTF_8)) {

            HeaderColumnNameMappingStrategy<Price> strategy
                    = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Price.class);

            CsvToBean<Price> csvToBean = new CsvToBeanBuilder<Price>(br)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Price> prices = csvToBean.parse();

            postgresRepository.saveAll(prices);
            mongoDBRepository.saveAll(prices);

            Files.deleteIfExists(tempFile);
            log.info("El archivo {} se ha cargado correctamente.", file.getOriginalFilename());
            future.complete(null);
        }  catch (IOException e) {
            log.error("Error al cargar el archivo CSV {}: {}", file.getOriginalFilename(), e.getMessage());
            future.completeExceptionally(e);
        }
        return future;
    }

    public List<ContractDTO> getContracts(){
        List<ContractDTO> contracts = contractFeignClient.getContracts();
        return contracts;
    }

}
