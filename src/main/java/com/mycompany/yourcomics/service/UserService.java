package com.mycompany.yourcomics.service;

import com.mycompany.yourcomics.dao.UserDao;
import com.mycompany.yourcomics.dao.UserDaoImpl;
import com.mycompany.yourcomics.entity.User;
import javax.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 *
 * @author fedec
 */
@Service
@Transactional
public class UserService {

    UserDao userDao = new UserDaoImpl();

    public JSONObject read(JSONObject userJson) {

        User user = userDao.login(userJson.getString("username"), userJson.getString("password"));

        // Carga los datos del User en el JSON 
        if (user != null) {
            userJson.put("ok", "true");
            userJson.put("id", user.getId());
            userJson.put("firstName", user.getFirstName());
            userJson.put("lastName", user.getLastName());
        } else {
            userJson.put("ok", "false");
        }
        return userJson;

    }

    public void create(String username, String password, String firstName, String lastName) {

        User user = new User(username, password, firstName, lastName);

        UserDao ud = new UserDaoImpl();

        ud.create(user);

    }

}
