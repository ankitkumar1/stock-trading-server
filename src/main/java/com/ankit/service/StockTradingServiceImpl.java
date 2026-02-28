package com.ankit.service;

import com.ankit.entity.Stock;
import com.ankit.grpc.StockRequest;
import com.ankit.grpc.StockResponse;
import com.ankit.grpc.StockTradingServiceGrpc;
import com.ankit.repository.StockRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@GrpcService
public class StockTradingServiceImpl extends StockTradingServiceGrpc.StockTradingServiceImplBase {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public void getStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        String stockSymbol = request.getStockSymbol();
        Stock stock = stockRepository.findByStockSymbol(stockSymbol);

        StockResponse response = StockResponse.newBuilder()
                    .setSymbol(stockSymbol)
                .setPrice(stock.getPrice())
                .setTimestamp(stock.getLastUpdated().toString()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void subscribeStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver){
        String symbol = request.getStockSymbol();

        for (int i=0; i<10; i++){
            StockResponse response = StockResponse.newBuilder()
                    .setSymbol(symbol)
                    .setPrice(new Random().nextDouble(200))
                    .setTimestamp(Instant.now().toString()).build();
            responseObserver.onNext(response);
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException exp){
                responseObserver.onError(exp);
            }
        }

        responseObserver.onCompleted();
    }
}
