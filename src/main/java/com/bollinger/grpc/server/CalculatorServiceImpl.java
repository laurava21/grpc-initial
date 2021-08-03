package com.bollinger.grpc.server;

import com.bollinger.proto.calculator.Calculator;
import com.bollinger.proto.calculator.CalculatorRequest;
import com.bollinger.proto.calculator.CalculatorResponse;
import com.bollinger.proto.calculator.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * Calculator service implementation
 * lva
 */
public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void sum(CalculatorRequest request, StreamObserver<CalculatorResponse> responseObserver) {

        // obtain values from request
        Calculator calculator = request.getCalculator();
        var sum1 = calculator.getSum1();
        var sum2 = calculator.getSum2();

        // calcule the sum
        var result = sum1 + sum2;

        // calculatorResponse
        var calculatorResponse = CalculatorResponse.newBuilder()
                .setResult( result)
                .build();

        // response
        responseObserver.onNext(calculatorResponse);

        // complete
        responseObserver.onCompleted();

    }
}
