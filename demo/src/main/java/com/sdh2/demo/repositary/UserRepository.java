package com.sdh2.demo.repositary;

import com.sdh2.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to find
     * @return an Optional containing the User if found, or an empty Optional if no user is found
     */
     Optional<User> findByEmail(String email);

    /**
     * Finds a user by their name.
     *
     * @param name the email address of the user to find
     * @return an Optional containing the User if found, or an empty Optional if no user is found
     */
     Optional<User> findByName(String name);

    /**
     * Finds a user by their name and email address.
     * @param name the email address of the user to find
     * @param email the email address of the user to find
     * @return an Optional containing the User if found, or an empty Optional if no user is found
     */
     Optional<User> findByNameAndEmail(String name, String email);

}
