package dev.practice.open.domain.account.optiongroup;

import com.google.common.collect.Lists;
import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.domain.AbstractEntity;
import dev.practice.open.domain.account.Account;
import dev.practice.open.domain.account.option.AccountOption;
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
    private Integer opening;
    private String accountOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountOptionGroup", cascade = CascadeType.PERSIST)
    private List<AccountOption> accountOptionList = Lists.newArrayList();

    @Builder
    public AccountOptionGroup(Account account, Integer opening, String accountOptionGroupName) {
        if (account == null) throw new InvalidParamException("AccountOptionGroup.account");
        if (opening == null) throw new InvalidParamException("AccountOptionGroup.opening");
        if (StringUtils.isBlank(accountOptionGroupName))
            throw new InvalidParamException("AccountOptionGroup.accountOptionGroupName");

        this.account = account;
        this.opening = opening;
        this.accountOptionGroupName = accountOptionGroupName;
    }

    public AccountOptionGroup addAccountOption(AccountOption accountOption) {
        this.accountOptionList.add(accountOption);
        return this;
    }
}
