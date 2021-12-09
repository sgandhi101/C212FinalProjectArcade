package edu.iu.c212.utils;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    private static File file = new File("/Users/sunny/Documents/GitHub/" +
            "C212FinalProjectArcade Starter Code/src/users.txt");

    // line format:
    // user_name|balance|item1,item2,item3
    // user name not allowed to contain pipe

    /**
     * Write user data to the file you provided above.
     *
     * @param users The total list of all users
     */
    public static void writeUserDataToFile(List<User> users) throws IOException {
        File temp = new File("temp.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

        for (User u : users) {
            List<Item> tempItemsList = u.getInventory();
            String tempItemsStringList = "";
            if (tempItemsList.size() != 0) {
                for (Item i : tempItemsList) {
                    tempItemsStringList += i.getReadableName() + ",";
                }
            } else {
                tempItemsStringList = ",";
            }
            writer.write(u.getUsername() + "|" + u.getBalance() + "|" + tempItemsStringList + "\n");
        }

        writer.close();
        temp.renameTo(file);
    }

    /**
     * Read user data from the file you provided above. Return a list of Users
     */
    public static List<User> getUserDataFromFile() throws IOException {
        Scanner sc = new Scanner(file);
        List<User> userList = new ArrayList<User>();
        String[] tempUser = new String[2];
        while (sc.hasNextLine()) {
            tempUser = sc.nextLine().split("\\|");
            List<Item> tempItemList = new ArrayList<Item>();

            for (String x : tempUser[2].split(",")) {
                for (Item y : Item.values()) {
                    if (y.getReadableName().equals(x)) {
                        tempItemList.add(y);
                        break;
                    }
                }
            }
            userList.add(new User(tempUser[0], Float.parseFloat(tempUser[1]), tempItemList));
        }
        return userList;
    }
}