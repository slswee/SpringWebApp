package com.example.restservice.controller;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.service.CustomerCreditInfoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class CustomerCreditInfoControllerTest {
    private CustomerCreditInfoController controller;

    @Mock
    private CustomerCreditInfoService service;

    @BeforeEach
    void init() {
        controller = new CustomerCreditInfoController();
    }
    @Test
 public void getCustomerCreditInfo() {
  CustomerCreditInfo mockCustomer = new CustomerCreditInfo();
  String testID = "b8866061-2380-435d-b506-d0ae4ff6cf31";
  UUID testUUID = UUID.fromString(testID);
  when(service.findCustomerCreditInfo(testUUID)).thenReturn(mockCustomer);
    CustomerCreditInfo response = controller.CustomerCreditInfo(testID);

    assertThat(response).isSameAs(mockCustomer);
    verify(service).findCustomerCreditInfo(testUUID);

    
  }
  

  

  

  

  

  
}