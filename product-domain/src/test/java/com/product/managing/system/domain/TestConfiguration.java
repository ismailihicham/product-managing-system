package com.product.managing.system.domain;

import com.product.managing.system.UseCasesDomainServiceImpl;
import com.product.managing.system.mapper.AccountDataMapper;
import com.product.managing.system.mapper.OrderDataMapper;
import com.product.managing.system.ports.input.UseCasesDomainService;
import com.product.managing.system.ports.output.AccountRepository;
import com.product.managing.system.ports.output.OrderRepository;
import com.product.managing.system.ports.output.ProductRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.product.managing.system")
public class TestConfiguration {

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public AccountRepository accountRepository() {

        return Mockito.mock(AccountRepository.class);
    }

    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public OrderDataMapper orderDataMapper() {
        return Mockito.mock(OrderDataMapper.class);
    }

    public AccountDataMapper accountDataMapper() {
        return Mockito.mock(AccountDataMapper.class);
    }


    @Bean
    public UseCasesDomainService useCasesDomainService() {
        return new UseCasesDomainServiceImpl(
                orderRepository(),
                orderDataMapper(),
                accountDataMapper(),
                productRepository(),
                accountRepository()
                );
    }
}
