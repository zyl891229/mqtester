package service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.IUserDao;
import pojo.User;
import service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public User[] getUser() {
		// TODO Auto-generated method stub
		return this.userDao.select();
	}

	@Override
	public int insertUser(User User) {
		// TODO Auto-generated method stub
		return this.userDao.insert(User);
	}

}