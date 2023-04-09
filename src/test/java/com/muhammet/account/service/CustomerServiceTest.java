package com.muhammet.account.service;

import com.muhammet.account.dto.CustomerDto;
import com.muhammet.account.dto.CustomerDtoConverter;
import com.muhammet.account.exception.CustomerNotFoundException;
import com.muhammet.account.model.Customer;
import com.muhammet.account.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.mock;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter converter;


    @BeforeEach
    public void setUp(){
        customerRepository= mock(CustomerRepository.class);
        converter= mock(CustomerDtoConverter.class);
        customerService=new CustomerService(customerRepository,converter);
    }

    @Test
    public void testFindByCustomerId_whenCustomerIdExists_shouldResturnCustomer(){
        Customer customer=new Customer("id","name","surname", Set.of());


        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));

        Customer result=customerService.findCustomerById("id");
        Assert.assertEquals(result,customer);

    }

    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException(){

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        Assert.assertThrows(CustomerNotFoundException.class,()-> customerService.findCustomerById("id"));

    }

    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldResturnCustomer(){
        Customer customer=new Customer("id","name","surname", Set.of());
        CustomerDto customerDto=new CustomerDto("id","name","surname",Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result=customerService.getCustomerById("id");
        Assert.assertEquals(result,customerDto);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExists_shouldThrowCustomerNotFoundException(){
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        Assert.assertThrows(CustomerNotFoundException.class,()->customerService.getCustomerById("id"));

        //converter ın hiçbir metodu çağırılmasın
        Mockito.verifyNoInteractions(converter);

    }



}