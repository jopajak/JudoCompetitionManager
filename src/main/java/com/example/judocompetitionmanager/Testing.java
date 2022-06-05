package com.example.judocompetitionmanager;

import org.iq80.leveldb.DB;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.hash;

public class Testing {
    public static void main(String[] args) throws JSONException {

        Database db = Database.getInstance();
        JSONObject jsonObj = new JSONObject();
        db.writeJsonObject("Contestants", jsonObj);

        JSONObject competitors = db.readJsonObject("Contestants");

//        List<String> list = Arrays.asList("Kuba", "Woje", "12", "78", "true", "M", "2");
//        List<String> list2 = Arrays.asList("Kuba1", "Woje2", "123", "78", "true", "M", "0");
//        List<String> list3 = Arrays.asList("Kub", "Woje2", "123", "78", "true", "M", "32");
//
//        JSONObject jo = db.jsonify_contestant(list);
//        JSONObject jox = db.jsonify_contestant(list2);
//        JSONObject jox2 = db.jsonify_contestant(list3);

//        competitors.put("Competitor1", jo);
//        competitors.put("CompetitorKuba", jox);
//        competitors.put("jatest", jox2);

//        db.writeJsonObject("Contestants", competitors);

        int hash1 = hash("kuba01324");
        int hash2 = hash("amrnacja");
        int hash3 = hash("123");

        db.addContestant(new Contestant("Kuba", "Kor1", 42, 82, true, 5, hash1));
        db.addContestant(new Contestant("Kuba", "Kor", 52, 48, false, 0, hash2));
        db.addContestant(new Contestant("Marian", "Zapalka", 22, 68, false, 2, hash3));
        db.addContestant(new Contestant("Radoslaw", "Maciwoda", 19, 92, true, 15, 4256));
        JSONObject jo2 = db.readJsonObject("Contestants");

        boolean isAuthenticated = db.authenticate("Kuba Kor", "amrnacja");
        System.out.println("----------");
        System.out.println(isAuthenticated);
        System.out.println("----------");




        Database.close();

    }
}
