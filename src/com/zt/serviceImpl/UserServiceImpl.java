package com.zt.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zt.model.User;
import com.zt.mybatis.dao.UserDAO;
import com.zt.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDAO userDAO;
	
	public User findUserByUser_name(String user_name) {
		return userDAO.findUserByUser_name(user_name);
	}

	@Override
	public List<User> getUserList() {
		return userDAO.getUserList();
	}

	@Override
	public void delete(int id) {
		userDAO.delete(id);
	}

}
