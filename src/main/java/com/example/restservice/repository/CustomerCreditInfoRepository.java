package com.example.restservice.repository;

import java.util.List;
import java.util.UUID;

import com.example.restservice.model.CustomerCreditInfo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCreditInfoRepository extends CrudRepository<CustomerCreditInfo, UUID> {

	void save(List<CustomerCreditInfo> content);

	@Query(value = "SELECT tags[?1] FROM customer_credit WHERE tags[?1] > 0", nativeQuery = true)
	List<Integer> getTags(int index);
}