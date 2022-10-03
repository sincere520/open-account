package dev.practice.order.application.customer;

import dev.practice.order.domain.customer.CustomerInfo;
import dev.practice.order.domain.customer.CustomerService;
import dev.practice.order.domain.notification.NotificationService;
import dev.practice.order.domain.customer.CustomerCommand;
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
        log.info("===CustomerFacade.registerCustomer===");
        var customerInfo = customerService.registerCustomer(command);
//        notificationService.sendEmail(customerInfo.getEmail(), "title", "description");
        return customerInfo;
    }
    public CustomerInfo.Main retrieveCustomerInfo(String customerToken) {
        log.info("===CustomerFacade.retrieveCustomerInfo===");
        return customerService.retrieveCustomerInfo(customerToken);
    }
}
