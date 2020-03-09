package com.example.restservice.controller;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.service.CustomerCreditInfoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
  
  @Captor
  ArgumentCaptor<List<CustomerCreditInfo>> savedCustomerInfo;

  @Test
  public void saveCustomer() {
    List<CustomerCreditInfo> testCustomers = new ArrayList<>();
    testCustomers.add(new CustomerCreditInfo("Norma Fisher", 266667962, Arrays.asList(369286, 80850)));
    testCustomers.add(new CustomerCreditInfo("Richard Turner", 489469559, Arrays.asList(-1, 992526)));
    testCustomers.add(new CustomerCreditInfo("Colleen Taylor", 144971554, Arrays.asList(-3, -1)));

    controller.saveCustomersFromFile("unittest.dat");
    verify(service, times(1)).saveCustomerCreditInfo(savedCustomerInfo.capture());

    for (int i = 0; i < 3; i++) {
      assertEquals(savedCustomerInfo.getValue().get(i).getName(), testCustomers.get(i).getName());
      assertEquals(savedCustomerInfo.getValue().get(i).getSSN(), testCustomers.get(i).getSSN());
      for (int x = 0; x < 2; x++) {
        assertEquals(savedCustomerInfo.getValue().get(i).getTags().get(x), testCustomers.get(i).getTags().get(x));
      }
    }
  }
}