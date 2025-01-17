package com.playground.test.protobasics06;

import com.google.protobuf.Empty;
import com.playground.models.protobasics06.AccountBalance;
import com.playground.models.protobasics06.AllAccountsResponse;
import com.playground.models.protobasics06.BalanceCheckRequest;
import com.playground.test.common.ResponseObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnaryAsyncClientTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(UnaryAsyncClientTest.class);

    @Test
    public void getBalanceTest() {
        var request = BalanceCheckRequest.newBuilder().setAccountNumber(1).build();
        var observer = ResponseObserver.<AccountBalance>create();
        this.bankStub.getAccountBalance(request, observer);
        observer.await();
        Assertions.assertEquals(1, observer.getItems().size());
        Assertions.assertEquals(100, observer.getItems().get(0).getBalance());
        Assertions.assertNull(observer.getThrowable());
    }

    @Test
    public void allAccountsTest(){
        var observer = ResponseObserver.<AllAccountsResponse>create();
        this.bankStub.getAllAccounts(Empty.getDefaultInstance(), observer);
        observer.await();
        Assertions.assertEquals(1, observer.getItems().size());
        Assertions.assertEquals(10, observer.getItems().get(0).getAccountsCount());
        Assertions.assertNull(observer.getThrowable());
    }
}
