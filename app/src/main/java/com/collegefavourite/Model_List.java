package com.collegefavourite;

/**
 * Created by ahmedgagan on 20/12/18.
 */

public class Model_List {
    private String name;
    private String favourite;

    public Model_List(String name, String favourite){
        this.name = name;
        this.favourite = favourite;

    }
    public String getName(){
        return name;
    }
    public String getFavourite(){
        return favourite;
    }
}
