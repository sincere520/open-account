package dev.practice.order.domain.account;

import com.google.common.collect.Lists;
import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.common.util.TokenGenerator;
import dev.practice.order.domain.AbstractEntity;
import dev.practice.order.domain.account.optiongroup.AccountOptionGroup;
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
    private static final String ACCOUNT_PREFIX = "acc_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountToken;
    private Long customerId;
    private String accountName;
    private Long accountAmt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.PERSIST)
    private List<AccountOptionGroup> accountOptionGroupList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("대기"),
        AVAILABLE("활성화"),
        NOT_AVAILABLE("비활성화");

        private final String description;
    }

    @Builder
    public Account(Long customerId, String accountName, Long accountAmt) {
        if (customerId == null) throw new InvalidParamException("Account.customerId");
        if (StringUtils.isBlank(accountName)) throw new InvalidParamException("Account.accountName");
        if (accountAmt == null) throw new InvalidParamException("Account.accountAmt");

        this.customerId = customerId;
        this.accountToken = TokenGenerator.randomCharacterWithPrefix(ACCOUNT_PREFIX);
        this.accountName = accountName;
        this.accountAmt = accountAmt;
        this.status = Status.PREPARE;
    }

    public void changeOnSale() {
        this.status = Status.AVAILABLE;
    }
    public void changeAmount(long amount) {
        this.accountAmt = amount;
    }

    public void changeEndOfSale() {
        this.status = Status.NOT_AVAILABLE;
    }

    public boolean availableSales() {
        return this.status == Status.AVAILABLE;
    }
}
