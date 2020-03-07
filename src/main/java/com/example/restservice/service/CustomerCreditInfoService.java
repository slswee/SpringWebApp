package com.example.restservice.service;

import java.util.List;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.repository.CustomerCreditInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerCreditInfoService {
  @Autowired
  private CustomerCreditInfoRepository CustomerCreditInfoRepository;

  public CustomerCreditInfo findCustomerCreditInfo(long id) {
    return CustomerCreditInfoRepository.findById(id).map(customerCreditInfo -> customerCreditInfo).orElse(null);
  }
  @Transactional
  public void saveCustomerCreditInfo(List<CustomerCreditInfo> content) {
    CustomerCreditInfoRepository.saveAll(content);
  }
  // public Iterable<CustomerCreditInfo> saveCustomerCreditInfo(List<CustomerCreditInfo> content) {
  //   return CustomerCreditInfoRepository.saveAll(content);
  // }
}
