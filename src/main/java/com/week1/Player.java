package com.week1;

public class Player {
    private String name;

    public Player(String name ) { // Constructor for creating a new player
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }
    @Override
    public String toString() {
        return name;
    }
}
