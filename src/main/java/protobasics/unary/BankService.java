package protobasics.unary;

import com.playground.models.protobasics06.AccountBalance;
import com.playground.models.protobasics06.BalanceCheckRequest;
import com.playground.models.protobasics06.BankServiceGrpc;
import io.grpc.stub.StreamObserver;
import protobasics.repository.AccountRepository;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        var accountNumber = request.getAccountNumber();
        var balance = AccountRepository.getBalance(accountNumber);
        var accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(balance)
                .build();

        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();
    }
}
