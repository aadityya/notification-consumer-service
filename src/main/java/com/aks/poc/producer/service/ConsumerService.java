package com.aks.poc.producer.service;

import com.aks.poc.producer.dto.Notification;
import com.azure.spring.messaging.checkpoint.Checkpointer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.azure.spring.messaging.AzureHeaders.CHECKPOINTER;

@Service
@Slf4j
public class ConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @Bean
    public Consumer<Message<String>> consume() {
        return message->{
            Checkpointer checkpointer = (Checkpointer) message.getHeaders().get(CHECKPOINTER);
            Notification notification = new Gson().fromJson(message.getPayload(), Notification.class);
            LOGGER.info("New message received: '{}'", message.getPayload());
            LOGGER.info("Message: '{}'", notification.getMessage());
            checkpointer.success()
                    .doOnSuccess(s->LOGGER.info("Message '{}' successfully checkpointed", message.getPayload()))
                    .doOnError(e->LOGGER.error("Error found", e))
                    .block();
        };
    }
}
