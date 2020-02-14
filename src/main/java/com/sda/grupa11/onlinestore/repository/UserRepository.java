package com.sda.grupa11.onlinestore.repository;


import com.sda.grupa11.onlinestore.model.User;
import com.sda.grupa11.onlinestore.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String usernmae);

    List<User> findAllByRole(Role role);

}
