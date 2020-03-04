package com.example.restservice.service;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.repository.CustomerCreditInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCreditInfoService {
  @Autowired
  private CustomerCreditInfoRepository CustomerCreditInfoRepository;

  public CustomerCreditInfo findCustomerCreditInfo(long id) {
    return CustomerCreditInfoRepository.findById(id).map(customerCreditInfo -> customerCreditInfo).orElse(null);
  }

  public CustomerCreditInfo saveCustomerCreditInfo(CustomerCreditInfo content) {
    return CustomerCreditInfoRepository.save(content);
  }
}
