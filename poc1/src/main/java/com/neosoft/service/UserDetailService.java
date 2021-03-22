package com.neosoft.service;

import java.util.Date;
import java.util.List;

import com.neosoft.entity.UserDetail;

public interface UserDetailService {
	void saveUserDetail(UserDetail userDetail);

	void editUserDetail(UserDetail userdetail);
	List<UserDetail> search(String keyword);
	List<UserDetail> getUserDetailByUserId(Long userid);
	List<UserDetail> searchByDate(Date joiningdate, Date dateofbirth );
	void deleteUserById(Long id);

}
