package com.service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.user.model.user;
@Repository
public interface UserRepo extends JpaRepository<user, Integer>{

}
