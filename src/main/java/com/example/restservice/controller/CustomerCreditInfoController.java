package com.example.restservice.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            Stream<String> lines = Files.lines(Paths.get(filename)).skip(1).limit(10);

              inputLines = lines.map(currentLine -> {
                try {
                  StringBuilder sb = new StringBuilder(currentLine);
                  String name = sb.substring(0, 72).trim();
                  int ssn = Integer.valueOf(sb.substring(72, 81));
                  List<Integer> tags = new ArrayList<>();
                  for (int i = 81; i < sb.length(); i += 9) {
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