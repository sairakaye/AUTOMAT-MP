package main;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Position shipAt; //ship is at
    private int nPersonEarth;
    private int nLionEarth;
    private int nCowEarth;
    private int nGrainEarth;
    private int nPersonMars;
    private int nLionMars;
    private int nCowMars;
    private int nGrainMars;
    private Node parent;

    public Node(int nPersonEarth, int nLionEarth, int nCowEarth, int nGrainEarth, Position shipAt,
                int nPersonMars, int nLionMars, int nCowMars, int nGrainMars){
        this.nPersonEarth = nPersonEarth;
        this.nLionEarth = nLionEarth;
        this.nCowEarth = nCowEarth;
        this.nGrainEarth = nGrainEarth;
        this.shipAt = shipAt;
        this.nPersonMars = nPersonMars;
        this.nLionMars = nLionMars;
        this.nCowMars = nCowMars;
        this.nGrainMars = nGrainMars;
    }

    public boolean isAccepting(){
        return nPersonEarth == 0 && nLionEarth == 0 && nCowEarth == 0 && nGrainEarth == 0;
    }

    public boolean isValid(){
        if (nPersonEarth >= 0 && nPersonMars >= 0 && nLionEarth >= 0 && nLionMars >= 0
            && nCowEarth >= 0 && nCowMars >= 0 && nGrainEarth >= 0 && nGrainMars >= 0) {
            boolean cond = true;
            if (shipAt == Position.EARTH) {
                if (nPersonMars > 0 && nCowMars > 0)
                    cond = false;
                if (nCowMars > 0 && nGrainMars > 0)
                    cond = false;
                if (nLionMars > 0 && nCowMars > 0)
                    cond = false;
                if (nPersonMars > 0 && nLionMars > 0)
                    cond = false;
            } else {
                if (nPersonEarth > 0 && nCowEarth > 0)
                    cond = false;
                if (nCowEarth > 0 && nGrainEarth > 0)
                    cond = false;
                if (nLionEarth > 0 && nCowEarth > 0)
                    cond = false;
                if (nPersonEarth > 0 && nLionEarth > 0)
                    cond = false;
            }
            return cond;
        }

        return false;
    }

    public List<Node> generateChildren(){
        List<Node> children = new ArrayList<Node>();

        //EARTH TO MARS
        if (shipAt == Position.EARTH){
            //transport bag of grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth, nGrainEarth - 1, Position.MARS,
                                              nPersonMars, nLionMars, nCowMars, nGrainMars + 1));

            //transport cow to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth - 1, nGrainEarth, Position.MARS,
                                              nPersonMars, nLionMars, nCowMars + 1, nGrainMars));
            //transport lion to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth - 1, nCowEarth, nGrainEarth, Position.MARS,
                                              nPersonMars, nLionMars + 1, nCowMars, nGrainMars));

            //transport human to Mars
            validateAndAdd(children, new Node(nPersonEarth - 1, nLionEarth, nCowEarth, nGrainEarth, Position.MARS,
                                              nPersonMars + 1, nLionMars, nCowMars, nGrainMars));

            //transport 2 humans to Mars
            validateAndAdd(children, new Node(nPersonEarth - 2, nLionEarth, nCowEarth, nGrainEarth, Position.MARS,
                                              nPersonMars + 2, nLionMars, nCowMars, nGrainMars));

            //transport 1 human and 1 lion to Mars
            validateAndAdd(children, new Node(nPersonEarth - 1, nLionEarth - 1, nCowEarth, nGrainEarth, Position.MARS,
                                              nPersonMars + 1, nLionMars + 1, nCowMars, nGrainMars));

            //transport 1 human and 1 cow to Mars
            validateAndAdd(children, new Node(nPersonEarth - 1, nLionEarth, nCowEarth - 1, nGrainEarth, Position.MARS,
                                              nPersonMars + 1, nLionMars, nCowMars + 1, nGrainMars));

            //transport 1 human and 1 grain to Mars
            validateAndAdd(children, new Node(nPersonEarth - 1, nLionEarth, nCowEarth, nGrainEarth - 1, Position.MARS,
                                              nPersonMars + 1, nLionMars, nCowMars, nGrainMars + 1));

            //transport 1 lion and 1 cow to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth - 1, nCowEarth - 1, nGrainEarth, Position.MARS,
                                              nPersonMars, nLionMars + 1, nCowMars + 1, nGrainMars));

            //transport 1 lion and 1 grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth - 1, nCowEarth, nGrainEarth - 1, Position.MARS,
                                              nPersonMars, nLionMars + 1, nCowMars, nGrainMars + 1));

            //transport 1 cow and 1 grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth - 1, nGrainEarth - 1, Position.MARS,
                                              nPersonMars, nLionMars, nCowMars + 1, nGrainMars + 1));

        } else /*MARS to EARTH*/ {
            //transport bag of grain to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth, nGrainEarth + 1, Position.EARTH,
                                              nPersonMars, nLionMars, nCowMars, nGrainMars - 1));

            //transport cow to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth + 1, nGrainEarth, Position.EARTH,
                                              nPersonMars, nLionMars, nCowMars - 1, nGrainMars));
            //transport lion to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth + 1, nCowEarth, nGrainEarth, Position.EARTH,
                                              nPersonMars, nLionMars - 1, nCowMars, nGrainMars));

            //transport human to Earth
            validateAndAdd(children, new Node(nPersonEarth + 1, nLionEarth, nCowEarth, nGrainEarth, Position.EARTH,
                                              nPersonMars - 1, nLionMars, nCowMars, nGrainMars));

            //transport 2 humans to Earth
            validateAndAdd(children, new Node(nPersonEarth + 2, nLionEarth, nCowEarth, nGrainEarth, Position.EARTH,
                                              nPersonMars - 2, nLionMars, nCowMars, nGrainMars));

            //transport 1 human and 1 lion to Earth
            validateAndAdd(children, new Node(nPersonEarth + 1, nLionEarth + 1, nCowEarth, nGrainEarth, Position.EARTH,
                                              nPersonMars - 1, nLionMars - 1, nCowMars, nGrainMars));

            //transport 1 human and 1 cow to Earth
            validateAndAdd(children, new Node(nPersonEarth + 1, nLionEarth, nCowEarth + 1, nGrainEarth, Position.EARTH,
                                              nPersonMars - 1, nLionMars, nCowMars - 1, nGrainMars));

            //transport 1 human and 1 grain to Earth
            validateAndAdd(children, new Node(nPersonEarth + 1, nLionEarth, nCowEarth, nGrainEarth + 1, Position.EARTH,
                                              nPersonMars - 1, nLionMars, nCowMars, nGrainMars - 1));

            //transport 1 lion and 1 cow to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth + 1, nCowEarth + 1, nGrainEarth, Position.EARTH,
                                              nPersonMars, nLionMars - 1, nCowMars - 1, nGrainMars));

            //transport 1 lion and 1 grain to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth + 1, nCowEarth, nGrainEarth + 1, Position.EARTH,
                                              nPersonMars, nLionMars - 1, nCowMars, nGrainMars - 1));

            //transport 1 cow and 1 grain to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth + 1, nGrainEarth + 1, Position.EARTH,
                                              nPersonMars, nLionMars, nCowMars - 1, nGrainMars - 1));
        }

        return children;
    }

    public void validateAndAdd(List<Node> children, Node testSubject){
        if (testSubject.isValid()){
            testSubject.setParent(this);
            children.add(testSubject);
            System.out.println("Humans on Earth: " + testSubject.getnPersonEarth() + " Humans on Mars: " + testSubject.getnPersonMars() + " " + testSubject.isOnEarth());
            System.out.println("Lions on Earth: " + testSubject.getnLionEarth() + " Lions on Mars: " + testSubject.getnLionMars() + testSubject.isOnEarth());
            System.out.println("Cows on Earth: " + testSubject.getnCowEarth() + " Cows on Mars: " + testSubject.getnCowMars() + testSubject.isOnEarth());
            System.out.println("Grain on Earth: " + testSubject.getnGrainEarth() + " Grain on Mars: " + testSubject.getnGrainMars() + testSubject.isOnEarth() + "\n");
        }
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public Node getParent(){
        return parent;
    }

    public void goToMars(){
        shipAt = Position.MARS;
    }

    public void goToEarth(){
        shipAt = Position.EARTH;
    }

    public boolean isOnMars(){
        return shipAt == Position.MARS;
    }

    public boolean isOnEarth(){
        return shipAt == Position.EARTH;
    }

    public int getnPersonEarth() {
        return nPersonEarth;
    }

    public void setnPersonEarth(int nPersonEarth) {
        this.nPersonEarth = nPersonEarth;
    }

    public int getnLionEarth() {
        return nLionEarth;
    }

    public void setnLionEarth(int nLionEarth) {
        this.nLionEarth = nLionEarth;
    }

    public int getnCowEarth() {
        return nCowEarth;
    }

    public void setnCowEarth(int nCowEarth) {
        this.nCowEarth = nCowEarth;
    }

    public int getnGrainEarth() {
        return nGrainEarth;
    }

    public void setnGrainEarth(int nGrainEarth) {
        this.nGrainEarth = nGrainEarth;
    }

    public int getnPersonMars() {
        return nPersonMars;
    }

    public void setnPersonMars(int nPersonMars) {
        this.nPersonMars = nPersonMars;
    }

    public int getnLionMars() {
        return nLionMars;
    }

    public void setnLionMars(int nLionMars) {
        this.nLionMars = nLionMars;
    }

    public int getnCowMars() {
        return nCowMars;
    }

    public void setnCowMars(int nCowMars) {
        this.nCowMars = nCowMars;
    }

    public int getnGrainMars() {
        return nGrainMars;
    }

    public void setnGrainMars(int nGrainMars) {
        this.nGrainMars = nGrainMars;
    }

    //testing
    public static void main (String[] args){
        Node n = new Node(2, 1, 1, 1, Position.EARTH, 0, 0 ,0 ,0);
        n.generateChildren().get(0).generateChildren();
    }
}


