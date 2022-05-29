package com.example.judocompetitionmanager;

public class Contestant {
    private String name;
    private String surname;
    private int age;
    private double weight;
    private Boolean sex;              // sex, female = false, male = true
    private String weightCategory;
    private int points;

    public Contestant(String name, String surname, int age, double weight, Boolean sex) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
        assignToWeightCat(weight);
    }

    @Override
    public String toString() {
        return  name + " " +
                surname + "   " +
                //weight + "kg " +
                //sex + " " +
                //"age: " +
                age + " " +
                weightCategory + ' ' +
                ", points=" + points + " ";
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    private void assignToWeightCat(Double weight){
        if(sex == true) {
            if(weight < 60) {
                weightCategory = "Extra Lightweight";
            }else if (weight < 66 && weight >= 60){
                weightCategory = "Half Lightweight";
            }else if (weight < 73 && weight >= 66){
                weightCategory = "Lightweight";
            }else if (weight < 81 && weight >= 73){
                weightCategory = "Half Middleweight";
            }else if (weight < 90 && weight >= 81){
                weightCategory = "Half Heavyweight";
            }else if (weight < 100 && weight >= 90){
                weightCategory = "Heavyweight";
            }else {
                weightCategory = "Open weight";
            }
        } else {
            if(weight < 48) {
                weightCategory = "Extra Lightweight";
            }else if (weight < 52 && weight >= 48){
                weightCategory = "Half Lightweight";
            }else if (weight < 57 && weight >= 52){
                weightCategory = "Lightweight";
            }else if (weight < 63 && weight >= 57){
                weightCategory = "Half Middleweight";
            }else if (weight < 70 && weight >= 63){
                weightCategory = "Half Heavyweight";
            }else if (weight < 78 && weight >= 70){
                weightCategory = "Heavyweight";
            }else {
                weightCategory = "Open weight";
            }
        }



    }

    public Boolean getSex() {
        return sex;
    }

    public String getSexString() {
        if (this.getSex() == true){
            return "male";
        }else {
            return "female";
        }
    }

    public double getWeight() {
        return weight;
    }

    public String getWeightCategory() {
        return weightCategory;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
