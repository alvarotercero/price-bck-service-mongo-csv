package com.bme.pricebckservice;

import com.bme.pricebckservice.domain.model.Price;
import com.bme.pricebckservice.domain.service.PriceService;
import com.bme.pricebckservice.repository.PostgresRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PriceBckServiceApplicationTests {


    @Test
    public void test() {
    }

}
