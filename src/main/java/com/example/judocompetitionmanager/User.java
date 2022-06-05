package com.example.judocompetitionmanager;

import java.util.UUID;

public class User {
    private String login;
    private String name;
    private String surname;
    private String passwordHash;
    private String salt;
    private int ID = 0;


    public User(String login, String name, String surname, String passwordHash, String salt){
        this.name = name;
        this.login = login;
        this.surname = surname;
        this.passwordHash = passwordHash;
        this.salt = salt;
    }

    public static String generateHash(String password, String salt){
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
    }
    public static String generateSalt() {
        String uuid = UUID.randomUUID().toString();
        return "uuid = " + uuid;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    public int getID(){
        return this.ID;
    }
    public String getPasswordHash(){
        return this.passwordHash;
    }
    public String getLogin(){
        return this.login;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getSalt(){
        return salt;
    }
}
