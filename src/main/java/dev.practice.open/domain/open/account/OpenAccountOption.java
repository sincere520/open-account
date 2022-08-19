package dev.practice.open.domain.open.account;

import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.domain.AbstractEntity;
import dev.practice.open.domain.open.account.OpenAccountOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "open_account_options")
public class OpenAccountOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "open_account_option_group_id")
    private OpenAccountOptionGroup openAccountOptionGroup;
    private Integer opening;
    private String accountOptionName;
    private Long accountOptionPrice;

    @Builder
    public OpenAccountOption(
            OpenAccountOptionGroup openAccountOptionGroup,
            Integer opening,
            String accountOptionName,
            Long accountOptionPrice) {
        if (openAccountOptionGroup == null) throw new InvalidParamException();
        if (opening == null) throw new InvalidParamException();
        if (StringUtils.isEmpty(accountOptionName)) throw new InvalidParamException();
        if (accountOptionPrice == null) throw new InvalidParamException();

        this.openAccountOptionGroup = openAccountOptionGroup;
        this.opening = opening;
        this.accountOptionName = accountOptionName;
        this.accountOptionPrice = accountOptionPrice;
    }
}
