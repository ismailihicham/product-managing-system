package com.product.managing.system.dataaccess.user.adapter;

import com.product.managing.system.dataaccess.exception.DataAccessException;
import com.product.managing.system.dataaccess.user.mapper.UserAccountDataMapper;
import com.product.managing.system.dataaccess.user.repository.UserJpaRepository;
import com.product.managing.system.entities.Account;
import com.product.managing.system.ports.output.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRespositoryImpl implements AccountRepository {

    private final UserJpaRepository jpaRepository;
    private final UserAccountDataMapper mapper;

    public UserRespositoryImpl(UserJpaRepository jpaRepository, UserAccountDataMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Account findAccount(UUID id) {
        var userEntity = jpaRepository.findById(id);
        if(userEntity.isEmpty()) {
            throw new DataAccessException("User with id "+ id + "is not found");
        }
        return mapper.userToAccount(userEntity.get());
    }

    @Override
    public Account createAccount(Account account) {
        var user = jpaRepository.save(mapper.accountToUserEntity(account));
        return mapper.userToAccount(user);
    }
}
