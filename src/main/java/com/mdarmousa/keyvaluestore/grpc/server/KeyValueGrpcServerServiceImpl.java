package com.mdarmousa.keyvaluestore.grpc.server;

import com.mdarmousa.keyvaluestore.grpc.KeyValueRequest;
import com.mdarmousa.keyvaluestore.grpc.KeyValueResponse;
import com.mdarmousa.keyvaluestore.grpc.KeyValueServiceGrpc;
import com.mdarmousa.keyvaluestore.models.KeyValue;
import com.mdarmousa.keyvaluestore.service.KeyValueService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class KeyValueGrpcServerServiceImpl extends KeyValueServiceGrpc.KeyValueServiceImplBase {
    private  final KeyValueService keyValueService;
    KeyValueGrpcServerServiceImpl(KeyValueService keyValueService){
        this.keyValueService = keyValueService;
    }

    @Override
    public void save(KeyValueRequest request, StreamObserver<KeyValueResponse> responseObserver) {

        var savedResult = this.keyValueService
                .save(new KeyValue(request.getKey(), request.getValue()));

        var response = KeyValueResponse.newBuilder()
                .setKey(savedResult.key())
                .setValue(savedResult.value())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
