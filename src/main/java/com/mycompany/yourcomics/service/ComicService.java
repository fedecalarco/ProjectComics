/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourcomics.service;

import com.mycompany.yourcomics.dao.ComicDao;
import com.mycompany.yourcomics.dao.ComicDaoImpl;
import com.mycompany.yourcomics.entity.Characters;
import com.mycompany.yourcomics.entity.Publisher;
import com.mycompany.yourcomics.entity.Comic;
import com.mycompany.yourcomics.entity.Genre;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author federico.calarco
 */
public class ComicService {

    // Pasarlo por ioc spring
    ComicDao comicDao = new ComicDaoImpl();

    public void create(Comic comic) {
        comicDao.create(comic);

    }

    public JSONObject getAll() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "allComics");
        jsonObject.put("allComics", comicDao.getAll());

        return jsonObject;
    }

    public JSONObject getComicById(long id) {

        JSONObject comic = new JSONObject(comicDao.read(id));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "descriptionComic");
        jsonObject.put("comic", comic);

        return jsonObject;
    }

}
