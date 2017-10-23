package edu.cn.service;

import edu.cn.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by FZH on 2017/10/12.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean login(String username) {
        if(userDAO.findUser(username))
            return true;
        else
            return false;
    }
}
