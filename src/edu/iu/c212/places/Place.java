package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

import java.io.IOException;

public abstract class Place {
    String placeName;
    Arcade arcade;
    double entryFee;

    public Place(String placeName, Arcade arcade, double entryFee) {
        this.placeName = placeName;
        this.arcade = arcade;
        this.entryFee = entryFee;
    }

    public abstract void onEnter(User user) throws IOException;

    public String getPlaceName() {
        return this.placeName;
    }

    public double getEntryFee() {
        return this.entryFee;
    }

    @Override
    public String toString() {
        return "Place Name: " + placeName + " (Cost: $" + entryFee + ") Game?:" + "no";
    }
}