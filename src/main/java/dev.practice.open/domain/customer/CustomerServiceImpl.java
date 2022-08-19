package dev.practice.open.domain.customer;

import dev.practice.open.domain.customer.Customer;
import dev.practice.open.domain.customer.CustomerCommand;
import dev.practice.open.domain.customer.CustomerInfo;
import dev.practice.open.domain.customer.CustomerReader;
import dev.practice.open.domain.customer.CustomerService;
import dev.practice.open.domain.customer.CustomerStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerStore customerStore;
    private final CustomerReader customerReader;

    @Override
    @Transactional
    public dev.practice.open.domain.customer.CustomerInfo registerCustomer(CustomerCommand command) {
        var initCustomer = command.toEntity();
        dev.practice.open.domain.customer.Customer customer = customerStore.store(initCustomer);
        return new dev.practice.open.domain.customer.CustomerInfo(customer);
    }

    @Override
    public CustomerInfo retrieveCustomer(String customerToken) {
        var customer = customerReader.getCustomer(customerToken);
        return new CustomerInfo(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public dev.practice.open.domain.customer.CustomerInfo getCustomerInfo(String customerToken) {
        dev.practice.open.domain.customer.Customer customer = customerReader.getCustomer(customerToken);
        return new dev.practice.open.domain.customer.CustomerInfo(customer);
    }

    @Override
    @Transactional
    public dev.practice.open.domain.customer.CustomerInfo enableCustomer(String customerToken) {
        dev.practice.open.domain.customer.Customer customer = customerReader.getCustomer(customerToken);
        customer.enable();
        return new dev.practice.open.domain.customer.CustomerInfo(customer);
    }

    @Override
    @Transactional
    public dev.practice.open.domain.customer.CustomerInfo disableCustomer(String customerToken) {
        Customer customer = customerReader.getCustomer(customerToken);
        customer.disable();
        return new CustomerInfo(customer);
    }

    @Override
    public CustomerInfo.Main retrieveCustomerInfo(String customerToken) {
        var customer = customerReader.getCustomer(customerToken);
        return new CustomerInfo.Main(customer);
    }

}
