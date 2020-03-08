package com.example.restservice.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
  public void saveCustomersFromFile() {

    List<CustomerCreditInfo> customers;
    try {
      customers = readFile("test.dat");
      // customerCreditInfoService.saveCustomerCreditInfo(customers);

    } catch (IOException e) {
      e.printStackTrace();
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
            if (customerList.size() == 20) {
              customerCreditInfoService.saveCustomerCreditInfo(customerList);
              customerList.clear();
            }
        }
        if (customerList.size() > 0)
          customerCreditInfoService.saveCustomerCreditInfo(customerList);
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