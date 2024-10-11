package com.inventorymanagementsystem.entity;

public class Location {

    private int loc_id;
    private String loc_name;

    public Location(int loc_id, String loc_name){
        this.loc_id = loc_id;
        this.loc_name = loc_name;
    }

    public int getLoc_id(){
        return loc_id;
    }
    public String getLoc_name() {
        return loc_name;
    }

    @Override
    public String toString() {
        return loc_name;
    }

}
