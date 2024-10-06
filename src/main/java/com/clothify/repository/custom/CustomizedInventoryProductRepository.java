package com.clothify.repository.custom;

import com.clothify.domain.warehouse.InventoryProduct;

public interface CustomizedInventoryProductRepository {
    void setLockTimeout(long timeoutDurationInMs);
    long getLockTimeout();
    InventoryProduct getByIdAndObtainPessimisticWriteLocking(Long id);

}
