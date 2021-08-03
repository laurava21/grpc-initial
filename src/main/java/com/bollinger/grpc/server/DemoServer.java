package com.bollinger.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * Demo server
 * lva
 */
public class DemoServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello gRPC!");
        Server server = ServerBuilder.forPort(50051)
                .addService(new GreetServiceImpl())
                .addService(new CalculatorServiceImpl())
                .build();
        server.start();

        // before await
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("Receive a shutdown request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        // for wait to terminate the program
        server.awaitTermination();
    }
}
