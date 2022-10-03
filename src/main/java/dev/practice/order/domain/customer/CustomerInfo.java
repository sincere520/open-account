package dev.practice.order.domain.customer;

import lombok.Getter;
import lombok.ToString;

@Getter
public class CustomerInfo {
    private final Long id;
    private final String customerToken;
    private final String customerName;
    private final String phoneNumber;
    private final Customer.Status status;

    public CustomerInfo(Customer customer) {
        this.id = customer.getId();
        this.customerToken = customer.getCustomerToken();
        this.customerName = customer.getCustomerName();
        this.phoneNumber = customer.getPhoneNumber();
        this.status = customer.getStatus();
    }


    @Getter
    @ToString
    public static class Main {
        private final Long id;
        private final String customerToken;
        private final String customerName;
        private final String phoneNumber;
        private final Customer.Status status;

        public Main(Customer customer) {
            this.id = customer.getId();
            this.customerToken = customer.getCustomerToken();
            this.customerName = customer.getCustomerName();
            this.phoneNumber = customer.getPhoneNumber();
            this.status = customer.getStatus();
        }
    }
}
