package dev.practice.open.domain.account;

import com.google.common.collect.Lists;
import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.common.util.TokenGenerator;
import dev.practice.open.domain.AbstractEntity;
import dev.practice.open.domain.account.optiongroup.AccountOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "accounts")
public class Account extends AbstractEntity {
    private static final String ACCOUNT_PREFIX = "itm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountToken;
    private Long customerId;
    private String accountName;
    private Long accountPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.PERSIST)
    private List<AccountOptionGroup> accountOptionGroupList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("판매준비중"),
        ON_SALE("판매중"),
        END_OF_SALE("판매종료");

        private final String description;
    }

    @Builder
    public Account(Long customerId, String accountName, Long accountPrice) {
        if (customerId == null) throw new InvalidParamException("Account.customerId");
        if (StringUtils.isBlank(accountName)) throw new InvalidParamException("Account.accountName");
        if (accountPrice == null) throw new InvalidParamException("Account.accountPrice");

        this.customerId = customerId;
        this.accountToken = TokenGenerator.randomCharacterWithPrefix(ACCOUNT_PREFIX);
        this.accountName = accountName;
        this.accountPrice = accountPrice;
        this.status = Status.PREPARE;
    }

    public void changeOnSale() {
        this.status = Status.ON_SALE;
    }

    public void changeEndOfSale() {
        this.status = Status.END_OF_SALE;
    }

    public boolean availableSales() {
        return this.status == Status.ON_SALE;
    }
}
