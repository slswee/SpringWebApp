package com.example.restservice.controller;
import java.io.*;
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

  @PostMapping("/customer")
  public CustomerCreditInfo saveCustomerCreditInfo() {
    return customerCreditInfoService.saveCustomerCreditInfo(new CustomerCreditInfo(
      "Honey Bunny",
      123456789,
      new int[]{1, 2, 3}
    ));
  }


    public static void readFile(String filename) {
		try {
            BufferedReader inputStream = new BufferedReader(new FileReader(filename));
            String line = inputStream.readLine();
            System.out.println(line);
            inputStream.close();

        }
        catch (FileNotFoundException e) {
             System.err.println("input file not found");
         }
         catch (IOException e) {
             System.err.println("Error reading file");
         }

    }
}