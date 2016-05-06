package com.mycompany.yourcomics.controller;

import com.mycompany.yourcomics.service.ComicService;
import com.mycompany.yourcomics.service.UserService;
import java.io.IOException;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONObject;

/**
 *
 * @author federico.calarco
 */
@ServerEndpoint(
        value = "/WS"
)
public class WebSocket {

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("WebSocket opened: " + session.getId());
        getAllComics(session);

    }

    @OnMessage
    public void onMessage(String txt, Session session) throws IOException, EncodeException {

        System.out.println("Message received: " + txt);

        JSONObject jObj = new JSONObject(txt);

        String type = jObj.get("type").toString();
        switch (type) {
            case "login":
                login(session, jObj);
                break;
            case "getAllComics":
                getAllComics(session);
                break;
            case "getComic":
                getComic(session, jObj.getLong("id"));
                break;
            default:
                System.out.println("default");

        }

    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase() + " ID: " + session.getId());

    }

    @OnError
    public void onError(Throwable t) {
    }

    public void login(Session session, JSONObject userJson) throws IOException {

        UserService userService = new UserService();

        JSONObject user = userService.read(userJson);

        session.getBasicRemote().sendText(user.toString());

    }
    
    
    
    
    // - Clase nueva -
    ComicService comicService = new ComicService();

    public void getAllComics(Session session) throws IOException {

        System.out.println("Get All Comics method");
        

      //  JSONArray allComics = comicService.getAll();
        
        JSONObject jObj = comicService.getAll();
        session.getBasicRemote().sendText(jObj.toString());

    }
    
    public void getComic(Session session,long id) throws IOException{
        
        JSONObject jObj = comicService.getComicById(id);
        session.getBasicRemote().sendText(jObj.toString());

    }

}
