package com.as.users;

import com.as.users.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    @Cacheable(value = "user", unless = "#result==null")
    Optional<User> findById(Long id);

    @Override
    @CachePut(value = "user", key="#result.id", unless = "#result==null")
    User save(User user);

    @Override
    @CacheEvict(value="user", condition = "#id!=null")
    void deleteById(Long id);

    @Override
    @CacheEvict(value="user", key="#user.id", condition = "#user!=null")
    void delete(User user);

    @Override
    @CacheEvict(value = "user",allEntries = true)
    void deleteAll();

}
