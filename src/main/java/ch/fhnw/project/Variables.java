package ch.fhnw.project;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by fabienne on 06.05.16.
 */
public  class Variables {
    private  String name;
    private List<Double> values;

    public Variables(String name, List<Double> values){
        this.name = name;
        this.values = values;
    }

    public String getName(){
        return name;
    }

    public List<Double> getValues(){
        return values;
    }

}
