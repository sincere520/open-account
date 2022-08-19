package dev.practice.open.interfaces.customer;

import dev.practice.open.domain.customer.Customer;
import dev.practice.open.domain.customer.CustomerCommand;
import dev.practice.open.domain.customer.CustomerInfo;
import dev.practice.open.interfaces.open.OpenDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CustomerDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest {
        @NotEmpty(message = "customerName 는 필수값입니다")
        private String customerName;

        @NotEmpty(message = "phoneNumber 는 필수값입니다")
        private String phoneNumber;

        public CustomerCommand toCommand() {
            return CustomerCommand.builder()
                    .customerName(customerName)
                    .phoneNumber(phoneNumber)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterResponse {
        private final String customerToken;
        private final String customerName;
        private final String phoneNumber;
        private final Customer.Status status;

        public RegisterResponse(CustomerInfo customerInfo) {
            this.customerToken = customerInfo.getCustomerToken();
            this.customerName = customerInfo.getCustomerName();
            this.phoneNumber = customerInfo.getPhoneNumber();
            this.status = customerInfo.getStatus();
        }
    }

    // 조회
    @Getter
    @Builder
    @ToString
    public static class Main {
        private final String id;
        private final String customerToken;
        private final String customerName;
        private final String phoneNumber;
        private final String status;
    }
}
