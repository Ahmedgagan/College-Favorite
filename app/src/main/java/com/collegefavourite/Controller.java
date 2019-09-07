package com.collegefavourite;

import java.util.ArrayList;

/**
 * Created by ahmedgagan on 20/12/18.
 */

public class Controller {
    private ArrayList<Model_List> myList = new ArrayList<Model_List>();
    public ArrayList<Model_List> getMyMatches(){return myList;}
    public Model_List getMatches(int pPosition){
        return myList.get(pPosition);
    }
    public void  setMatches(Model_List Match){
        myList.add(Match);
    }
    public int  getMatchesArraylistsize(){
        return myList.size();
    }
}
