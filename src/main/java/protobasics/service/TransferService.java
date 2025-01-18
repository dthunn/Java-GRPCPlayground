package protobasics.service;

import com.playground.models.protobasics06.TransferRequest;
import com.playground.models.protobasics06.TransferResponse;
import com.playground.models.protobasics06.TransferServiceGrpc;
import io.grpc.stub.StreamObserver;
import protobasics.requesthandlers.TransferRequestHandler;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {

    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> responseObserver) {
        return new TransferRequestHandler(responseObserver);
    }

}
