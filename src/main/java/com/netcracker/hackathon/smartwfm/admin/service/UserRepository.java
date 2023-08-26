package com.netcracker.hackathon.smartwfm.admin.service;

import com.netcracker.hackathon.smartwfm.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserName(String userName);
}
