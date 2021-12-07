package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Inventory extends Place {
    public Inventory(Arcade arcade) {
        super("Inventory", arcade, 0);
    }

    @Override
    public void onEnter(User user) throws IOException {
        System.out.println("Hi " + user.getUsername() + "! Your inventory looks like this:");
        HashMap<Item, Integer> tempUserItemList = new HashMap<>();

        // Look for duplicates
        for (Item i : user.getInventory()) {
            if (!tempUserItemList.containsKey(i)) {
                tempUserItemList.put(i, 1);
            } else {
                tempUserItemList.put(i, tempUserItemList.get(i) + 1);
            }
        }

        // Print out Inventory
        double totalNetWorth = user.getBalance();
        for (Map.Entry<Item, Integer> items : tempUserItemList.entrySet()) {
            double tempValue = items.getKey().getValue() * items.getValue();
            System.out.println(items.getKey().getReadableName() + ": " + items.getValue() + " (Value: $" +
                    tempValue + ")");
            totalNetWorth += tempValue;
        }
        if (user.getInventory().size() == 0) {
            System.out.println("Your inventory is empty!");
        }

        System.out.println("Total Net Worth: " + totalNetWorth);

        if (user.getInventory().size() == 3) {
            System.out.println("REMEMBER! You can only have 3 items at a time. Sell one by going to the Store.");
        }

        arcade.transitionArcadeState("Lobby");
    }
}
