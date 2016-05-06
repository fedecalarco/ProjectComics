/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourcomics.controller;

import com.mycompany.yourcomics.entity.Characters;
import com.mycompany.yourcomics.entity.Comic;
import com.mycompany.yourcomics.entity.Genre;
import com.mycompany.yourcomics.entity.Publisher;
import com.mycompany.yourcomics.service.ComicService;
import com.mycompany.yourcomics.service.UserService;

/**
 *
 * @author federico.calarco
 */
public class Main {

    public static void main(String[] args) {

        // USER
        UserService us = new UserService();
        us.create("fede", "123", "Federico Gaston", "Calarco");
        us.create("pepe", "123", "Homer", "Simpsons");

        // COMICS
        ComicService cm = new ComicService();
        
        // Characters
        Characters batman = new Characters("Batman");
        Characters spiderman = new Characters("Spiderman");
        Characters xmen = new Characters("x-men");
        Characters spawn = new Characters("Spawn");
        Characters creepyComics = new Characters("Creepy comics");
        Characters howToPassAsHumanBeing = new Characters("How to pass as human being");
        
        // Publishers
        Publisher dc = new Publisher("DC");
        Publisher marvel = new Publisher("Marvel");
        Publisher imageComics = new Publisher("Image Comics");
        Publisher darkHorse = new Publisher("Dark Horse");
        
        
        
        Genre superhero = new Genre("Superhero");
        Genre horror = new Genre("Horror");
        Genre nonFiction = new Genre("Non-Fiction");

        Comic comic1 = new Comic("Knightfall", 622, "Batman_Knightfall_Broken_Bat_2002_Edition.jpg", true, 4, "Standard", batman, dc, superhero);
        Comic comic2 = new Comic("Death in the family", 305, "Batman_Death_in_the_Family.jpg", true, 5, "Standard", batman, dc, superhero);
        Comic comic3 = new Comic("Avenging spiderman", 1, "Avenging_SpiderMan_1_Cover.jpg", true, 3, "Standard", spiderman, marvel, superhero);
        Comic comic4 = new Comic("A legend reborn", 1, "X-Men_Vol_2_1_Magneto_Variant.jpg", true, 5, "Collector", xmen, marvel, superhero);
        Comic comic5 = new Comic("Milestone", 100, "2171682-spawn__117__2002_.jpg", false, 3, "Collector", spawn, imageComics, superhero);
        Comic comic6 = new Comic("How to pass as human being", 1, "4802806-04.jpg", true, 2, "Standard", howToPassAsHumanBeing, darkHorse, nonFiction);
        
        cm.create(comic1);
        cm.create(comic2);
        cm.create(comic3);
        cm.create(comic4);
        cm.create(comic5);
        cm.create(comic6);

    }

}
