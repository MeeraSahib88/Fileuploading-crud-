package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.UserDeatils;
@Repository
public interface UserRepo extends JpaRepository<UserDeatils, String> {

}
