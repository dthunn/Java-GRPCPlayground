package com.playground.test.protobasics08;

import com.playground.models.protobasics08.AccountBalance;
import com.playground.models.protobasics08.BalanceCheckRequest;
import com.playground.models.protobasics08.ValidationCode;
import com.playground.test.common.ResponseObserver;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnaryInputValidationTest extends  AbstractTest {

    @Test
    public void blockingInputValidationTest(){
        var ex = Assertions.assertThrows(StatusRuntimeException.class, () -> {
            var request = BalanceCheckRequest.newBuilder()
                    .setAccountNumber(11)
                    .build();
            var response = this.bankBlockingStub.getAccountBalance(request);
        });
        Assertions.assertEquals(ValidationCode.INVALID_ACCOUNT, getValidationCode(ex));
    }

    @Test
    public void asyncInputValidationTest(){
        var request = BalanceCheckRequest.newBuilder()
                .setAccountNumber(11)
                .build();
        var observer = ResponseObserver.<AccountBalance>create();
        this.bankStub.getAccountBalance(request, observer);
        observer.await();

        Assertions.assertTrue(observer.getItems().isEmpty());
        Assertions.assertNotNull(observer.getThrowable());
        Assertions.assertEquals(ValidationCode.INVALID_ACCOUNT, getValidationCode(observer.getThrowable()));
    }
}
