package ch.fhnw.project.DataHandling;


import ch.fhnw.project.Variables;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabienne on 25.05.16.
 */
public class LinConverter implements DataConverter{

    public Data read(File file) throws IOException {
        List<Variables> finalList = new ArrayList<>();

        BufferedReader in = new BufferedReader(new FileReader(file.toString()));
        String firstLine = in.readLine();
        String[] variableNames = new String[Integer.parseInt(firstLine)];
        List<List<Double>> variableValues = new ArrayList<List<Double>>();
        String line;
        String[] linesplit;


        for (int i = 0; i < Integer.parseInt(firstLine); i++) {
            variableNames[i] = in.readLine();
            variableValues.add(new ArrayList<Double>());
        }
        in.readLine();

        for (int i = 0; i < Integer.parseInt(firstLine); i++) {
            line = in.readLine();
            linesplit = line.split(";");
            for (int r = 0; r < linesplit.length; r++) {
                variableValues.get(i).add(Double.parseDouble(linesplit[r]));
            }
        }
        for (int i = 0; i < Integer.parseInt(firstLine); i++) {
            finalList.add(new Variables(variableNames[i], variableValues.get(i)));
        }

        return new Data(file.getName(),finalList);

    }

}
