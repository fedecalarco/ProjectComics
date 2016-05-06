/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourcomics.dao;

import com.mycompany.yourcomics.entity.Comic;
import java.util.List;

/**
 *
 * @author fedec
 */
public interface ComicDao {
    void create(Comic comic);
    Comic read(Long id);
    void update(Comic comic);
    void delete(Comic comic);
    
    List<Comic> getAll();
    
}
