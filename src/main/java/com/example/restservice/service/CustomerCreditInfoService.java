package com.example.restservice.service;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.repository.CustomerCreditInfoRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CustomerCreditInfoService {
  @Autowired
  private CustomerCreditInfoRepository CustomerCreditInfoRepository;

  public CustomerCreditInfo findCustomerCreditInfo(UUID uuid) {
    return CustomerCreditInfoRepository.findById(uuid).map(customerCreditInfo -> customerCreditInfo).orElse(null);
  }
  @Transactional
  public void saveCustomerCreditInfo(List<CustomerCreditInfo> content) {
    CustomerCreditInfoRepository.saveAll(content);
  }

}
