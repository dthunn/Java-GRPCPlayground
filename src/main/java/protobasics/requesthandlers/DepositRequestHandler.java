package protobasics.requesthandlers;

import com.playground.models.protobasics06.AccountBalance;
import com.playground.models.protobasics06.DepositRequest;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protobasics.repository.AccountRepository;

public class DepositRequestHandler implements StreamObserver<DepositRequest> {

    private static final Logger log = LoggerFactory.getLogger(DepositRequestHandler.class);
    private final StreamObserver<AccountBalance> responseObserver;
    private int accountNumber;

    public DepositRequestHandler(StreamObserver<AccountBalance> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(DepositRequest depositRequest) {
        log.info("received deposit {}", depositRequest);
        switch (depositRequest.getRequestCase()) {
            case ACCOUNT_NUMBER -> this.accountNumber = depositRequest.getAccountNumber();
            case MONEY -> AccountRepository.addAmount(this.accountNumber, depositRequest.getMoney().getAmount());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("client error {}", throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        var accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(this.accountNumber)
                .setBalance(AccountRepository.getBalance(this.accountNumber))
                .build();
        this.responseObserver.onNext(accountBalance);
        this.responseObserver.onCompleted();
    }
}
