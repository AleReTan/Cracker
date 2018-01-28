package com.netcracker.demo;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class DemotaskApplication {

	public static void main(String[] args)


	{

		SpringApplication.run(DemotaskApplication.class, args);
	}
}
