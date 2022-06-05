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

        String salt = User.generateSalt();
        String hash = User.generateHash("admin123", salt);

        User adminUser = new User("admin", "admin", "admin", hash, salt);
        db.addUser(adminUser);

        boolean isAuth = db.authenticateUser(adminUser, "admin123");
        System.out.println(isAuth);

        GlobalData.currentUserLogin = "admin";

        Database.close();


    }
}
