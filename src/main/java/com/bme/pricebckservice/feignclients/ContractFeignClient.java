package com.bme.pricebckservice.feignclients;

import com.bme.pricebckservice.domain.model.dto.ContractDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="contract-bck-service", url="localhost:8082")
public interface ContractFeignClient {

    @GetMapping("/api/v3/contract/all")
    List<ContractDTO> getContracts();

}
