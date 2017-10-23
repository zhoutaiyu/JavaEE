package edu.cn.dao;

import edu.cn.dao.UserDAO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by FZH on 2017/10/12.
 */
@Repository
public class UserDAOImp implements UserDAO {

    @Override
    public boolean findUser(String username) {
        if(username.equals("fzh"))
            return true;
        else
            return false;
    }
}
