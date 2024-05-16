package com.bme.pricebckservice.web.controller;

import com.bme.pricebckservice.domain.model.Price;
import com.bme.pricebckservice.domain.model.dto.ContractDTO;
import com.bme.pricebckservice.domain.service.PriceService;
import com.opencsv.exceptions.CsvException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-24T17:05:00.970072+02:00[Europe/Paris]")

@RestController
@RequestMapping("${openapi.swaggerContractOpenAPI30.base-path:/api/v3}")
@Log4j2
public class PriceApiController implements PriceApi {

    private final PriceService priceService;

    @org.springframework.beans.factory.annotation.Autowired
    public PriceApiController(PriceService priceService) {
        this.priceService = priceService;
    }

    //@Override
    //public Optional<NativeWebRequest> getRequest() {
    //    return PriceApi.super.getRequest();
    //}

    @Override
    public ResponseEntity<Price> addPrice(Price price) {
        int response = 0;
        response = priceService.addPrice(price);
        if(response == 422) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (response == 200) {
            priceService.addPriceMongo(price);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deletePrice(Long id) {
        int response = 0;
        response = priceService.deletePrice(id);
        if(response == 400) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (response == 200) {
            priceService.deletePriceMongo(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else if(response == 404) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public ResponseEntity<List<Price>> getPrices() {
        List<Price> prices = priceService.getPricesMongo();
        if (prices == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(prices, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<Price>> getPricesByContracts(Long idContract) {
        List<Price> pricesByContract = priceService.getPricesByContractsMongo(idContract);
        if (pricesByContract == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if(pricesByContract.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(pricesByContract, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Price> updatePrice(Price price) {
        int response = 0;
        response = priceService.updatePrice(price);
        if(response == 404) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else if(response == 200) {
            priceService.updatePriceMongo(price);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Price> addPriceCSV(@RequestParam MultipartFile file) {
        try {
            CompletableFuture<Void> future = priceService.localCsv(file);
            future.thenRun(() -> {
                log.info("El archivo CSV se ha cargado correctamente.");
            }).exceptionally(ex -> {
                log.error("Error al cargar el archivo CSV: " + ex.getMessage());
                return null;
            });
            return ResponseEntity.ok().build();
        } catch (IOException | CsvException e) {
            log.error("Error al procesar el archivo CSV: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("contract/allContracts")
    public ResponseEntity<List<ContractDTO>> getContracts() {
        List<ContractDTO> contracts = priceService.getContracts();
        if (contracts == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (contracts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(contracts, HttpStatus.OK);
        }
    }
}
