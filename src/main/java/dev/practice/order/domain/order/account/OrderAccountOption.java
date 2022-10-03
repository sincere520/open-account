package dev.practice.order.domain.order.account;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "order_account_options")
public class OrderAccountOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_account_option_group_id")
    private OrderAccountOptionGroup orderAccountOptionGroup;
    private Integer ordering;
    private String accountOptionName;
    private Long accountOptionAmt;

    @Builder
    public OrderAccountOption(
            OrderAccountOptionGroup orderAccountOptionGroup,
            Integer ordering,
            String accountOptionName,
            Long accountOptionAmt) {
        if (orderAccountOptionGroup == null) throw new InvalidParamException();
        if (ordering == null) throw new InvalidParamException();
        if (StringUtils.isEmpty(accountOptionName)) throw new InvalidParamException();
        if (accountOptionAmt == null) throw new InvalidParamException();

        this.orderAccountOptionGroup = orderAccountOptionGroup;
        this.ordering = ordering;
        this.accountOptionName = accountOptionName;
        this.accountOptionAmt = accountOptionAmt;
    }
}
