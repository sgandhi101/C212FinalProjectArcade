package edu.iu.c212.places.games;

import edu.iu.c212.Arcade;
import edu.iu.c212.places.Place;


public abstract class Game extends Place {
    public Game(String placeName, Arcade arcade, double entryFee) {
        super(placeName, arcade, entryFee);
    }
}

