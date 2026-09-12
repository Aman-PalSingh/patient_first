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
        String patientId = billingRequest.getPatientId();
        String name = billingRequest.getName();
        String email = billingRequest.getEmail();

        log.info("Patient Id: {}", patientId);
        log.info("Name: {}", name);
        log.info("Email: {}", email);

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("ACC-" + patientId)
                .setStatus("Account created for " + name)
                .build();

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();

    }
}
