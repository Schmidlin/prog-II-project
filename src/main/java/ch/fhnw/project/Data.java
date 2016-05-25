package ch.fhnw.project;

import java.util.List;

/**
 * Created by fabienne on 19.05.16.
 */
public class Data {
    private String dataName;
    private List<Variables> listVariables;

    public Data(String dataName, List<Variables> listVariable){
        this.dataName = dataName;
        this.listVariables = listVariable;
    }
    public String getDataName(){return dataName;}
    public List<Variables> getListVariables(){return listVariables;}
}
