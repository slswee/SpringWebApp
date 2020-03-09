package com.example.restservice.controller;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.service.CustomerCreditInfoService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerCreditInfoController {
  @Autowired
  private CustomerCreditInfoService customerCreditInfoService;

  @GetMapping("/customer")
  public CustomerCreditInfo CustomerCreditInfo(@RequestParam(value = "id", defaultValue = "1") String uuid) {
    return customerCreditInfoService.findCustomerCreditInfo(UUID.fromString(uuid));
  }

  @GetMapping("/tags")
  public List<Integer> CustomerTags(@RequestParam(value = "id", defaultValue = "1") String uuid) {
    CustomerCreditInfo customer = customerCreditInfoService.findCustomerCreditInfo(UUID.fromString(uuid));
    List<Integer> tags = customer.getTags();

    return tags;
  }

  @PostMapping("/customer")
  public void saveCustomersFromFile(@RequestParam(value = "filename", defaultValue = "test.dat") String filename) {

    List<CustomerCreditInfo> customers;
    try {
      customers = getCustomerFromFileAndSave(filename);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private List<CustomerCreditInfo> getCustomerFromFileAndSave(String filename) throws IOException {
    List<CustomerCreditInfo> customerList = new ArrayList<>();

    FileInputStream inputStream = null;
    Scanner sc = null;

      try {
        inputStream = new FileInputStream(filename);
        sc = new Scanner(inputStream, "UTF-8");
        // skip the first line which is the header
        sc.nextLine();
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            StringBuilder sb = new StringBuilder(currentLine);
            String name = sb.substring(0, 72).trim();
            int ssn = Integer.valueOf(sb.substring(72, 81));
            List<Integer> tags = new ArrayList<>();
            for (int i = 81; i < sb.length(); i += 9) {
              int tag = Integer.valueOf(sb.substring(i, i + 9).trim());
              // negative numbers are added to the tags 
              tags.add(tag);
            }

            customerList.add(new CustomerCreditInfo(name, ssn, tags));
            if (customerList.size() == 20) {
              customerCreditInfoService.saveCustomerCreditInfo(customerList);
              customerList.clear();
            }
        }
        if (customerList.size() > 0)
          customerCreditInfoService.saveCustomerCreditInfo(customerList);
        if (sc.ioException() != null) {
            throw sc.ioException();
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } finally {
        if (inputStream != null) {
            inputStream.close();
        }
        if (sc != null) {
            sc.close();
        }
    }
          return customerList;
      }
}