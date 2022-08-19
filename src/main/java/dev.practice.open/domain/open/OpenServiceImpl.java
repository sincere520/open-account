package dev.practice.open.domain.open;

import dev.practice.open.domain.open.Open;
import dev.practice.open.domain.open.OpenCommand;
import dev.practice.open.domain.open.OpenInfo;
import dev.practice.open.domain.open.OpenInfoMapper;
import dev.practice.open.domain.open.OpenAccountSeriesFactory;
import dev.practice.open.domain.open.OpenReader;
import dev.practice.open.domain.open.OpenService;
import dev.practice.open.domain.open.OpenStore;
import dev.practice.open.domain.open.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenServiceImpl implements OpenService {
    private final OpenStore openStore;
    private final OpenReader openReader;
    private final OpenAccountSeriesFactory openAccountSeriesFactory;
    private final PaymentProcessor paymentProcessor;
    private final OpenInfoMapper openInfoMapper;

    @Override
    @Transactional
    public String registerOpen(dev.practice.open.domain.open.OpenCommand.RegisterOpen requestOpen) {
        Open open = openStore.store(requestOpen.toEntity());
        openAccountSeriesFactory.store(open, requestOpen);
        return open.getOpenToken();
    }

    @Override
    @Transactional
    public void paymentOpen(dev.practice.open.domain.open.OpenCommand.PaymentRequest paymentRequest) {
        var openToken = paymentRequest.getOpenToken();
        var open = openReader.getOpen(openToken);
        paymentProcessor.pay(open, paymentRequest);
        open.openComplete();
    }

    @Override
    @Transactional(readOnly = true)
    public OpenInfo.Main retrieveOpen(String openToken) {
        var open = openReader.getOpen(openToken);
        var openAccountList = open.getOpenAccountList();
        return openInfoMapper.of(open, openAccountList);
    }

    @Override
    @Transactional
    public void updateReceiverInfo(String openToken, OpenCommand.UpdateReceiverInfoRequest request) {
        var open = openReader.getOpen(openToken);
        open.updateDeliveryFragment(
                request.getReceiverName(),
                request.getReceiverPhone(),
                request.getReceiverZipcode(),
                request.getReceiverAddress1(),
                request.getReceiverAddress2(),
                request.getEtcMessage()
        );
        open.deliveryPrepare();
    }
}
