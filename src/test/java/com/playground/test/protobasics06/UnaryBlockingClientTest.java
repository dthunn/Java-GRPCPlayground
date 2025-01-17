package com.playground.test.protobasics06;

import com.google.protobuf.Empty;
import com.playground.models.protobasics06.BalanceCheckRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnaryBlockingClientTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(UnaryBlockingClientTest.class);

    @Test
    public void getBalanceTest() {
        var request = BalanceCheckRequest.newBuilder()
                .setAccountNumber(1)
                .build();

        var balance = this.bankBlockingStub.getAccountBalance(request);
        log.info("unary balance received: {}", balance);

        Assertions.assertEquals(100, balance.getBalance());
    }

    @Test
    public void allAccountsTest(){
        var allAccounts = this.bankBlockingStub.getAllAccounts(Empty.getDefaultInstance());
        log.info("all accounts size: {}", allAccounts.getAccountsCount());
        Assertions.assertEquals(10, allAccounts.getAccountsCount());
    }
}
