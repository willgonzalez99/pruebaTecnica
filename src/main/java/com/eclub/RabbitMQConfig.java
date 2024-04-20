package com.eclub;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String COLA_COMPRAS = "cola_compras";
    public static final String COLA_VENTAS = "cola_ventas";

    @Bean
    public Queue colaVentas() {
        return new Queue(COLA_VENTAS, true);
    }

    @Bean
    public Queue colaCompras() {
        return new Queue(COLA_COMPRAS, true);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}