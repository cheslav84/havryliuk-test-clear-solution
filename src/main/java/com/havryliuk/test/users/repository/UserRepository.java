package com.havryliuk.test.users.repository;

import com.havryliuk.test.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
