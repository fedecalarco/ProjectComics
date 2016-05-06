/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourcomics.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author fedec
 */
@Entity
public class Comic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private int number;
    private String cover; // img
    private boolean available;
    private int rating;
    private String edition;
    @OneToOne(cascade = {CascadeType.ALL})
    private Characters character;
    @OneToOne(cascade = {CascadeType.ALL})
    private Publisher publisher;
    @OneToOne(cascade = {CascadeType.ALL})
    private Genre genre;

    public Comic() {
    }

    public Comic(String title, int number, String cover, boolean available, int rating, String edition, Characters character, Publisher publisher, Genre genre) {

        this.title = title;
        this.number = number;
        this.cover = cover;
        this.available = available;
        this.rating = rating;
        this.edition = edition;
        this.character = character;
        this.publisher = publisher;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    
    
    
}
