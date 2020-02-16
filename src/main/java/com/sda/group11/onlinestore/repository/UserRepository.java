package com.sda.group11.onlinestore.repository;


import com.sda.group11.onlinestore.model.enums.Role;
import com.sda.group11.onlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    List<User> findAllByRole(Role role);

}
