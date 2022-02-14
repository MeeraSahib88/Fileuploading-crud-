package com.example.demo.ServiecImp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dto.MessageResponse;
import com.example.demo.Dto.UserDeatislDto;
import com.example.demo.Model.UserDeatils;
import com.example.demo.Repository.UserRepo;
import com.example.demo.Serviecs.UserDetailserviecs;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiecImpl implements UserDetailserviecs {

	@Autowired
	UserRepo userRepo;
	@Autowired
	Environment environment;

	@Override
	public ResponseEntity<?> insertUserDetail(String userrequest, MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserDeatislDto userDeatislDto = null;
			try {
				userDeatislDto = mapper.readValue(userrequest, UserDeatislDto.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UserDeatils deatils = UserDeatils.builder().email(userDeatislDto.getEmail()).name(userDeatislDto.getName())
					.itemImage(file.getBytes()).phonenumber(userDeatislDto.getPhonenumber()).build();
			userRepo.save(deatils);
			return ResponseEntity
					.ok(new MessageResponse(HttpStatus.OK.value(), environment.getProperty("success.store"), deatils));
		} catch (Exception e) {
			return ResponseEntity
					.ok(new MessageResponse(HttpStatus.BAD_REQUEST.value(), environment.getProperty("failed")));
		}
	}

	@Override
	public ResponseEntity<?> getByUser(String userId) {
		List<UserDeatils> userDeatils= userRepo.findAll();
		if (userDeatils.isEmpty()) {
			return ResponseEntity
					.ok(new MessageResponse(HttpStatus.BAD_REQUEST.value(), environment.getProperty("failed.fetched")));
		}
		return ResponseEntity
				.ok(new MessageResponse(HttpStatus.OK.value(), environment.getProperty("success.fetched"), userDeatils));
	}

	@Override
	public ResponseEntity<?> updateUserDetalis(MultipartFile multipartImage, String id, BigDecimal phonenumber,
			String email,String name) {
		try {
			Optional<UserDeatils> optional = userRepo.findById(id);
			if (!optional.isPresent())
				return ResponseEntity.ok(MessageResponse.builder().message("id should be valid")
						.status(HttpStatus.PARTIAL_CONTENT.value()).build());

			UserDeatils userDeatils = optional.get();
			if (email != null) {
				userDeatils.setEmail(email);
			}
			if (phonenumber != null) {
				userDeatils.setPhonenumber(phonenumber);
			}
			if (name != null) {
				userDeatils.setName(name);
			}
			

			if (multipartImage != null) {
				userDeatils.setItemImage(multipartImage.getBytes());
			}
		
			userDeatils = userRepo.save(userDeatils);

			return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(),
					environment.getProperty("success.update"), userDeatils));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(HttpStatus.BAD_REQUEST.value(),
					environment.getProperty("failed.update")));
		}
	}

	@Override
	public ResponseEntity<?> deleteUser(String id) {
		try {
			userRepo.deleteById(id);
		} catch (Exception e) {
			return ResponseEntity
					.ok(new MessageResponse(HttpStatus.BAD_REQUEST.value(), environment.getProperty("failed.Delete")));
		}
		return ResponseEntity.ok(new MessageResponse(HttpStatus.OK.value(), environment.getProperty("success.Delete"), null));
	}

	
}
