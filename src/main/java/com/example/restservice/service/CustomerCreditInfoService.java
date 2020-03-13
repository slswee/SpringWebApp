package com.example.restservice.service;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.model.TagMetrics;
import com.example.restservice.repository.CustomerCreditInfoRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerCreditInfoService {
  @Autowired
  private CustomerCreditInfoRepository CustomerCreditInfoRepository;

  public CustomerCreditInfo findCustomerCreditInfo(final UUID uuid) {
    return CustomerCreditInfoRepository.findById(uuid).map(customerCreditInfo -> customerCreditInfo).orElse(null);
  }

  @Transactional
  public void saveCustomerCreditInfo(final List<CustomerCreditInfo> content) {
    CustomerCreditInfoRepository.saveAll(content);
  }

  public TagMetrics getTagMetrics(final String tag) {
    final int index = Integer.parseInt(tag.substring(1, 6));
    final List<Integer> tags = CustomerCreditInfoRepository.getTags(index);
    Collections.sort(tags);
    final int median = tags.get(tags.size() / 2);
    Integer sum = 0;
    for (Integer t : tags) {
      sum += t;
    }
    final double mean = sum.doubleValue() / tags.size();

    return new TagMetrics(median, mean, 0.0);
  }

}
