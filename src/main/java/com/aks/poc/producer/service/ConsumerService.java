package com.aks.poc.producer.service;

import com.azure.messaging.servicebus.ServiceBusProcessorClient;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Configuration
@EnableScheduling
@Slf4j
public class ConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    ServiceBusProcessorClient client;

    @Scheduled(fixedDelay = 2000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        LOGGER.info("Start Execution");
        client.start();
        Thread.sleep(5000);
        client.close();
        LOGGER.info("End Execution");
    }
}
