package com.example.restservice;

import java.io.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
		// readFile("/Users/sally/SallyProjects/spring-boot-rest-service-master/test.dat");
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
