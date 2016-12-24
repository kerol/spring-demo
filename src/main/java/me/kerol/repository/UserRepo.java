package me.kerol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by kevin on 24/12/2016.
 */
@Transactional
public interface UserRepo extends CrudRepository<User, Long> {

    public User findByName(String name);
    public User findByNameOrEmail(String name, String email);
    public User findByEmail(String email);
}
