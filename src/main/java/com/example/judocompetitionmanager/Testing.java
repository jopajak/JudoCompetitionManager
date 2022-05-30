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
        db.writeJsonObject("competitors", jsonObj);

        JSONObject competitors = db.readJsonObject("competitors");

        List<String> list = Arrays.asList("Kuba", "Woje", "12", "78", "true", "M");
        List<String> list2 = Arrays.asList("Kuba1", "Woje2", "123", "78", "true", "M");
        List<String> list3 = Arrays.asList("Kub", "Woje2", "123", "78", "true", "M");

        JSONObject jo = db.jsonify_contestant(list);
        JSONObject jox = db.jsonify_contestant(list2);

        competitors.put("Competitor1", jo);
        competitors.put("CompetitorKuba", jox);

        db.writeJsonObject("competitors", competitors);

        JSONObject jo2 = db.readJsonObject("competitors");
        System.out.println(jo2);
        List competitorsList = db.getCompetitorsList();
        System.out.println(competitorsList);


        Database.close();

    }
}
