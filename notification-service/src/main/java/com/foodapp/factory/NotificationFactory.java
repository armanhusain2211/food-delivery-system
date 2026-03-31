package com.foodapp.factory;

import com.foodapp.handler.NotificationHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class NotificationFactory {

    private final Map<String, NotificationHandler> handlers;

    public NotificationFactory(List<NotificationHandler> handlerList) {
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(
                        h -> h.getClass().getSimpleName().replace("Handler", "").toUpperCase(),
                        Function.identity()
                ));
    }

    public NotificationHandler getHandler(String type) {
        return handlers.get(type);
    }
}