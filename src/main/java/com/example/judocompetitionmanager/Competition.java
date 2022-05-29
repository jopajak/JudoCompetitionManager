package com.example.judocompetitionmanager;

public class Competition {
    private String name;
    private String startDate;
    private String endDate;
    private String competitionWebsite;
    private String organizer;
    private String address;

    public Competition(String name, String startDate, String endDate, String competitionWebsite, String organizer, String address){
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.competitionWebsite = competitionWebsite;
        this.organizer = organizer;
        this.address = address;
    }

    public String getName(){
        return name;
    }
    public String getStartDate(){
        return startDate;
    }
    public String getEndDate(){
        return endDate;
    }
    public String getCompetitionWebsite(){
        return competitionWebsite;
    }
    public String getOrganizer(){
        return organizer;
    }
    public String getAddress(){
        return address;
    }

}
