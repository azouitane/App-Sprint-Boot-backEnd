package com.helpdesktech.helpdesk.repository;

import com.helpdesktech.helpdesk.entity.User;
import com.helpdesktech.helpdesk.enums.User.UserRole;
import com.helpdesktech.helpdesk.enums.User.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByFullName(String fullName);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByStatus(UserStatus userStatus);
    long countByStatus(UserStatus userStatus);
    long countByRole(UserRole userRole);
}
