package br.com.rezultz.participantserviceregistracionfila.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "participant.exchange";
    public static final String QUEUE = "participant.service.fila.queue";
    public static final String ROUTING_KEY = "participant.service.fila.routingKey";
    public static final String EXCHANGE_DLX = "participant.exchange.dlx";
    public static final String QUEUE_DLQ = "participant.service.fila.queue.dlq";
    public static final String ROUTING_KEY_DlQ = "participant.service.fila.routingKey.dlq";


    @Bean
    public Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", EXCHANGE_DLX);
        return new Queue(QUEUE, true, false, false, args);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }


    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

    @Bean
    public DirectExchange exchangeDLX() {
        return new DirectExchange(EXCHANGE_DLX);
    }

    @Bean
    public Queue queueDLQ() {
        return new Queue(QUEUE_DLQ, true);
    }

    @Bean
    public Binding bindingDLQ(DirectExchange exchange) {
        return BindingBuilder.bind(queueDLQ()).to(exchangeDLX()).with(ROUTING_KEY_DlQ);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public MessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate) {
        return new RepublishMessageRecoverer(
                rabbitTemplate,
                EXCHANGE_DLX,
                ROUTING_KEY_DlQ
        );
    }
}
