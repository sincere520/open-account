package dev.practice.order.infrastructure.order.remit;

import dev.practice.order.domain.order.remit.RemitMessageChannelSender;
import dev.practice.order.domain.order.remit.RemitPaymentCompleteMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RemitMessageAwsSqsSender implements RemitMessageChannelSender {
    private final String SQS_QUEUE_NAME = "order-payComplete-live.fifo";
    private final QueueMessagingTemplate queueMessagingTemplate;

    @Override
    public void paymentComplete(RemitPaymentCompleteMessage message) {
        try {
            Map<String, Object> headers = new HashMap<>();
            headers.put(SqsMessageHeaders.SQS_GROUP_ID_HEADER, "item-queues");
            log.info("testtttt1:"+headers.toString());
            headers.put(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER, UUID.randomUUID().toString());
            log.info("testtttt22:"+headers.toString());
            log.info("testtttt23:"+message.toString());
            queueMessagingTemplate.convertAndSend(SQS_QUEUE_NAME, message, headers);
//            queueMessagingTemplate.send(message);
            log.info("testtttt3:"+headers.toString());
            log.info("[SQS enqueued topic: {}, message: {}", SQS_QUEUE_NAME, message);
        } catch (Exception e) {
            log.error("SQS failed topic: {}, message: {}", SQS_QUEUE_NAME, message);
            throw new RuntimeException(e);
        }
    }
}
