package com.sda.grupa11.onlinestore.repository;


import com.sda.grupa11.onlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String usernmae);

}
