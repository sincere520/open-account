package dev.practice.order.domain.customer;

public interface CustomerService {
    CustomerInfo registerCustomer(CustomerCommand command);
    CustomerInfo retrieveCustomer(String customerToken);
    CustomerInfo getCustomerInfo(String customerToken);
    CustomerInfo enableCustomer(String customerToken);
    CustomerInfo disableCustomer(String customerToken);

    CustomerInfo.Main retrieveCustomerInfo(String customerToken);
}
