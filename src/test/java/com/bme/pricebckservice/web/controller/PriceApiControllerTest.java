package com.bme.pricebckservice.web.controller;

import com.bme.pricebckservice.domain.model.Price;
import com.bme.pricebckservice.domain.service.PriceService;
import com.bme.pricebckservice.repository.PostgresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriceApiControllerTest {

    @InjectMocks
    private PriceApiController priceApiController;

    @Mock
    private PriceService priceService;

    private Price price;
    ResponseEntity<Price> responseEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        price = new Price(12345L, 9.44, 25L);
    }

    @Test
    void addPriceCreated() {
        ResponseEntity<Price> expected = new ResponseEntity<>(HttpStatus.CREATED);
        int number = 200;
        when(priceService.addPrice(price)).thenReturn(number);
        responseEntity = priceApiController.addPrice(price);
        assertEquals(expected, responseEntity);
    }

    @Test
    void addPriceUnprocessable() {
        ResponseEntity<Price> expected = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        int number = 422;
        when(priceService.addPrice(price)).thenReturn(number);
        responseEntity = priceApiController.addPrice(price);
        assertEquals(expected, responseEntity);
    }

    @Test
    void addPriceBadRequest() {
        ResponseEntity<Price> expected = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        int number = 400;
        when(priceService.addPrice(price)).thenReturn(number);
        responseEntity = priceApiController.addPrice(price);
        assertEquals(expected, responseEntity);
    }

    @Test
    void deletePriceOk() {
        ResponseEntity<Void> expected = new ResponseEntity<>(HttpStatus.OK);
        Long id = 12345L;
        int number = 200;
        when(priceService.deletePrice(id)).thenReturn(number);
        ResponseEntity<Void> responseEntityVoid = priceApiController.deletePrice(id);
        assertEquals(expected, responseEntityVoid);
    }

    @Test
    void deletePriceBadRequest() {
        ResponseEntity<Void> expected = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Long id = 12345L;
        int number = 400;
        when(priceService.deletePrice(id)).thenReturn(number);
        ResponseEntity<Void> responseEntityVoid = priceApiController.deletePrice(id);
        assertEquals(expected, responseEntityVoid);
    }

    @Test
    void deletePriceNotFound() {
        ResponseEntity<Void> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Long id = 12345L;
        int number = 404;
        when(priceService.deletePrice(id)).thenReturn(number);
        ResponseEntity<Void> responseEntityVoid = priceApiController.deletePrice(id);
        assertEquals(expected, responseEntityVoid);
    }

    @Test
    void deletePriceUnprocessable() {
        ResponseEntity<Void> expected = new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        Long id = 12345L;
        int number = 422;
        when(priceService.deletePrice(id)).thenReturn(number);
        ResponseEntity<Void> responseEntityVoid = priceApiController.deletePrice(id);
        assertEquals(expected, responseEntityVoid);
    }

    @Test
    void getPricesOk() {
        List<Price> prices = new ArrayList<Price>();
        ResponseEntity<List<Price>> expected = new ResponseEntity<>(prices, HttpStatus.OK);
        ResponseEntity<List<Price>> actual = priceApiController.getPrices();
        assertEquals(expected, actual);
    }

    @Test
    void getPricesEmpty() {
        ResponseEntity<List<Price>> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(priceService.getPricesMongo()).thenReturn(null);
        ResponseEntity<List<Price>> actual = priceApiController.getPrices();
        assertEquals(expected, actual);
    }

    @Test
    void getPricesByContractsNotFound() {
        ResponseEntity<List<Price>> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Long id = 12345L;
        ResponseEntity<List<Price>> actual = priceApiController.getPricesByContracts(id);
        assertEquals(expected,actual);
    }

    @Test
    void getPricesByContractsOk() {
        List<Price> prices = new ArrayList<Price>();
        ResponseEntity<List<Price>> expected = new ResponseEntity<>(prices, HttpStatus.OK);
        Long id = 12345L;
        prices.add(price);
        when(priceService.getPricesByContractsMongo(id)).thenReturn(prices);
        ResponseEntity<List<Price>> actual = priceApiController.getPricesByContracts(id);
        assertEquals(expected,actual);
    }

    @Test
    void getPricesByContractsNull() {
        ResponseEntity<List<Price>> expected = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Long id = 12345L;
        when(priceService.getPricesByContractsMongo(id)).thenReturn(null);
        ResponseEntity<List<Price>> actual = priceApiController.getPricesByContracts(id);
        assertEquals(expected,actual);
    }

    @Test
    void updatePriceNotFound() {
        ResponseEntity<Price> expected = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        int number = 404;
        when(priceService.updatePrice(price)).thenReturn(number);
        ResponseEntity<Price> actual = priceApiController.updatePrice(price);
        assertEquals(expected,actual);
    }

    @Test
    void updatePriceBadRequest() {
        ResponseEntity<Price> expected = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        int number = 400;
        when(priceService.updatePrice(price)).thenReturn(number);
        ResponseEntity<Price> actual = priceApiController.updatePrice(price);
        assertEquals(expected,actual);
    }

    @Test
    void updatePriceOk() {
        ResponseEntity<Price> expected = new ResponseEntity<>(HttpStatus.OK);
        int number = 200;
        when(priceService.updatePrice(price)).thenReturn(number);
        ResponseEntity<Price> actual = priceApiController.updatePrice(price);
        assertEquals(expected,actual);
    }
}