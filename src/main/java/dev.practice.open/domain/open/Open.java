package dev.practice.open.domain.open;

import com.google.common.collect.Lists;
import dev.practice.open.common.exception.IllegalStatusException;
import dev.practice.open.common.exception.InvalidParamException;
import dev.practice.open.common.util.TokenGenerator;
import dev.practice.open.domain.AbstractEntity;
import dev.practice.open.domain.open.fragment.DeliveryFragment;
import dev.practice.open.domain.open.account.OpenAccount;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Getter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "opens")
public class Open extends AbstractEntity {

    private static final String OPEN_PREFIX = "opn_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String openToken;
    private Long userId;
    private String payMethod;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "open", cascade = CascadeType.PERSIST)
    private List<OpenAccount> openAccountList = Lists.newArrayList();

    @Embedded
    private DeliveryFragment deliveryFragment;

    private ZonedDateTime openedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        INIT("주문시작"),
        OPEN_COMPLETE("주문완료"),
        DELIVERY_PREPARE("배송준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료");

        private final String description;
    }

    @Builder
    public Open(
            Long userId,
            String payMethod,
            DeliveryFragment deliveryFragment
    ) {
        if (userId == null) throw new InvalidParamException("Open.userId");
        if (StringUtils.isEmpty(payMethod)) throw new InvalidParamException("Open.payMethod");
        if (deliveryFragment == null) throw new InvalidParamException("Open.deliveryFragment");

        this.openToken = TokenGenerator.randomCharacterWithPrefix(OPEN_PREFIX);
        this.userId = userId;
        this.payMethod = payMethod;
        this.deliveryFragment = deliveryFragment;
        this.openedAt = ZonedDateTime.now();
        this.status = Status.INIT;
    }

    /**
     * 주문 가격 = 주문 상품의 총 가격
     * 주문 하나의 상품의 가격 = (상품 가격 + 상품 옵션 가격) * 주문 갯수
     */
    public Long calculateTotalAmount() {
        return openAccountList.stream()
                .mapToLong(OpenAccount::calculateTotalAmount)
                .sum();
    }

    public void openComplete() {
        if (this.status != Status.INIT) throw new IllegalStatusException();
        this.status = Status.OPEN_COMPLETE;
    }

    // TODO - 개별 배송도 구현
    public void deliveryPrepare() {
        if (this.status != Status.OPEN_COMPLETE) throw new IllegalStatusException();
        this.status = Status.DELIVERY_PREPARE;
        this.getOpenAccountList().forEach(OpenAccount::deliveryPrepare);
    }

    // TODO - 개별 배송도 구현
    public void inDelivery() {
        if (this.status != Status.DELIVERY_PREPARE) throw new IllegalStatusException();
        this.status = Status.IN_DELIVERY;
        this.getOpenAccountList().forEach(OpenAccount::inDelivery);
    }

    // TODO - 개별 배송도 구현
    public void deliveryComplete() {
        if (this.status != Status.IN_DELIVERY) throw new IllegalStatusException();
        this.status = Status.DELIVERY_COMPLETE;
        this.getOpenAccountList().forEach(OpenAccount::deliveryComplete);
    }

    // TODO - 개별 배송도 구현
    public boolean isAlreadyPaymentComplete() {
        switch (this.status) {
            case OPEN_COMPLETE:
            case DELIVERY_PREPARE:
            case IN_DELIVERY:
            case DELIVERY_COMPLETE:
                return true;
        }
        return false;
    }

    public void updateDeliveryFragment(
            String receiverName,
            String receiverPhone,
            String receiverZipcode,
            String receiverAddress1,
            String receiverAddress2,
            String etcMessage
    ) {
        this.deliveryFragment = DeliveryFragment.builder()
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverZipcode(receiverZipcode)
                .receiverAddress1(receiverAddress1)
                .receiverAddress2(receiverAddress2)
                .etcMessage(etcMessage)
                .build();
    }
}
