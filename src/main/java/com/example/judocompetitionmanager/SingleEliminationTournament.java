package com.example.judocompetitionmanager;

import java.util.ArrayList;
import java.util.Random;

public class SingleEliminationTournament {

    //konstruktor z pobraną z bazy listą zaowdników

    public static void main(String[] args) {
        ArrayList<Contestant> contestants0 = new ArrayList<Contestant>();
        ArrayList<Contestant> contestants = new ArrayList<Contestant>();
        ArrayList<Contestant> winners = new ArrayList<Contestant>();
        ArrayList lista = new ArrayList();

        // inicjalizacja listy testowej
        contestants0.add(new Contestant("Kyle", "M", 8, 50.0d, false));
        contestants0.add(new Contestant("Malwina", "K", 12, 35.0d, false));
        contestants0.add(new Contestant ("Edzia", "M", 11, 60.0d, false));
        contestants0.add(new Contestant("Karl", "W", 10, 95.0d, true));
        contestants0.add(new Contestant("Jack", "F", 9, 44.0d, true));
        contestants0.add(new Contestant ("Thomas", "G", 14, 50.0d, true));
        contestants0.add(new Contestant("Mikasa", "C", 15, 45.0d, false));

        //--------------PRZEMIESZANIE LISTY / LOSOWANIE---------------
        Random rand = new Random();
        int size = contestants0.size();
        for (int i = 0; i < size; i++){
            int x = rand.nextInt(contestants0.size());
            //System.out.println(x + "x");
            if (contestants.contains(contestants0.get(x))) {
                System.out.println("contains");
                contestants0.remove(x);
            }else{
                contestants.add(contestants0.get(x));
                contestants0.remove(x);
            }
        }
        System.out.println("---------------------");
        for(int i = 0; i < contestants.size(); i++){
            System.out.println(contestants.get(i).getName() + " " + contestants.get(i).getWeightCategory());
        }

        //----------------------------------------
        //wylosowane pary:
        for(int i = 0; i < contestants.size(); i = i+2){
            int id = 0 + i/2;
            if (contestants.size() %2 == 0){
                Contestant c1 = contestants.get(i);
                Contestant c2 = contestants.get(i+1);
                System.out.println("para " + id + " : " + c1.getName() + " " + c2.getName());
            }else{
                if (i == contestants.size()-1){
                    Contestant c1 = contestants.get(i);
                    System.out.println("wolny los " + id + " : " + c1.getName());
                }else{
                    Contestant c1 = contestants.get(i);
                    Contestant c2 = contestants.get(i+1);
                    System.out.println("para " + id + " : " + c1.getName() + " " + c2.getName());
                }
            }

        }




    }

}
