/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourcomics.dao;

import com.mycompany.yourcomics.entity.User;

/**
 *
 * @author fedec
 */
public interface UserDao {
    void create(User user);
    User read(Long id);
    void update(User user);
    void delete(User user);
    
    User login(String username,String password);
}
