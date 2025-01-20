package com.playground.test.protobasics10;

import com.playground.models.protobasics10.Money;
import com.playground.models.protobasics10.WithdrawRequest;
import com.playground.test.common.ResponseObserver;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

public class ExecutorCallOptionsTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(ExecutorCallOptionsTest.class);

    @Test
    public void executorDemo() {
//        var observer = ResponseObserver.<Money>create();
//        var request = WithdrawRequest.newBuilder()
//                .setAccountNumber(1)
//                .setAmount(30)
//                .build();
//        this.bankStub
//                .withExecutor(Executors.newVirtualThreadPerTaskExecutor())
//                .withdraw(request, observer);
//        observer.await();
    }
}
