package com.clothify.repository.custom;


import com.clothify.domain.warehouse.Inventory;

public interface CustomizedInventoryRepository {
    void setLockTimeout(long timeoutDurationInMs);
    long getLockTimeout();
    Inventory getByIdAndObtainPessimisticWriteLocking(Long id);
}
