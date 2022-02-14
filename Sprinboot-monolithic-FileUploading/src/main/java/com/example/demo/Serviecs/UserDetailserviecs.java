package com.example.demo.Serviecs;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserDetailserviecs {

	ResponseEntity<?> insertUserDetail(String userrequest, MultipartFile file);

	ResponseEntity<?> getByUser(String userId);

	ResponseEntity<?> updateUserDetalis(MultipartFile multipartImage, String id, BigDecimal phonenumber, String email,String name);

	ResponseEntity<?> deleteUser(String id);

}
