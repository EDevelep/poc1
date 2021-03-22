package com.neosoft.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neosoft.entity.UserDetail;

@Repository
public interface UserDetailRepo extends JpaRepository<UserDetail, Long> {

	UserDetail findUserByemailid(String emailid);

	UserDetail findUsersByuserid(Long userid);

	List<UserDetail> findUserByuserid(Long userid);

	@Query("SELECT u FROM UserDetail u WHERE CONCAT(u.username, u.surname,  u.pincode) LIKE %?1%")
	List<UserDetail> search(String keyword);

	@Query("select u  from UserDetail u order by joiningdate,dateofbirth")
	List<UserDetail> searchDate(Date joiningdate, Date dateofbirth);

	// List<UserDetail> searchDateBetweenjoiningdateAnddateofbirth(Date date);

}
