package com.aps.billing_service.grpc;

import com.aps.billing.BillingRequest;
import com.aps.billing.BillingResponse;
import com.aps.billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseStreamObserver) {
        log.info("createBilling request received {}", billingRequest.toString());
        BillingResponse response = BillingResponse.newBuilder().setAccountId("12345").setStatus("ACTIVE").build();
        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();

    }
}
