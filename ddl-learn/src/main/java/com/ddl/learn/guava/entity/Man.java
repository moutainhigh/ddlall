package com.ddl.learn.guava.entity;

import java.io.Serializable;

public class Man implements Serializable {

    private Godness god;

    public Man() {


    }

    public Man(Godness god) {
        this.god = god;
    }

    public Godness getGod() {
        return god;
    }

    public void setGod(Godness god) {
        this.god = god;
    }

    @Override
    public String toString() {
        return "Man [god=" + god + "]";
    }

}
