package com.bollinger.grpc.client;

import com.bollinger.proto.calculator.Calculator;
import com.bollinger.proto.calculator.CalculatorRequest;
import com.bollinger.proto.calculator.CalculatorResponse;
import com.bollinger.proto.calculator.CalculatorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorClient {
    public static void main(String[] args) {
        System.out.println("Hello I'm the gRPC client!");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50051)
                .usePlaintext()
                .build();

        System.out.println("Creating stub");
        CalculatorServiceGrpc.CalculatorServiceBlockingStub client = CalculatorServiceGrpc.newBlockingStub(channel);

        Calculator calculator = Calculator.newBuilder()
                .setSum1(23L)
                .setSum2(23456L)
                .build();

        CalculatorRequest calculatorRequest = CalculatorRequest.newBuilder()
                .setCalculator(calculator)
                .build();

        CalculatorResponse calculatorResponse = client.sum(calculatorRequest);

        System.out.println(calculatorResponse.getResult());
    }
}
