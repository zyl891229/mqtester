package service;

import pojo.User;

public interface IUserService {
	public User getUserById(int userId);

	public int insertUser(User User);

	User[] getUser();

}