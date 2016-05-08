/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourcomics.service;

import com.mycompany.yourcomics.dao.ComicDao;
import com.mycompany.yourcomics.dao.ComicDaoImpl;
import com.mycompany.yourcomics.entity.Comic;
import org.json.JSONObject;

/**
 *
 * @author federico.calarco
 */
public class ComicService {

    ComicDao comicDao = new ComicDaoImpl();
    JSONObject jsonObject;

    public void create(Comic comic) {
        comicDao.create(comic);
    }

    public JSONObject getAll() {

        jsonObject = new JSONObject();
        jsonObject.put("type", "allComics");
        jsonObject.put("allComics", comicDao.getAll());

        return jsonObject;
    }

    public JSONObject getComicById(long id) {

        jsonObject = new JSONObject();
        jsonObject.put("type", "descriptionComic");
        jsonObject.put("comic", new JSONObject(comicDao.read(id)));

        return jsonObject;
    }

    public JSONObject getComicFilter(String filter) {
        
        
        
        jsonObject = new JSONObject();
        jsonObject.put("type", "allComics");
        jsonObject.put("allComics", comicDao.getFilter(filter));
        
        
        
        return jsonObject;
    }

}
