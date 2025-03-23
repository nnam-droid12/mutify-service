package com.mutify.mutify.business.service;


import com.mutify.mutify.business.dto.BusinessDto;
import com.mutify.mutify.business.entities.Business;
import com.mutify.mutify.business.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {


    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public Business registerBusiness(BusinessDto businessDTO) {
        if (businessRepository.findByEmail(businessDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Business with this email already exists");
        }

        Business business = new Business();
        business.setBusinessName(businessDTO.getBusinessName());
        business.setEmail(businessDTO.getEmail());
        business.setBusinessType(businessDTO.getBusinessType());
        business.setCountry(businessDTO.getCountry());

        return businessRepository.save(business);
    }
}
