package com.rtkit.golos.core.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
@Configuration
public class MessagingConfig {

    @Bean
    @Autowired
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(produceJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("golos");
    }

    @Bean
    public Queue promotionMessageQueue() {
        return new Queue("promotion", false);
    }

    @Bean
    public Binding bindingPromotionQueue(@Qualifier("promotionMessageQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("promotion");
    }

    @Bean
    public Jackson2JsonMessageConverter produceJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
