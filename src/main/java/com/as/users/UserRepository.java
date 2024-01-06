package com.as.users;

import com.as.users.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    @Cacheable("user")
    Optional<User> findById(Long id);

    @Override
    @CachePut(value = "user", key="#result.id")
    User save(User user);

    @Override
    @CacheEvict(value="user")
    void deleteById(Long id);

    @Override
    @CacheEvict(value="user", key="#user.id")
    void delete(User user);

    @Override
    @CacheEvict(allEntries = true)
    void deleteAll();

}
