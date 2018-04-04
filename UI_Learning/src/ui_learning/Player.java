package ui_learning;

import java.util.ArrayList;

public abstract class Player {
    public ArrayList<CardButton> PlayerHands ;
    public ArrayList<CardButton> PlayerTable ;
    //public ArrayList<Integer> PlayerDiscard ;
    public ArrayList<Integer> tempHu ;
    public abstract boolean checkHu() ;
}
