package com.example.restservice.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.restservice.model.CustomerCreditInfo;
import com.example.restservice.service.CustomerCreditInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
  // return customerCreditInfoService.saveCustomerCreditInfo(new
  // CustomerCreditInfo(
  // "Honey Bunny",
  // 123456789,
  // new int[]{1, 2, 3}
  // ));
  // }

  @PostMapping("/customer")
  public void saveCustomersFromFile() {

    List<CustomerCreditInfo> customers;
    try {
      customers = readFile("test.dat");
      saveCustomers(customers);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Transactional
  private void saveCustomers(List<CustomerCreditInfo> customers) {
    int size = customers.size();
    int counter = 0;
    List<CustomerCreditInfo> temp = new ArrayList<>();
    for (CustomerCreditInfo customer: customers) {
      temp.add(customer);
      if ((counter + 1) % 20 == 0 || (counter + 1) == size) {
        customerCreditInfoService.saveCustomerCreditInfo(temp);
        temp.clear();
      }
      counter++;
    }
  }

  private List<CustomerCreditInfo> readFile(String filename) throws IOException {
    List<CustomerCreditInfo> customerList = new ArrayList<>();

    FileInputStream inputStream = null;
    Scanner sc = null;

      try {
        inputStream = new FileInputStream(filename);
        sc = new Scanner(inputStream, "UTF-8");
        // skip the first line which is the header
        sc.nextLine();
        
        int x = 0; 
        while (sc.hasNextLine() && x++ < 103) {
            String currentLine = sc.nextLine();
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

            customerList.add(new CustomerCreditInfo(name, ssn, tags));
        }
        // note that Scanner suppresses exceptions
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


      // try {
      //       Stream<String> lines = Files.lines(Paths.get(filename)).skip(1).limit(10);

      //         customerList = lines.map(currentLine -> {
      //           try {
      //             StringBuilder sb = new StringBuilder(currentLine);
      //             String name = sb.substring(0, 72).trim();
      //             int ssn = Integer.valueOf(sb.substring(72, 81));
      //             List<Integer> tags = new ArrayList<>();
      //             for (int i = 81; i < sb.length(); i += 9) {
      //               // we get rid of the space 
      //               int tag = Integer.valueOf(sb.substring(i, i + 9).trim());
      //               // we are adding the negative numbers to the tags
      //               tags.add(tag);
      //             }
      //             return new CustomerCreditInfo(name, ssn, tags);
      //           } catch(Exception e) {
      //             throw e;
      //           }
      //         })
      //         .collect(Collectors.toList());
      //     }
      //     catch (FileNotFoundException e) {
      //         System.err.println("input file not found");
      //     }
      //     catch (IOException e) {
      //         System.err.println("Error reading file");
      //     }
          return customerList;
      }
}