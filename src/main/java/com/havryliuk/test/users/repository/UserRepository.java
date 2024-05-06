package com.havryliuk.test.users.repository;

import com.havryliuk.test.users.dto.UserDtoResponse;
import com.havryliuk.test.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("""
            select new com.havryliuk.test.users.dto.UserDtoResponse(
            u.id, u.email, u.firstName, u.lastName, u.birthDate, u.phoneNumber, u.address)
            from  User u where u.id = :id
            """)
    Optional<UserDtoResponse> findUserResponseDtoById(String id);
//    Optional<UserDtoResponse> findUserBy(String id, UserDtoResponse.class);
    <T>List<T> findAllBy(Class<T> type);
}
