package com.example.soccerapps;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ModelMovieRealm extends RealmObject{
   @PrimaryKey
    private String Team;
    private String FormedYear;
    private String TeamBadge;
    private String Website;
    private String Facebook;
    private String Twitter;
    private String Instagram;
    private String DescriptionEN;
    private String Sport;
    private String Country;
    private Integer idTeam;

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public String getFormedYear() {
        return FormedYear;
    }

    public void setFormedYear(String formedYear) {
        FormedYear = formedYear;
    }

    public String getTeamBadge() {
        return TeamBadge;
    }

    public void setTeamBadge(String teamBadge) {
        TeamBadge = teamBadge;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getTwitter() {
        return Twitter;
    }

    public void setTwitter(String twitter) {
        Twitter = twitter;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getDescriptionEN() {
        return DescriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        DescriptionEN = descriptionEN;
    }

    public String getSport() {
        return Sport;
    }

    public void setSport(String sport) {
        Sport = sport;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }


    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }
}
