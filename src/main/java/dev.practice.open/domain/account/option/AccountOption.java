package dev.practice.open.domain.account.option;


import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.domain.AbstractEntity;
import dev.practice.open.domain.account.optiongroup.AccountOptionGroup;
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
    private Integer opening;
    private String accountOptionName;
    private Long accountOptionPrice;

    @Builder
    public AccountOption(
            AccountOptionGroup accountOptionGroup,
            Integer opening,
            String accountOptionName,
            Long accountOptionPrice
    ) {
        if (accountOptionGroup == null) throw new InvalidParamException("AccountOption.accountOptionGroup");
        if (opening == null) throw new InvalidParamException("AccountOption.opening");
        if (StringUtils.isBlank(accountOptionName)) throw new InvalidParamException("AccountOption.accountOptionName");
        if (accountOptionPrice == null) throw new InvalidParamException("AccountOption.accountOptionPrice");

        this.accountOptionGroup = accountOptionGroup;
        this.opening = opening;
        this.accountOptionName = accountOptionName;
        this.accountOptionPrice = accountOptionPrice;
    }
}
