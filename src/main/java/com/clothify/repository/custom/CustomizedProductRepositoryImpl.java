package com.clothify.repository.custom;

import com.clothify.domain.product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CustomizedProductRepositoryImpl implements CustomizedProductRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Product> findAllByPublished(Pageable pageable, boolean isPublished) {
        List<Long> productIds = em.createQuery("""
                            select p.id from Product p where p.isPublished = :isPublished
                        """, Long.class)
                .setParameter("isPublished", isPublished)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return null;
    }
}
