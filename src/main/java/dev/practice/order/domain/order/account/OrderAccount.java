package dev.practice.order.domain.order.account;

import com.google.common.collect.Lists;
import dev.practice.order.common.exception.IllegalStatusException;
import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import dev.practice.order.domain.order.Order;
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
@Table(name = "order_accounts")
public class OrderAccount extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Integer orderCount;
    private Long customerId;
    private Long accountId;
    private String accountName;
    private String accountToken;
    private Long accountAmt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderAccount", cascade = CascadeType.PERSIST)
    private List<OrderAccountOptionGroup> orderAccountOptionGroupList = Lists.newArrayList();

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
    public OrderAccount(
            Order order,
            Integer orderCount,
            Long customerId,
            Long accountId,
            String accountName,
            String accountToken,
            Long accountAmt
    ) {
        if (order == null) throw new InvalidParamException("OrderAccountLine.order");
        if (orderCount == null) throw new InvalidParamException("OrderAccountLine.orderCount");
        if (customerId == null) throw new InvalidParamException("OrderAccountLine.customerId");
        if (accountId == null && StringUtils.isEmpty(accountName))
            throw new InvalidParamException("OrderAccountLine.accountNo and accountName");
        if (StringUtils.isEmpty(accountToken)) throw new InvalidParamException("OrderAccountLine.accountToken");
        if (accountAmt == null) throw new InvalidParamException("OrderAccountLine.accountAmt");

        this.order = order;
        this.orderCount = orderCount;
        this.customerId = customerId;
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountToken = accountToken;
        this.accountAmt = accountAmt;
        this.deliveryStatus = DeliveryStatus.BEFORE_DELIVERY;
    }

    public Long calculateTotalAmount() {
        var accountOptionTotalAmount = orderAccountOptionGroupList.stream()
                .mapToLong(OrderAccountOptionGroup::calculateTotalAmount)
                .sum();
        return (accountAmt + accountOptionTotalAmount) * orderCount;
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
