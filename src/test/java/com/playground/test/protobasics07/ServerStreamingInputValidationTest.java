package com.playground.test.protobasics07;

import com.playground.models.protobasics07.Money;
import com.playground.models.protobasics07.WithdrawRequest;
import com.playground.test.common.ResponseObserver;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ServerStreamingInputValidationTest extends AbstractTest {

    @ParameterizedTest
    @MethodSource("testdata")
    public void blockingInputValidationTest(WithdrawRequest request, Status.Code code){
        var ex = Assertions.assertThrows(StatusRuntimeException.class, () -> {
            var response = this.bankBlockingStub.withdraw(request).hasNext();
        });
        Assertions.assertEquals(code, ex.getStatus().getCode());
    }

    @ParameterizedTest
    @MethodSource("testdata")
    public void asyncInputValidationTest(WithdrawRequest request, Status.Code code){
        var observer = ResponseObserver.<Money>create();
        this.bankStub.withdraw(request, observer);
        observer.await();

        Assertions.assertTrue(observer.getItems().isEmpty());
        Assertions.assertNotNull(observer.getThrowable());
        Assertions.assertEquals(code, ((StatusRuntimeException) observer.getThrowable()).getStatus().getCode());
    }

    private Stream<Arguments> testdata(){
        return Stream.of(
                Arguments.of(WithdrawRequest.newBuilder().setAccountNumber(11).setAmount(10).build(), Status.Code.INVALID_ARGUMENT),
                Arguments.of(WithdrawRequest.newBuilder().setAccountNumber(1).setAmount(17).build(), Status.Code.INVALID_ARGUMENT),
                Arguments.of(WithdrawRequest.newBuilder().setAccountNumber(1).setAmount(120).build(), Status.Code.FAILED_PRECONDITION)
        );
    }
}
