package com.aks.poc.producer.config;

import com.azure.identity.ManagedIdentityCredentialBuilder;
import com.azure.messaging.servicebus.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ServiceBusClientConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceBusClientConfiguration.class);
    private static final String SERVICE_BUS_FQDN = "xxx.servicebus.windows.net";
    private static final String QUEUE_NAME = "xxx";

    @Bean
    ServiceBusClientBuilder serviceBusClientBuilder() {
        return new ServiceBusClientBuilder()
                .fullyQualifiedNamespace(SERVICE_BUS_FQDN)
                .credential(new ManagedIdentityCredentialBuilder().build());
    }

    @Bean
    ServiceBusProcessorClient serviceBusProcessorClient(ServiceBusClientBuilder builder) {
        return builder.processor()
                .queueName(QUEUE_NAME)
                .processMessage(ServiceBusClientConfiguration::processMessage)
                .processError(ServiceBusClientConfiguration::processError)
                .buildProcessorClient();
    }

    private static void processMessage(ServiceBusReceivedMessageContext context) {
        ServiceBusReceivedMessage message = context.getMessage();
        LOGGER.info("Processing message {}", message.getBody());
    }

    private static void processError(ServiceBusErrorContext context) {
        LOGGER.error("Error");
    }
}
