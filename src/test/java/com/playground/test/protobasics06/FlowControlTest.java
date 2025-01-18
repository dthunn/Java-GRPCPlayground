package com.playground.test.protobasics06;

import com.playground.models.protobasics06.FlowControlServiceGrpc;
import com.playground.test.common.AbstractChannelTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import protobasics.common.GrpcServer;
import protobasics.service.FlowControlService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FlowControlTest extends AbstractChannelTest {

    private final GrpcServer server = GrpcServer.create(new FlowControlService());
    private FlowControlServiceGrpc.FlowControlServiceStub stub;

    @BeforeAll
    public void setup() {
        this.server.start();
        this.stub = FlowControlServiceGrpc.newStub(channel);
    }

    @Test
    public void flowControlDemo() {
        var responseObserver = new ResponseHandler();
        var requestObserver = this.stub.getMessages(responseObserver);
        responseObserver.setRequestObserver(requestObserver);
        responseObserver.start();
        responseObserver.await();
    }

    @AfterAll
    public void stop() {
        this.server.stop();
    }
}
