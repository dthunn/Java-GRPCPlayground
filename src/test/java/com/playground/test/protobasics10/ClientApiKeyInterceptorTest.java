package com.playground.test.protobasics10;

import com.playground.models.protobasics10.BalanceCheckRequest;
import io.grpc.ClientInterceptor;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import protobasics.common.GrpcServer;
import protobasics10.BankService;
import protobasics10.Constants;
import protobasics10.interceptors.ApiKeyValidationInterceptor;

import java.util.List;

public class ClientApiKeyInterceptorTest extends AbstractInterceptorTest {

    private static final Logger log = LoggerFactory.getLogger(ClientApiKeyInterceptorTest.class);

    @Override
    protected List<ClientInterceptor> getClientInterceptors() {
        return List.of(
                MetadataUtils.newAttachHeadersInterceptor(getApiKey())
        );
    }

    @Override
    protected GrpcServer createServer() {
        return GrpcServer.create(6565, builder -> {
            builder.addService(new BankService())
                    .intercept(new ApiKeyValidationInterceptor());
        });
    }

    @Test
    public void clientApiKeyDemo(){
        var request = BalanceCheckRequest.newBuilder()
                .setAccountNumber(1)
                .build();
        var response = this.bankBlockingStub.getAccountBalance(request);
        log.info("{}", response);
    }

    private Metadata getApiKey(){
        var metadata = new Metadata();
        metadata.put(Constants.API_KEY, "bank-client-secret");
        return metadata;
    }
}
