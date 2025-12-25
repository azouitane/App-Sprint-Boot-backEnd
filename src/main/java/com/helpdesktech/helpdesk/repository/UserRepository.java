package com.helpdesktech.helpdesk.repository;

import com.helpdesktech.helpdesk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByFullName(String fullName);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
