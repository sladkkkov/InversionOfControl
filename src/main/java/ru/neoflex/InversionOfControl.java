package ru.neoflex;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.neoflex.config.Config;
import ru.neoflex.service.OrderService;
import ru.neoflex.service.impl.OrderServiceImpl;

/**
 * InversionOfControl.
 *
 * @author nanikeev
 */

public class InversionOfControl {


    public static void main(String... args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.createNew(5L);
    }
}
