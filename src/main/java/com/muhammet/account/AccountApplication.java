package com.muhammet.account;

import com.muhammet.account.model.Customer;
import com.muhammet.account.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {
	private static CustomerRepository customerRepository;

	public AccountApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;

	}

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}

	@Bean

	@Override
	public void run(String... args) throws Exception {
		Customer customer=customerRepository.save(new Customer("","Muhammet","Yılmaz",new HashSet<>()));
		System.out.println(customer.getId());
	}
}
