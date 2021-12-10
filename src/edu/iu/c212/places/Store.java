package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Store extends Place {
    public Store(Arcade arcade) {
        super("Store", arcade, 0);
    }

    enum StoreAction {
        BUY, SELL, LEAVE;

        @Override
        public String toString() {
            return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }

    @Override
    public void onEnter(User user) throws IOException {
        boolean stayInStore = true;
        List<String> storeActions = new ArrayList<>();

        for (StoreAction action : StoreAction.values()) {
            storeActions.add(action.toString());
        }

        List<String> confirmation = new ArrayList<>();
        confirmation.add("Yes");
        confirmation.add("No");

        while (stayInStore) {
            String userChoice = ConsoleUtils.printMenuToConsole("Store Actions",
                    storeActions, true);

            if (userChoice.equals("Sell")) {
                // Check if user has an empty inventory
                if (user.getInventory().size() == 0) {
                    System.out.println("You don't have anything to sell.");
                    continue;
                }

                // If the user doesn't have an empty inventory
                List<String> userOwnedObjects = new ArrayList<>();
                for (Item i : user.getInventory()) {
                    userOwnedObjects.add(i.getReadableName());
                }

                String whatObjectToSell = ConsoleUtils.printMenuToConsole("What would you like to sell? " +
                                "Remember you can only get 50% of your value back!", userOwnedObjects,
                        true);

                String saleConfirmation = ConsoleUtils.printMenuToConsole("Are you sure you want to sell " +
                        whatObjectToSell + "?", confirmation, true);

                if (saleConfirmation.equals("Yes")) {
                    List<Item> tempInventory = new ArrayList<>();
                    boolean soldOnce = false;
                    for (Item i : user.getInventory()) {
                        if (i.getReadableName().equals(whatObjectToSell) && !soldOnce) {
                            soldOnce = true;
                        } else {
                            tempInventory.add(i);
                        }
                    }
                    user.setInventory(tempInventory);
                    for (Item i : Item.values()) {
                        if (i.getReadableName().equals(whatObjectToSell)) {
                            user.setBalance(user.getBalance() + (i.getValue() / 2));
                            break;
                        }
                    }
                    arcade.saveUsersToFile();
                } else {
                    System.out.println("You did not confirm the sell.");
                }

            } else if (userChoice.equals("Buy")) {
                if (user.getInventory().size() == 3) {
                    System.out.println("You don't have any more space in your inventory to buy new items.");
                    continue;
                }

                List<String> itemOptions = new ArrayList<>();
                for (Item i : Item.values()) {
                    itemOptions.add(i.toString());
                }

                String whatObjectToBuy = ConsoleUtils.printMenuToConsole("What item would you like to buy?",
                        itemOptions, true);

                for (Item ii : Item.values()) {
                    if (ii.toString().equals(whatObjectToBuy)) {
                        whatObjectToBuy = ii.getReadableName();
                    }
                }

                // Check if user has enough money to buy item
                boolean canAfford = false;
                Item toBeBought = null;
                for (Item i : Item.values()) {
                    if (i.getReadableName().equals(whatObjectToBuy)) {
                        if (user.getBalance() >= i.getValue()) {
                            canAfford = true;
                            toBeBought = i;
                        }
                        break;
                    }
                }
                if (!canAfford) {
                    System.out.println("You cannot afford this item.");
                    continue;
                }

                String buyConfirmation = ConsoleUtils.printMenuToConsole("Are you sure you want to buy " +
                        whatObjectToBuy + "?", confirmation, true);

                if (buyConfirmation.equals("Yes")) {
                    user.setBalance(user.getBalance() - toBeBought.getValue());
                    List<Item> tempItems = new ArrayList<>(user.getInventory());
                    tempItems.add(toBeBought);
                    user.setInventory(tempItems);
                    arcade.saveUsersToFile();
                } else {
                    System.out.println("You did not confirm the purchase.");
                }

            } else {
                stayInStore = false;
                System.out.println("Going back to the Lobby!");
            }
        }
    }
}
