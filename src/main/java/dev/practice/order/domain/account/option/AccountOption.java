package dev.practice.order.domain.account.option;


import dev.practice.order.domain.account.optiongroup.AccountOptionGroup;
import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "account_options")
public class AccountOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_option_group_id")
    private AccountOptionGroup accountOptionGroup;
    private Integer ordering;
    private String accountOptionName;
    private Long accountOptionAmt;

    @Builder
    public AccountOption(
            AccountOptionGroup accountOptionGroup,
            Integer ordering,
            String accountOptionName,
            Long accountOptionAmt
    ) {
        if (accountOptionGroup == null) throw new InvalidParamException("AccountOption.accountOptionGroup");
        if (ordering == null) throw new InvalidParamException("AccountOption.ordering");
        if (StringUtils.isBlank(accountOptionName)) throw new InvalidParamException("AccountOption.accountOptionName");
        if (accountOptionAmt == null) throw new InvalidParamException("AccountOption.accountOptionAmt");

        this.accountOptionGroup = accountOptionGroup;
        this.ordering = ordering;
        this.accountOptionName = accountOptionName;
        this.accountOptionAmt = accountOptionAmt;
    }
}
