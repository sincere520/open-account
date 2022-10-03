package dev.practice.order.domain.account.optiongroup;

import com.google.common.collect.Lists;
import dev.practice.order.domain.account.Account;
import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import dev.practice.order.domain.account.option.AccountOption;
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
@Table(name = "account_option_groups")
public class AccountOptionGroup extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private Integer ordering;
    private String accountOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountOptionGroup", cascade = CascadeType.PERSIST)
    private List<AccountOption> accountOptionList = Lists.newArrayList();

    @Builder
    public AccountOptionGroup(Account account, Integer ordering, String accountOptionGroupName) {
        if (account == null) throw new InvalidParamException("AccountOptionGroup.account");
        if (ordering == null) throw new InvalidParamException("AccountOptionGroup.ordering");
        if (StringUtils.isBlank(accountOptionGroupName))
            throw new InvalidParamException("AccountOptionGroup.accountOptionGroupName");

        this.account = account;
        this.ordering = ordering;
        this.accountOptionGroupName = accountOptionGroupName;
    }

    public AccountOptionGroup addAccountOption(AccountOption accountOption) {
        this.accountOptionList.add(accountOption);
        return this;
    }
}
