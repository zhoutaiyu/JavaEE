package edu.cn.dao;

/**
 * Created by 周太宇 on 2017/10/12.
 */
public interface UserDao {
    /**
     * 根据用户名查找是否存在此用户
     * @param username
     * @return
     */
    public Boolean findUser(String username);
}
