package com.example.restservice.repository;

import com.example.restservice.model.CustomerCreditInfo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCreditInfoRepository extends CrudRepository<CustomerCreditInfo, Long> {
}