package ch.fhnw.project;

import javafx.collections.transformation.SortedList;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.LinkedList;

/**
 * Created by fabienne on 06.05.16.
 */
public class DataConvert {


    public static LinkedList<Variables> convert(File file) {


        try {
            BufferedReader in = new BufferedReader(new FileReader(file.toString()));
            if (file.toString().contains(".txt")) {
                System.out.println("Test is .txt");
                String firstLine = in.readLine();
                String[] firstLineSplit = firstLine.split("\t");
                System.out.println("Variable Count: " + firstLineSplit.length);
                Variables[] variables2 = new Variables[firstLineSplit.length]; //has to be replaced with variable count of .txt version
                String line;
                String[] lineSplit;
                LinkedList<Double> val = new LinkedList<Double>();
                LinkedList<Variables> finalList2 = new LinkedList<>();
                int z = 0;


                for (int w = 0; w < firstLineSplit.length; w++) {

                    z = 0;


                    while ((line = in.readLine()) != null) {

                        lineSplit = line.split("\t");
                        if (w > 0) {
                            val.set(z, Double.parseDouble(lineSplit[w]));
                        } else {
                            val.add(Double.parseDouble(lineSplit[w]));
                        }
                        z++;

                    }

                    finalList2.add(new Variables(firstLineSplit[w], val));
                }


                return finalList2;

            } else {
                System.out.println("Test is .lin");
                int variableCount = Integer.parseInt(in.readLine());
                System.out.println("Variable count: " + variableCount);

                Variables[] variables = new Variables[variableCount];
                String[] names = new String[variableCount];

                for (int i = 0; i < variableCount; i++) {
                    String name = in.readLine();
                    names[i] = name;

                }
                in.readLine();
                LinkedList<Double> val = new LinkedList<Double>();
                for (int i = 0; i < variableCount; i++) {
                    String line = in.readLine();
                    String[] lineValues = line.split(";");
                    System.out.println("Value 1 from LineValues: " + lineValues[0]);
                    System.out.println("Values count " + lineValues.length);
                    //System.out.println("lineValue Nr.2 " + lineValues[1]);

                    for (int r = 0; r < lineValues.length; r++) {
                        val.add(Double.parseDouble(lineValues[r]));
                    }
                    //System.out.println("val to array nr.2 " + val.toArray()[1]);
                    variables[i] = new Variables(names[i], val);
                    val.remove(); //empty the val here!!
                }
                LinkedList<Variables> finalList = new LinkedList<>(Arrays.asList(variables));
                return finalList;
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("It's a fnfe in the converting function");
        } catch (IOException ioe) {
            System.out.println("It's a IOException in the converting function");
        }
        LinkedList<Variables> toReturn = new LinkedList<>();
        return toReturn;
    }


    public static void main(String[] args) {

    }
}
