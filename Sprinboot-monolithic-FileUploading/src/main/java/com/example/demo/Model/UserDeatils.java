package com.example.demo.Model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_fileupload")
public class UserDeatils {
	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	private String Id;
	
	private String name;
	
	private BigDecimal phonenumber;
	
	private String email;
	
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] itemImage;
	

}
