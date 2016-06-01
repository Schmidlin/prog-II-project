package ch.fhnw.project.DataHandling;

import ch.fhnw.project.Variables;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabienne on 25.05.16.
 */
public class TxtConverter implements DataConverter{

    public Data read(File file) throws IOException {
        List<Variables> finalList = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(file.toString()));
        String firstLine = in.readLine();
        String[] variableNames = firstLine.split("\t");
        List<List<Double>> variableValues = new ArrayList<List<Double>>();
        String line;
        String[] lineValues = new String[variableNames.length];

        for (int i=0; i<variableNames.length;i++){
            variableValues.add(new ArrayList<Double>());
        }

        while((line = in.readLine()) != null){
            lineValues = line.split("\t");
            for (int i = 0; i<variableNames.length; i++){
                variableValues.get(i).add(Double.parseDouble(lineValues[i]));
            }
        }


        for (int i =0; i<variableNames.length; i++){
            finalList.add(new Variables(variableNames[i],variableValues.get(i)));
        }
        return new Data(file.getName(),finalList);

    }
}
