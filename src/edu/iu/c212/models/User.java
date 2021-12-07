package edu.iu.c212.models;

import java.util.List;

public class User {
    String username;
    double balance;
    List<Item> inventory;

    public User(String username, double balance, List<Item> inventory) {
        this.username = username;
        this.balance = balance;
        this.inventory = inventory;
    }

    public String getUsername() {
        return this.username;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Item> getInventory() {
        return this.inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
}