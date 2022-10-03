package dev.practice.order.domain.customer;

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
    public CustomerInfo registerCustomer(CustomerCommand command) {
        var initCustomer = command.toEntity();
        Customer customer = customerStore.store(initCustomer);
        return new CustomerInfo(customer);
    }

    @Override
    public CustomerInfo retrieveCustomer(String customerToken) {
        var customer = customerReader.getCustomer(customerToken);
        return new CustomerInfo(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerInfo getCustomerInfo(String customerToken) {
        Customer customer = customerReader.getCustomer(customerToken);
        return new CustomerInfo(customer);
    }

    @Override
    @Transactional
    public CustomerInfo enableCustomer(String customerToken) {
        Customer customer = customerReader.getCustomer(customerToken);
        customer.enable();
        return new CustomerInfo(customer);
    }

    @Override
    @Transactional
    public CustomerInfo disableCustomer(String customerToken) {
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
