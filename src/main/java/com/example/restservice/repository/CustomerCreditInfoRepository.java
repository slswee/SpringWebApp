package com.example.restservice.repository;

import java.util.List;
import java.util.UUID;

import com.example.restservice.model.CustomerCreditInfo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCreditInfoRepository extends CrudRepository<CustomerCreditInfo, UUID> {

	CustomerCreditInfo save(List<CustomerCreditInfo> content);
}