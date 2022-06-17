package ru.neoflex.service;

import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * UselessBeanPostProcessor.
 *
 * @author nanikeev
 */
@Log
public class UselessBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info(String.format("Bean %s registered", beanName));
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info(String.format("Bean %s finally configured", beanName));
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
