package dev.practice.open.domain.open.account;

import com.google.common.collect.Lists;
import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.domain.AbstractEntity;
import dev.practice.open.domain.open.account.OpenAccount;
import dev.practice.open.domain.open.account.OpenAccountOption;
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
@Table(name = "open_account_option_groups")
public class OpenAccountOptionGroup extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "open_account_id")
    private dev.practice.open.domain.open.account.OpenAccount openAccount;
    private Integer opening;
    private String accountOptionGroupName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "openAccountOptionGroup", cascade = CascadeType.PERSIST)
    private List<dev.practice.open.domain.open.account.OpenAccountOption> openAccountOptionList = Lists.newArrayList();

    @Builder
    public OpenAccountOptionGroup(
            OpenAccount openAccount,
            Integer opening,
            String accountOptionGroupName
    ) {
        if (openAccount == null) throw new InvalidParamException();
        if (opening == null) throw new InvalidParamException();
        if (StringUtils.isEmpty(accountOptionGroupName)) throw new InvalidParamException();
                
        this.openAccount = openAccount;
        this.opening = opening;
        this.accountOptionGroupName = accountOptionGroupName;
    }

    public Long calculateTotalAmount() {
        return openAccountOptionList.stream()
                .mapToLong(OpenAccountOption::getAccountOptionPrice)
                .sum();
    }
}
