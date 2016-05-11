package ch.fhnw.project;


import java.util.LinkedList;

/**
 * Created by fabienne on 06.05.16.
 */
public  class Variables {
    private  String name;
    private LinkedList<Double> values;

    public Variables(String name, LinkedList<Double> values){
        this.name = name;
        this.values = values;
    }

    public String getName(){
        return name;
    }

    public LinkedList<Double> getValues(){
        return values;
    }

}
