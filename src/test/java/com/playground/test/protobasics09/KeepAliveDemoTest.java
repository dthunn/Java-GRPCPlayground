package com.playground.test.protobasics09;

import com.google.common.util.concurrent.Uninterruptibles;
import com.playground.models.protobasics09.BalanceCheckRequest;
import com.playground.models.protobasics09.BankServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protobasics.common.GrpcServer;
import protobasics.service.DeadlineBankService;

import java.util.concurrent.TimeUnit;

public class KeepAliveDemoTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(KeepAliveDemoTest.class);
    private final GrpcServer grpcServer = GrpcServer.create(new DeadlineBankService());
    private BankServiceGrpc.BankServiceBlockingStub bankBlockingStub;

    @BeforeAll
    public void setup() {
        this.grpcServer.start();
        this.bankBlockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    /*
        Configure the server with keep alive
     */
    // @Test
    public void keepAliveDemo() {
        var request = BalanceCheckRequest.newBuilder()
                .setAccountNumber(1)
                .build();
        var response = this.bankBlockingStub.getAccountBalance(request);
        log.info("{}", response);

        // just blocking the thread for 30 seconds
        Uninterruptibles.sleepUninterruptibly(30, TimeUnit.SECONDS);
    }

    @AfterAll
    public void stop() {
        this.grpcServer.stop();
    }
}
