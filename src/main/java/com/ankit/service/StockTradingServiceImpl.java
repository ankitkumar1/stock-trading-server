package com.ankit.service;

import com.ankit.entity.Stock;
import com.ankit.grpc.StockRequest;
import com.ankit.grpc.StockResponse;
import com.ankit.grpc.StockTradingServiceGrpc;
import com.ankit.repository.StockRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

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
}
