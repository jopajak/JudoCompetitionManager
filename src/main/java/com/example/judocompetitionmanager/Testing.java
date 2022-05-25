package com.example.judocompetitionmanager;

import org.iq80.leveldb.DB;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Testing {
    public static void main(String[] args) throws JSONException {
        Database db = Database.getInstance();
        db.writeString("user1", "Jakub Dobosz");
        db.writeString("user2", "Marian Kowal");

        System.out.println(db.readString("user2"));

        List<String> list=new ArrayList<String>();
        list.add("AGH Krakow");
        list.add("12");
        list.add("hard");
        list.add("AGH Krakow");
        list.add("12");
        list.add("hard");

        JSONObject jo = db.jsonify_competition(list);
        db.writeJsonObject("Zawody1", jo);
        JSONObject jo2 = db.readJsonObject("Zawody1");
        String name = jo2.getString("organizer");
        System.out.println(name);

        Database.close();

    }
}
