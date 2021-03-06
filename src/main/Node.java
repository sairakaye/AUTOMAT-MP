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
    private List<Node> children;

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
        children = new ArrayList<Node>();
    }

    public Node(boolean nBoyEarth, boolean nGirlEarth, boolean nLionEarth, boolean nCowEarth, boolean nGrainEarth, boolean onEarth,
                boolean nBoyMars, boolean nGirlMars, boolean nLionMars, boolean nCowMars, boolean nGrainMars){
        if (nBoyEarth){
            if (nGirlEarth)
                this.nPersonEarth = 2;
            else
                this.nPersonEarth = 1;
        }
        if (nGirlEarth){
            if (nBoyEarth)
                this.nPersonEarth = 2;
            else
                this.nPersonEarth = 1;
        }
        if (nLionEarth)
            this.nLionEarth = 1;
        if (nCowEarth)
            this.nCowEarth = 1;
        if (nGrainEarth)
            this.nGrainEarth = 1;

        if (onEarth)
            shipAt = Position.EARTH;
        else
            shipAt = Position.MARS;

        if (nBoyMars){
            if (nGirlMars)
                this.nPersonMars = 2;
            else
                this.nPersonMars = 1;
        }
        if (nGirlMars){
            if (nBoyMars)
                this.nPersonMars = 2;
            else
                this.nPersonMars = 1;
        }
        if (nLionMars)
            this.nLionMars = 1;
        if (nCowMars)
            this.nCowMars = 1;
        if (nGrainMars)
            this.nGrainMars = 1;
        children = new ArrayList<Node>();
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
        children = new ArrayList<Node>();

        //EARTH TO MARS
        if (shipAt == Position.EARTH){
            //transport bag of grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth, nGrainEarth - 1, Position.MARS,
                                              nPersonMars, nLionMars, nCowMars, nGrainMars + 1));

            //transport lion to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth - 1, nCowEarth, nGrainEarth, Position.MARS,
                    nPersonMars, nLionMars + 1, nCowMars, nGrainMars));

            //transport cow to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth - 1, nGrainEarth, Position.MARS,
                                              nPersonMars, nLionMars, nCowMars + 1, nGrainMars));

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

            //transport 1 cow and 1 grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth - 1, nGrainEarth - 1, Position.MARS,
                                              nPersonMars, nLionMars, nCowMars + 1, nGrainMars + 1));

            //transport 1 lion and 1 grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth - 1, nCowEarth, nGrainEarth - 1, Position.MARS,
                                              nPersonMars, nLionMars + 1, nCowMars, nGrainMars + 1));

        } else /*MARS to EARTH*/ {
            //transport bag of grain to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth, nGrainEarth + 1, Position.EARTH,
                                              nPersonMars, nLionMars, nCowMars, nGrainMars - 1));

            //transport lion to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth + 1, nCowEarth, nGrainEarth, Position.EARTH,
                                              nPersonMars, nLionMars - 1, nCowMars, nGrainMars));

            //transport cow to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth + 1, nGrainEarth, Position.EARTH,
                                              nPersonMars, nLionMars, nCowMars - 1, nGrainMars));

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

            //transport 1 cow and 1 grain to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth + 1, nGrainEarth + 1, Position.EARTH,
                                              nPersonMars, nLionMars, nCowMars - 1, nGrainMars - 1));

            //transport 1 lion and 1 grain to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth + 1, nCowEarth, nGrainEarth + 1, Position.EARTH,
                                              nPersonMars, nLionMars - 1, nCowMars, nGrainMars - 1));
        }

        return children;
    }

    public List<Node> generateChildrenType2(){
        children = new ArrayList<Node>();

        //EARTH TO MARS
        if (shipAt == Position.EARTH){
            //transport bag of grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth, nGrainEarth - 1, Position.MARS,
                    nPersonMars, nLionMars, nCowMars, nGrainMars + 1));

            //transport lion to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth - 1, nCowEarth, nGrainEarth, Position.MARS,
                    nPersonMars, nLionMars + 1, nCowMars, nGrainMars));

            //transport cow to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth - 1, nGrainEarth, Position.MARS,
                    nPersonMars, nLionMars, nCowMars + 1, nGrainMars));

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

            //transport lion to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth + 1, nCowEarth, nGrainEarth, Position.EARTH,
                    nPersonMars, nLionMars - 1, nCowMars, nGrainMars));

            //transport cow to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth + 1, nGrainEarth, Position.EARTH,
                    nPersonMars, nLionMars, nCowMars - 1, nGrainMars));

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

    public List<Node> generateChildrenType3(){
        children = new ArrayList<Node>();

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

    public List<Node> generateChildrenType4(){
        children = new ArrayList<Node>();

        //EARTH TO MARS
        if (shipAt == Position.EARTH){
            //transport bag of grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth, nGrainEarth - 1, Position.MARS,
                    nPersonMars, nLionMars, nCowMars, nGrainMars + 1));

            //transport lion to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth - 1, nCowEarth, nGrainEarth, Position.MARS,
                    nPersonMars, nLionMars + 1, nCowMars, nGrainMars));

            //transport cow to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth - 1, nGrainEarth, Position.MARS,
                    nPersonMars, nLionMars, nCowMars + 1, nGrainMars));

            //transport human to Mars
            validateAndAdd(children, new Node(nPersonEarth - 1, nLionEarth, nCowEarth, nGrainEarth, Position.MARS,
                    nPersonMars + 1, nLionMars, nCowMars, nGrainMars));

            //transport 1 lion and 1 grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth - 1, nCowEarth, nGrainEarth - 1, Position.MARS,
                    nPersonMars, nLionMars + 1, nCowMars, nGrainMars + 1));

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

            //transport 1 cow and 1 grain to Mars
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth - 1, nGrainEarth - 1, Position.MARS,
                    nPersonMars, nLionMars, nCowMars + 1, nGrainMars + 1));
        } else /*MARS to EARTH */{
            //transport bag of grain to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth, nGrainEarth + 1, Position.EARTH,
                    nPersonMars, nLionMars, nCowMars, nGrainMars - 1));

            //transport lion to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth + 1, nCowEarth, nGrainEarth, Position.EARTH,
                    nPersonMars, nLionMars - 1, nCowMars, nGrainMars));

            //transport cow to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth + 1, nGrainEarth, Position.EARTH,
                    nPersonMars, nLionMars, nCowMars - 1, nGrainMars));

            //transport human to Earth
            validateAndAdd(children, new Node(nPersonEarth + 1, nLionEarth, nCowEarth, nGrainEarth, Position.EARTH,
                    nPersonMars - 1, nLionMars, nCowMars, nGrainMars));

            //transport 1 lion and 1 grain to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth + 1, nCowEarth, nGrainEarth + 1, Position.EARTH,
                    nPersonMars, nLionMars - 1, nCowMars, nGrainMars - 1));

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

            //transport 1 cow and 1 grain to Earth
            validateAndAdd(children, new Node(nPersonEarth, nLionEarth, nCowEarth + 1, nGrainEarth + 1, Position.EARTH,
                    nPersonMars, nLionMars, nCowMars - 1, nGrainMars - 1));
        }

        return children;
    }

    public void validateAndAdd(List<Node> child, Node testSubject){
        if (testSubject.isValid()){
            testSubject.setParent(this);
            children.add(testSubject);
        }
    }

    public boolean equals(Object o){
        if (!(o instanceof Node)){
            return false;
        } else{
            Node n = (Node) o;
            return (n.getnPersonEarth() == nPersonEarth && n.getnLionEarth() == nLionEarth && n.getnCowEarth() == nCowEarth && n.getnGrainEarth() == nGrainEarth
                    && n.getShipAt() == shipAt
                    && n.getnPersonMars() == nPersonMars && n.getnLionMars() == nLionMars && n.getnCowMars() == nCowMars && n.getnGrainMars() == nGrainMars);
        }
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public Node getParent(){
        return parent;
    }

    public void goToMars(/*int nPersonEarth, int nLionEarth, int nCowEarth, int nGrainEarth, int nPersonMars, int nLionMars, int nCowMars, int nGrainMars*/){
        shipAt = Position.MARS;
        //return new Node(nPersonEarth, nLionEarth, nCowEarth, nGrainEarth, shipAt, nPersonMars, nLionMars, nCowMars, nGrainMars);
    }

    public void goToEarth(/*int nPersonEarth, int nLionEarth, int nCowEarth, int nGrainEarth, int nPersonMars, int nLionMars, int nCowMars, int nGrainMars*/){
        shipAt = Position.EARTH;
        //return new Node(nPersonEarth, nLionEarth, nCowEarth, nGrainEarth, shipAt, nPersonMars, nLionMars, nCowMars, nGrainMars);
    }

    public Position getShipAt(){
        return shipAt;
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

    public List<Node> getChildren(){
        return children;
    }

    public static List<Node> getSolution(Node solution) {
        if (solution == null) {
            System.out.print("\nNo solution found.");
        } else {
            System.out.println("\nSolution: ");
            List<Node> path = new ArrayList<Node>();
            Node state = solution;
            while(state != null) { //while state is not null
                path.add(state); //add the path of the solution to the list
                state = state.getParent(); //get the parent of this state
            }

            return path;
        }

        return null;
    }

    public String toString(){
        String toSend = "";

        if (shipAt == Position.EARTH){
            toSend += "For the next step, Earth should have: \n";

            if (nPersonEarth > 0)
                toSend += nPersonEarth + " human(s)\n";

            if (nLionEarth > 0)
                toSend += nLionEarth + " lion(s)\n";

            if (nCowEarth > 0)
                toSend += nCowEarth + " cow(s)\n";

            if (nGrainEarth > 0) {
                toSend += nGrainEarth + " grain(s)\n\n";
            } else {
                toSend += "\n";
            }

            return toSend;
        } else {
            toSend += "For the next step, Mars should have: \n";
            if (nPersonMars > 0)
                toSend += nPersonMars + " human(s)\n";

            if (nLionMars > 0)
                toSend += nLionMars + " lion(s)\n";

            if (nCowMars > 0)
                toSend += nCowMars + " cow(s)\n";

            if (nGrainMars > 0) {
                toSend += nGrainMars + " grain(s)\n\n";
            } else {
                toSend += "\n";
            }

            return toSend;
        }
    }
}


