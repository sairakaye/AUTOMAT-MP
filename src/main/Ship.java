package main;

import java.util.ArrayList;

public class Ship {

    private ArrayList<String> passengers;
    private boolean isOnEarth;
    private Position curAt;

    public Ship(){
        curAt = Position.EARTH;
        passengers = new ArrayList<>();
    }

    public void addToShip(ArrayList<String> passengers){
        this.passengers = passengers;
    }

    public void transport(){
        if (isOnEarth){
            curAt = Position.MARS;
        } else {
            curAt = Position.EARTH;
        }
    }
}
