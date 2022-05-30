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

        JSONObject jo = db.jsonify_contestant(list);
        JSONObject jox = db.jsonify_contestant(list);

        competitors.put("Competitor1", jo);
        competitors.put("CompetitorKuba", jox);

        db.writeJsonObject("competitors", competitors);

        JSONObject jo2 = db.readJsonObject("competitors");
        System.out.println(jo2);
        List list23 = db.getCompetitorsList();

        System.out.println(list23);




        Contestant kuba = new Contestant("Kuba", "Dober", 25, 60, true);
        db.addContestant(kuba);

        Competition com1 = new Competition("Com1", "24.05.2021", "25.05.2021", "www", "krakowska", "rusz");
        db.addCompetition(com1);

        Contestant kuba2 = db.getContestant("Kuba");
        System.out.println(kuba2.getSurname());
        Database.close();

    }
}
