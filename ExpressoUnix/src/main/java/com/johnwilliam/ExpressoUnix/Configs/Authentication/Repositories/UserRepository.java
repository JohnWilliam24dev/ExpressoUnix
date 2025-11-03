package com.johnwilliam.ExpressoUnix.Configs.Authentication.Repositories;

import com.johnwilliam.ExpressoUnix.Configs.Authentication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
