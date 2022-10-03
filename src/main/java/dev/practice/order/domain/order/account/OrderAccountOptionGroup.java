package dev.practice.order.domain.order.account;

import com.google.common.collect.Lists;
import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "order_account_option_groups")
public class OrderAccountOptionGroup extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_account_id")
    private OrderAccount orderAccount;
    private Integer ordering;
    private String accountOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderAccountOptionGroup", cascade = CascadeType.PERSIST)
    private List<OrderAccountOption> orderAccountOptionList = Lists.newArrayList();

    @Builder
    public OrderAccountOptionGroup(
            OrderAccount orderAccount,
            Integer ordering,
            String accountOptionGroupName
    ) {
        if (orderAccount == null) throw new InvalidParamException();
        if (ordering == null) throw new InvalidParamException();
        if (StringUtils.isEmpty(accountOptionGroupName)) throw new InvalidParamException();
                
        this.orderAccount = orderAccount;
        this.ordering = ordering;
        this.accountOptionGroupName = accountOptionGroupName;
    }

    public Long calculateTotalAmount() {
        return orderAccountOptionList.stream()
                .mapToLong(OrderAccountOption::getAccountOptionAmt)
                .sum();
    }
}
