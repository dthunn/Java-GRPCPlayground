package com.playground.test.protobasics09;

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


public class LazyChannelDemoTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(LazyChannelDemoTest.class);
    private final GrpcServer grpcServer = GrpcServer.create(new DeadlineBankService());
    private BankServiceGrpc.BankServiceBlockingStub bankBlockingStub;

    @BeforeAll
    public void setup() {
        //  this.grpcServer.start();
        this.bankBlockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    @Test
    public void lazyChannelDemo() {
        var ex = Assertions.assertThrows(StatusRuntimeException.class, () -> {
            var request = BalanceCheckRequest.newBuilder()
                    .setAccountNumber(1)
                    .build();
            var response = this.bankBlockingStub.getAccountBalance(request);
            log.info("{}", response);
        });
        Assertions.assertEquals(Status.Code.UNAVAILABLE, ex.getStatus().getCode());
    }

    @AfterAll
    public void stop() {
        this.grpcServer.stop();
    }
}
