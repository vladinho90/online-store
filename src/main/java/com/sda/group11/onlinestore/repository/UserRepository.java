package com.sda.group11.onlinestore.repository;


import com.sda.group11.onlinestore.model.User;
import com.sda.group11.onlinestore.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

<<<<<<< HEAD
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String username);
=======
    Optional<User> findUserByUsername(String username);

    List<User> findAllByRole(Role role);
>>>>>>> 715dea130b8b7888f9c56bd67edaf6d50319aed7
}
