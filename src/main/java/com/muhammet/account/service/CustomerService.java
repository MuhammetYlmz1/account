package com.muhammet.account.service;

import com.muhammet.account.dto.CustomerDto;
import com.muhammet.account.dto.CustomerDtoConverter;
import com.muhammet.account.exception.CustomerNotFoundException;
import com.muhammet.account.model.Customer;
import com.muhammet.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id)
                .orElseThrow(
                        ()->new CustomerNotFoundException("Böyle bir id bulunmamaktadır."+id));
    }

    public CustomerDto getCustomerById(String customerId) {

    return converter.convertToCustomerDto(findCustomerById(customerId));
    }
}
