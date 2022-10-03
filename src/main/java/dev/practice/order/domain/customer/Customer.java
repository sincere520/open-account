package dev.practice.order.domain.customer;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.common.util.TokenGenerator;
import dev.practice.order.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "customer")
public class Customer extends AbstractEntity {
    private static final String PREFIX_CUSTOMER = "cus_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerToken;
    private String customerName;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"), DISABLE("비활성화");
        private final String description;
    }

    @Builder
    public Customer(String customerName, String phoneNumber) {
        if (StringUtils.isEmpty(customerName)) throw new InvalidParamException("empty customerName");
        if (StringUtils.isEmpty(phoneNumber)) throw new InvalidParamException("empty phoneNumber");

        this.id = id;
        this.customerToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_CUSTOMER);
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.status = Status.ENABLE;
    }

    public void enable() {
        this.status = Status.ENABLE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }
}
