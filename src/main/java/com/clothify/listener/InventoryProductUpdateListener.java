package com.clothify.listener;

//import com.nix.ecommerceapi.event.InventoryProductUpdateEvent;
//import com.nix.ecommerceapi.repository.InventoryProductRepository;
//import java.util.HashMap;
//import java.util.Map;
//import lombok.AllArgsConstructor;
//import org.springframework.context.event.EventListener;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//@AllArgsConstructor
//@Transactional
//public class InventoryProductUpdateListener {
//    private final InventoryProductRepository repository;
//
//    @Async
//    @EventListener
//    public void updateListener(InventoryProductUpdateEvent event) {
//        Map<Long, Long> amount = new HashMap<>();
//        event.products().forEach(
//                cartResponse -> {
//                    long value = amount.getOrDefault(cartResponse.getProduct().getProductId(), 0L);
//                    amount.put(cartResponse.getProduct().getProductId(), value + cartResponse.getQuantity());
//                }
//        );
//        amount.forEach(repository::atomicUpdate);
//    }
//}
