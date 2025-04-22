package com.product.managing.system.dataaccess.product.adapter;

import com.product.managing.system.entities.Product;
import com.product.managing.system.ports.output.ProductRepository;
import com.product.managing.system.dataaccess.product.mapper.ProductDataMapper;
import com.product.managing.system.dataaccess.product.repository.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository jpaRepository;
    private final ProductDataMapper mapper;

    public ProductRepositoryImpl(ProductJpaRepository jpaRepository, ProductDataMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Product saveProduct(Product product) {
        var productEntity = jpaRepository.save(mapper.productToProductEntity(product));
        return mapper.productEntityToProduct(productEntity);
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        var p = jpaRepository.findById(productId);
        return p.map(mapper::productEntityToProduct);
    }

    @Override
    public void removeProductById(UUID productId) {
        jpaRepository.deleteById(productId);

    }

    @Override
    public List<Product> retrieveAllProducts() {
        return jpaRepository.findAll().stream().map(mapper::productEntityToProduct).toList();
    }
}
