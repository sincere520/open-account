package dev.practice.open.domain.open.account;

import com.google.common.collect.Lists;
import dev.practice.open.common.exception.IllegalStatusException;
import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.domain.AbstractEntity;
import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.account.OpenAccountOptionGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "open_account")
public class OpenAccount extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "open_id")
    private Open open;

    private Integer openCount;
    private Long customerId;
    private Long accountId;
    private String accountName;
    private String accountToken;
    private Long accountPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "openAccount", cascade = CascadeType.PERSIST)
    private List<OpenAccountOptionGroup> openAccountOptionGroupList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Getter
    @AllArgsConstructor
    public enum DeliveryStatus {
        BEFORE_DELIVERY("배송전"),
        DELIVERY_PREPARE("배송준비중"),
        DELIVERING("배송중"),
        COMPLETE_DELIVERY("배송완료");

        private final String description;
    }

    @Builder
    public OpenAccount(
            Open open,
            Integer openCount,
            Long customerId,
            Long accountId,
            String accountName,
            String accountToken,
            Long accountPrice
    ) {
        if (open == null) throw new InvalidParamException("OpenAccountLine.open");
        if (openCount == null) throw new InvalidParamException("OpenAccountLine.openCount");
        if (customerId == null) throw new InvalidParamException("OpenAccountLine.customerId");
        if (accountId == null && StringUtils.isEmpty(accountName))
            throw new InvalidParamException("OpenAccountLine.accountNo and accountName");
        if (StringUtils.isEmpty(accountToken)) throw new InvalidParamException("OpenAccountLine.accountToken");
        if (accountPrice == null) throw new InvalidParamException("OpenAccountLine.accountPrice");

        this.open = open;
        this.openCount = openCount;
        this.customerId = customerId;
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountToken = accountToken;
        this.accountPrice = accountPrice;
        this.deliveryStatus = DeliveryStatus.BEFORE_DELIVERY;
    }

    public Long calculateTotalAmount() {
        var accountOptionTotalAmount = openAccountOptionGroupList.stream()
                .mapToLong(OpenAccountOptionGroup::calculateTotalAmount)
                .sum();
        return (accountPrice + accountOptionTotalAmount) * openCount;
    }

    public void deliveryPrepare() {
        if (this.deliveryStatus != DeliveryStatus.BEFORE_DELIVERY) throw new IllegalStatusException();
        this.deliveryStatus = DeliveryStatus.DELIVERY_PREPARE;
    }

    public void inDelivery() {
        if (this.deliveryStatus != DeliveryStatus.DELIVERY_PREPARE) throw new IllegalStatusException();
        this.deliveryStatus = DeliveryStatus.DELIVERING;
    }

    public void deliveryComplete() {
        if (this.deliveryStatus != DeliveryStatus.DELIVERING) throw new IllegalStatusException();
        this.deliveryStatus = DeliveryStatus.COMPLETE_DELIVERY;
    }
}
