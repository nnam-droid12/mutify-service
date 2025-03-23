package com.mutify.mutify.business.controller;


import com.mutify.mutify.business.dto.BusinessDto;
import com.mutify.mutify.business.entities.Business;
import com.mutify.mutify.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/register")
    public ResponseEntity<Business> registerBusiness(@RequestBody BusinessDto businessDTO) {
        Business business = businessService.registerBusiness(businessDTO);
        return ResponseEntity.ok(business);
    }
}
