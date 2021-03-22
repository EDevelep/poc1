package com.neosoft.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosoft.entity.UserDetail;
import com.neosoft.repo.UserDetailRepo;
import com.neosoft.service.UserDetailService;

@Service
public class UserDetailServiceImpl implements UserDetailService {
	private static final Logger LOG = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	@Autowired
	private UserDetailRepo userDetailRepo;

	@Override
	@Transactional
	public void saveUserDetail(UserDetail userDetail) {
		UserDetail dbuserdetail = userDetailRepo.findUserByemailid(userDetail.getEmailid());
		if (Objects.nonNull(dbuserdetail)) {
			throw new IllegalArgumentException("Email Id is alredy Register");
		}
		// when we are saving the user that time we put user is active
		userDetail.setActive('Y');
		userDetailRepo.save(userDetail);
		LOG.debug("User Detail save ");

	}

	@Override
	@Transactional
	public void editUserDetail(UserDetail userdetail) {

		Optional<UserDetail> dbuserdetail = userDetailRepo.findById(userdetail.getUserid());
		if (Objects.isNull(dbuserdetail) || dbuserdetail.get().getActive() == 'N') {
			throw new IllegalArgumentException(
					"UserDetail  is  Not  Found for Given Id and you account is Decative Plz Contact the support Team");
		}
		UserDetail Uiuserdetail = dbuserdetail.get();
		Uiuserdetail.setActive(userdetail.getActive());
		Uiuserdetail.setEmailid(userdetail.getEmailid());
		Uiuserdetail.setPassword(userdetail.getPassword());
		Uiuserdetail.setSurname(userdetail.getSurname());
		Uiuserdetail.setPincode(userdetail.getPincode());
		Uiuserdetail.setDateofbirth(userdetail.getDateofbirth());
		userDetailRepo.save(Uiuserdetail);
	}

	@Override
	public List<UserDetail> getUserDetailByUserId(Long userid) {

		List<UserDetail> dbUserDetails = userDetailRepo.findUserByuserid(userid);
		if (Objects.isNull(dbUserDetails) || dbUserDetails.get(0).getActive() == 'N') {
			throw new IllegalArgumentException(
					"UserDetail  is  Not  Found for Given Id and you account is Decative Plz Contact the support Team");
		}
		return dbUserDetails;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDetail> search(String keyword) {
		List<UserDetail> userdetail = userDetailRepo.search(keyword);
		if (Objects.isNull(userdetail)) {
			throw new IllegalArgumentException("No Record Found  For given Value");
		}
		return userdetail;
	}

	@Override
	@Transactional
	public void deleteUserById(Long id) {
		UserDetail dbUserDetails = userDetailRepo.findUsersByuserid(id);
		if (Objects.isNull(dbUserDetails)) {
			throw new IllegalArgumentException("UserDetail  is  Not  Found for Given Id");
		}

		// performing soft delete opration on user bacause we dont want delete the user
		dbUserDetails.setActive('N');
		userDetailRepo.save(dbUserDetails);
	}

	@Override
	@Transactional
	public List<UserDetail> searchByDate(Date joiningdate, Date dateofbirth) {
		List<UserDetail> userdetail = userDetailRepo.searchDate(joiningdate,dateofbirth);

		if (Objects.isNull(userdetail)) {
			throw new IllegalArgumentException("UserDetail  is  Not  Found for Given Date");
		}
		return userdetail;
	}

}
