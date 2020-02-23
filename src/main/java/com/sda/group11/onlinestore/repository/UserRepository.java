package com.sda.group11.onlinestore.repository;


import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    List<User> findAllByRole(Role role);
}
