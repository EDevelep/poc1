package com.neosoft.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.entity.UserDetail;
import com.neosoft.enums.Response;
import com.neosoft.enums.StatusEnum;
import com.neosoft.exception.UserDetailServerException;
import com.neosoft.service.UserDetailService;

import io.swagger.annotations.Api;

//@Api(value = "UserDetailController", tags = { "UserDetail Controller" })
@RestController
@RequestMapping("/api")
public class UserDetailController {

	@Autowired
	private UserDetailService userDetailService;

	@PostMapping(value = "/register")
	public ResponseEntity<?> registerNewDiagnosticcenter(@RequestBody UserDetail userDetail) {
		try {
			userDetailService.saveUserDetail(userDetail);
			return new ResponseEntity<>(("UserDetail data saved successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new UserDetailServerException(e.getMessage(), e);
		}

	}

	@PutMapping(value = "/updateuser")
	public ResponseEntity<?> updateUserDetails(@RequestBody UserDetail userDetail) {
		try {
			userDetailService.editUserDetail(userDetail);
			return new ResponseEntity<>(("UserDetail data updated successfully"), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
		// writting custom exception
		catch (Exception e) {
			throw new UserDetailServerException(e.getMessage(), e);
		}
	}

	@GetMapping(value = "/get/{userid}")
	public ResponseEntity<Response<List<UserDetail>>> findAllUserDetails(@PathVariable Long userid) {
		try {
			List<UserDetail> userdetail = userDetailService.getUserDetailByUserId(userid);
			return new ResponseEntity<>(new Response<>(userdetail, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new UserDetailServerException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Response<String>> deleteUser(@PathVariable Long userId) {
		try {
			userDetailService.deleteUserById(userId);
			return new ResponseEntity<>(new Response<>(null, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new UserDetailServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/search/{value}")
	public ResponseEntity<Response<List<UserDetail>>> serchUser(@PathVariable String value) {
		try {
			
			//we can pass the dto also
			List<UserDetail> userdetail = userDetailService.search(value);
			return new ResponseEntity<>(new Response<>(userdetail, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new UserDetailServerException(e.getMessage(), e);
		}

	}

	@PostMapping("/seasearchbydaterchdate/{joiningdate}/{dateofbirth}")
	public ResponseEntity<Response<List<UserDetail>>> searchByDate(@PathVariable Date joiningdate,
			@PathVariable Date dateofbirth) {
		try {
			List<UserDetail> userdetail = userDetailService.searchByDate(joiningdate, dateofbirth);
			return new ResponseEntity<>(new Response<>(userdetail, StatusEnum.SUCCESS, null), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(new Response<>(null, StatusEnum.FAILURE, e.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new UserDetailServerException(e.getMessage(), e);
		}

	}

}
