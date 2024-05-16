package com.bme.pricebckservice.domain.service;

import com.bme.pricebckservice.domain.model.Price;
import com.bme.pricebckservice.repository.MongoDBRepository;
import com.bme.pricebckservice.repository.PostgresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceServiceTest {

    @Mock
    private PostgresRepository postgresRepository;

    @Mock
    private MongoDBRepository mongoDBRepository;

    @InjectMocks
    private PriceService service;

    private Price price;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        price = new Price(12345L, 9.44, 25L);
    }

    @Test
    void getPricesEmpty() {
        assertNull(service.getPrices());
    }

    @Test
    void getPricesNotEmpty() {
        when(postgresRepository.findAll()).thenReturn(Arrays.asList(price));
        assertNotNull(service.getPrices());
    }

    @Test
    void getPricesByContractsEmptyList() {
        Long idContract = 1L;
        assertNull(service.getPricesByContracts(idContract));
    }

    @Test
    void getPricesByContractsNotEmptyListNoContracts() {
        when(postgresRepository.findAll()).thenReturn(Arrays.asList(price));
        Long idContract = 1L;
        assertTrue(service.getPricesByContracts(idContract).isEmpty());
    }

    @Test
    void getPricesByContractsNotEmptyListWithContracts() {
        when(postgresRepository.findAll()).thenReturn(Arrays.asList(price));
        Long idContract = 25L;
        assertEquals(Arrays.asList(price), service.getPricesByContracts(idContract));
    }

    @Test
    void addPriceEmptyList() {
        int expected = 200;
        int actual = service.addPrice(price);
        assertEquals(expected, actual);
    }

    @Test
    void addPriceNotEmptyListExisting() {
        int expected = 422;
        when(postgresRepository.findAll()).thenReturn(Arrays.asList(price));
        int actual = service.addPrice(price);
        assertEquals(expected, actual);
    }

    @Test
    void addPriceNotEmptyListNotExisting() {
        int expected = 200;
        when(postgresRepository.findAll()).thenReturn(Arrays.asList(price));
        Price price2 = new Price(6789L, 3.44, 20L);
        int actual = service.addPrice(price2);
        assertEquals(expected, actual);
    }

    @Test
    void deletePriceEmptyList() {
        int expected = 400;
        Long idContract = 1L;
        int actual = service.deletePrice(idContract);
        assertEquals(expected, actual);
    }

    @Test
    void deletePriceNotFound() {
        int expected = 404;
        Long idContract = 1L;
        when(postgresRepository.findAll()).thenReturn(Arrays.asList(price));
        int actual = service.deletePrice(idContract);
        assertEquals(expected, actual);
    }

    @Test
    void deletePriceFound() {
        int expected = 200;
        Long idContract = 12345L;
        when(postgresRepository.findAll()).thenReturn(Arrays.asList(price));
        int actual = service.deletePrice(idContract);
        assertEquals(expected, actual);
    }

    @Test
    void updatePriceEmptyList() {
        int expected = 400;
        int actual = service.updatePrice(price);
        assertEquals(expected, actual);
    }

    @Test
    void updatePriceNotFound() {
        int expected = 404;
        when(postgresRepository.findAll()).thenReturn(Arrays.asList(price));
        Price price2 = new Price(6789L, 3.44, 20L);
        int actual = service.updatePrice(price2);
        assertEquals(expected, actual);
    }

    @Test
    void updatePriceFound() {
        int expected = 200;
        when(postgresRepository.findAll()).thenReturn(Arrays.asList(price));
        int actual = service.updatePrice(price);
        assertEquals(expected, actual);
    }

    @Test
    void getPricesEmptyMongo() {
        assertNull(service.getPricesMongo());
    }

    @Test
    void getPricesNotEmptyMongo() {
        when(mongoDBRepository.findAll()).thenReturn(Arrays.asList(price));
        assertNotNull(service.getPricesMongo());
    }

    @Test
    void getPricesByContractsEmptyListMongo() {
        Long idContract = 1L;
        assertNull(service.getPricesByContractsMongo(idContract));
    }

    @Test
    void getPricesByContractsNotEmptyListNoContractsMongo() {
        when(mongoDBRepository.findAll()).thenReturn(Arrays.asList(price));
        Long idContract = 1L;
        assertTrue(service.getPricesByContractsMongo(idContract).isEmpty());
    }

    @Test
    void getPricesByContractsNotEmptyListWithContractsMongo() {
        when(mongoDBRepository.findAll()).thenReturn(Arrays.asList(price));
        Long idContract = 25L;
        assertEquals(Arrays.asList(price), service.getPricesByContractsMongo(idContract));
    }

    @Test
    void addPriceEmptyListMongo() {
        int expected = 200;
        int actual = service.addPriceMongo(price);
        assertEquals(expected, actual);
    }

    @Test
    void addPriceNotEmptyListExistingMongo() {
        int expected = 422;
        when(mongoDBRepository.findAll()).thenReturn(Arrays.asList(price));
        int actual = service.addPriceMongo(price);
        assertEquals(expected, actual);
    }

    @Test
    void addPriceNotEmptyListNotExistingMongo() {
        int expected = 200;
        when(mongoDBRepository.findAll()).thenReturn(Arrays.asList(price));
        Price price2 = new Price(6789L, 3.44, 20L);
        int actual = service.addPriceMongo(price2);
        assertEquals(expected, actual);
    }

    @Test
    void deletePriceEmptyListMongo() {
        int expected = 400;
        Long idContract = 1L;
        int actual = service.deletePriceMongo(idContract);
        assertEquals(expected, actual);
    }

    @Test
    void deletePriceNotFoundMongo() {
        int expected = 404;
        Long idContract = 1L;
        when(mongoDBRepository.findAll()).thenReturn(Arrays.asList(price));
        int actual = service.deletePriceMongo(idContract);
        assertEquals(expected, actual);
    }

    @Test
    void deletePriceFoundMongo() {
        int expected = 200;
        Long idContract = 12345L;
        when(mongoDBRepository.findAll()).thenReturn(Arrays.asList(price));
        int actual = service.deletePriceMongo(idContract);
        assertEquals(expected, actual);
    }

    @Test
    void updatePriceEmptyListMongo() {
        int expected = 400;
        int actual = service.updatePriceMongo(price);
        assertEquals(expected, actual);
    }

    @Test
    void updatePriceNotFoundMongo() {
        int expected = 404;
        when(mongoDBRepository.findAll()).thenReturn(Arrays.asList(price));
        Price price2 = new Price(6789L, 3.44, 20L);
        int actual = service.updatePriceMongo(price2);
        assertEquals(expected, actual);
    }

    @Test
    void updatePriceFoundMongo() {
        int expected = 200;
        when(mongoDBRepository.findAll()).thenReturn(Arrays.asList(price));
        int actual = service.updatePriceMongo(price);
        assertEquals(expected, actual);
    }

}