package ru.neoflex.service;

import org.springframework.stereotype.Component;
import ru.neoflex.model.Order;

/**
 * OrderService.
 *
 * @author nanikeev
 */

public interface OrderService {

    void createNew(Long customerId);
}
