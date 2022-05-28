package com.example.judocompetitionmanager;

import org.iq80.leveldb.DB;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Testing {
    public static void main(String[] args) throws JSONException {

        Database db = Database.getInstance();
        JSONObject jsonObj = new JSONObject();
        db.writeJsonObject("Competitions", jsonObj);

        JSONObject competitions = db.readJsonObject("Competitions");

        List<String> list = Arrays.asList("KT", "ad", "dawed", "mad", "kot", "dawe");
        List<String> list2 = Arrays.asList("AGH", "14", "medium", "Krk", "23", "Judo");

        JSONObject jo = db.jsonify_competition(list);
        JSONObject jox = db.jsonify_competition(list);

        competitions.put("Competition1", jo);
        competitions.put("Competition2", jox);

        db.writeJsonObject("Competitions", competitions);

        JSONObject jo2 = db.readJsonObject("Competitions");

        String name = jo2.getJSONObject("Competition1").getString("name");
        System.out.println(name);

        Database.close();

    }
}
