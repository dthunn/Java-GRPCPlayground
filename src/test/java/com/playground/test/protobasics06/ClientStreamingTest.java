package com.playground.test.protobasics06;

import com.playground.models.protobasics06.AccountBalance;
import com.playground.models.protobasics06.DepositRequest;
import com.playground.models.protobasics06.Money;
import com.playground.test.common.ResponseObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class ClientStreamingTest extends AbstractTest {

    @Test
    public void depositTest() {
        var responseObserver = ResponseObserver.<AccountBalance>create();
        var requestObserver = this.bankStub.deposit(responseObserver);

        // initial message - account number
        requestObserver.onNext(DepositRequest.newBuilder().setAccountNumber(5).build());

        // sending stream of money
        IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Money.newBuilder().setAmount(10).build())
                .map(m -> DepositRequest.newBuilder().setMoney(m).build())
                .forEach(requestObserver::onNext);

        // notifying the server that we are done
        requestObserver.onCompleted();

        // at this point out response observer should receive a response
        responseObserver.await();

        // assert
        Assertions.assertEquals(1, responseObserver.getItems().size());
        Assertions.assertEquals(200, responseObserver.getItems().get(0).getBalance());
        Assertions.assertNull(responseObserver.getThrowable());
    }
}
