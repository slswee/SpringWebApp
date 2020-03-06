package com.example.restservice.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.service.CustomerCreditInfoService;

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
  public CustomerCreditInfo CustomerCreditInfo(@RequestParam(value = "id", defaultValue = "1") String id) {
    return customerCreditInfoService.findCustomerCreditInfo(Long.parseLong(id));
  }

  // @PostMapping("/customer")
  // public CustomerCreditInfo saveCustomerCreditInfo() {
  //   return customerCreditInfoService.saveCustomerCreditInfo(new CustomerCreditInfo(
  //     "Honey Bunny",
  //     123456789,
  //     new int[]{1, 2, 3}
  //   ));
  // }


  @PostMapping("/customer")
  public void saveCustomersFromFile() {
    // return customerCreditInfoService.saveCustomerCreditInfo(new CustomerCreditInfo(
    //   "Honey Bunny",
    //   123456789, 
    //   Arrays.asList(123, 333, 444)
    // ));
    List<CustomerCreditInfo> customers = readFile("test.dat");
    for (CustomerCreditInfo customer: customers) {
      customerCreditInfoService.saveCustomerCreditInfo(
        customer
      );
    }

  }


    private List<CustomerCreditInfo> readFile(String filename) {
      List<CustomerCreditInfo> inputLines = new ArrayList<>();
      try {
              BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
              String line = bufferedReader.readLine();
              System.out.println(line);

              inputLines = IntStream.range(1, 10).mapToObj(i -> {
                try {
                  return bufferedReader.readLine();
                } catch (IOException ex) {
                  throw new RuntimeException(ex);
                }
              }).map(currentLine -> {
                try {
                  StringBuilder sb = new StringBuilder(currentLine);
                  String name = sb.substring(0, 72);
                  int ssn = Integer.valueOf(sb.substring(73, 82));
                  List<Integer> tags = new ArrayList<>();
                  for (int i = 83; i < sb.length(); i += 9) {
                    // we get rid of the space 
                    int tag = Integer.valueOf(sb.substring(i, i + 9).trim());
                    // we are adding the negative numbers to the tags
                    tags.add(tag);
                  }
                  return new CustomerCreditInfo(name, ssn, tags);
                } catch(Exception e) {
                  throw e;
                }
              })
              .collect(Collectors.toList());
              bufferedReader.close();
              // process the strings
          }
          catch (FileNotFoundException e) {
              System.err.println("input file not found");
          }
          catch (IOException e) {
              System.err.println("Error reading file");
          }
          return inputLines;
      }
}