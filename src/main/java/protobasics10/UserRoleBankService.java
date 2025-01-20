package protobasics10;

import com.playground.models.protobasics10.AccountBalance;
import com.playground.models.protobasics10.BalanceCheckRequest;
import com.playground.models.protobasics10.BankServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protobasics10.repository.AccountRepository;

public class UserRoleBankService extends BankServiceGrpc.BankServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(UserRoleBankService.class);

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        var accountNumber = request.getAccountNumber();
        var balance = AccountRepository.getBalance(accountNumber);
        if(UserRole.STANDARD.equals(Constants.USER_ROLE_KEY.get())){
            var fee = balance > 0 ? 1 : 0;
            AccountRepository.deductAmount(accountNumber, fee);
            balance = balance - fee;
        }
        var accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(balance)
                .build();
        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();
    }

}
