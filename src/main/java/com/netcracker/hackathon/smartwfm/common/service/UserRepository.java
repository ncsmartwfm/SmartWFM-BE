package com.netcracker.hackathon.smartwfm.common.service;

import com.netcracker.hackathon.smartwfm.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUserName(String userName);
}
