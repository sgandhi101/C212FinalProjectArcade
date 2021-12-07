package edu.iu.c212;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;

import java.io.IOException;
import java.util.List;

public interface IArcade {
    List<User> getUserSaveDataFromFile();

    void saveUsersToFile() throws IOException;

    void transitionArcadeState(String newPlaceNameToGoTo) throws IOException;

    User getUserOnArcadeEntry() throws IOException;

    List<Place> getAllPlaces();
}
