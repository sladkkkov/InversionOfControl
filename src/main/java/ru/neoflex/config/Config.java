package ru.neoflex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.neoflex.repository.impl.OrderRepositoryImpl;
import ru.neoflex.service.impl.OrderServiceImpl;

@Configuration
@ComponentScan
public class Config {
    @Bean
    public OrderServiceImpl orderService(){
        return new OrderServiceImpl(orderRepository());
    }

    @Bean
    public OrderRepositoryImpl orderRepository(){
        return new OrderRepositoryImpl();
    }
}
