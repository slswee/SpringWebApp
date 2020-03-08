package com.example.restservice.controller;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.service.CustomerCreditInfoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CustomerCreditInfoControllerTest {
    @InjectMocks
    private CustomerCreditInfoController controller;
    @Mock
    private CustomerCreditInfoService service;

    private final String testID = "b8866061-2380-435d-b506-d0ae4ff6cf31";

    @Test
 public void getCustomerCreditInfo() {
  CustomerCreditInfo mockCustomer = new CustomerCreditInfo();

  UUID testUUID = UUID.fromString(testID);
  when(service.findCustomerCreditInfo(any())).thenReturn(mockCustomer);
    CustomerCreditInfo response = controller.CustomerCreditInfo(testID);

    assertThat(response).isSameAs(mockCustomer);
    verify(service).findCustomerCreditInfo(testUUID);
    
  }

  @Test
  public void getTags() {
    List<Integer> tags = Arrays.asList(583047,460668,518620,907327,-1,28253);
    CustomerCreditInfo mockCustomer = new CustomerCreditInfo("coolName", 1234, tags);
    when(service.findCustomerCreditInfo(any())).thenReturn(mockCustomer);
    List<Integer> response = controller.CustomerTags(testID);

    assertThat(response).isSameAs(tags);
    
  }
  

  

  

  

  

  
}