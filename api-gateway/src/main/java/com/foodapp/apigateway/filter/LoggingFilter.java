package com.foodapp.apigateway.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class LoggingFilter implements
        HandlerFilterFunction<ServerResponse, ServerResponse> {

    @Override
    public ServerResponse filter(
            ServerRequest request,
            HandlerFunction<ServerResponse> next) throws Exception {

        System.out.println("Request Path : " + request.path());

        return next.handle(request);
    }
}