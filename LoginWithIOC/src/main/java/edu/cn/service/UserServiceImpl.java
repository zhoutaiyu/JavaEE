package edu.cn.service;

import edu.cn.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * Created by 周太宇 on 2017/10/12.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 业务层功能：登录
     *
     * @param username
     * @return
     */
    @Override
    public Boolean login(String username) {
        if (userDao.findUser(username))
            return true;
        else
            return false;
    }
}
