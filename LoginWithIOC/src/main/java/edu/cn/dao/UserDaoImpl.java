package edu.cn.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by 周太宇 on 2017/10/12.
 */
@Repository
public class UserDaoImpl implements UserDao {
    /**
     * 根据用户名查找是否存在此用户
     * @param username
     * @return
     */
    @Override
    public Boolean findUser(String username) {
        if ("zty".equals(username)){
            return true;
        }
        else return false;
    }
}
