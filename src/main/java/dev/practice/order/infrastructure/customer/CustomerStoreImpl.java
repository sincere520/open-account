package dev.practice.order.infrastructure.customer;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.customer.Customer;
import dev.practice.order.domain.customer.CustomerStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerStoreImpl implements CustomerStore {

    private final CustomerRepository customerRepository;

    @Override
    public Customer store(Customer customer) {
        if (StringUtils.isEmpty(customer.getCustomerToken())) throw new InvalidParamException("customer.getCustomerToken()");
        if (StringUtils.isEmpty(customer.getCustomerName())) throw new InvalidParamException("customer.getCustomerName()");
        if (StringUtils.isEmpty(customer.getPhoneNumber())) throw new InvalidParamException("customer.getPhoneNumber()");
//        if (StringUtils.isEmpty(customer.getEmail())) throw new InvalidParamException("customer.getEmail()");
        if (customer.getStatus() == null) throw new InvalidParamException("customer.getStatus()");

        return customerRepository.save(customer);
    }
}
