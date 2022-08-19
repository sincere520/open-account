package dev.practice.open.application.customer;

import dev.practice.open.domain.notification.NotificationService;
import dev.practice.open.domain.customer.CustomerCommand;
import dev.practice.open.domain.customer.CustomerInfo;
import dev.practice.open.domain.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerFacade {
    private final CustomerService customerService;
    private final NotificationService notificationService;

    public CustomerInfo registerCustomer(CustomerCommand command) {
        var customerInfo = customerService.registerCustomer(command);
//        notificationService.sendEmail(customerInfo.getEmail(), "title", "description");
        return customerInfo;
    }
    public CustomerInfo.Main retrieveCustomerInfo(String customerToken) {
        System.out.println("testtttttttretrieveCustomerInfo");
        return customerService.retrieveCustomerInfo(customerToken);
    }
}
