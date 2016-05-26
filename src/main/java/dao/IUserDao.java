package dao;

import pojo.User;


public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

	User[] select();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}