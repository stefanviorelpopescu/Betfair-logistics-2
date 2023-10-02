package org.digitalstack.logistics.dao.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OrderStatus {
    NEW,
    DELIVERING,
    DELIVERED,
    CANCELED,
    ;

    public static final Map<OrderStatus, List<OrderStatus>> allowedTransitions = new HashMap<>();

    static {
        allowedTransitions.put(NEW, List.of(DELIVERING, DELIVERED, CANCELED));
        allowedTransitions.put(DELIVERING, List.of(DELIVERED, CANCELED));
        allowedTransitions.put(DELIVERED, Collections.emptyList());
        allowedTransitions.put(CANCELED, Collections.emptyList());
    }
}
