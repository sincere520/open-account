package dev.practice.open.domain.customer;

import dev.practice.open.domain.customer.CustomerCommand;
import dev.practice.open.domain.customer.CustomerInfo;

public interface CustomerService {
    dev.practice.open.domain.customer.CustomerInfo registerCustomer(CustomerCommand command);
    dev.practice.open.domain.customer.CustomerInfo retrieveCustomer(String customerToken);
    dev.practice.open.domain.customer.CustomerInfo getCustomerInfo(String customerToken);
    dev.practice.open.domain.customer.CustomerInfo enableCustomer(String customerToken);
    CustomerInfo disableCustomer(String customerToken);

    CustomerInfo.Main retrieveCustomerInfo(String customerToken);
}
