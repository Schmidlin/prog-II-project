package ch.fhnw.project;

import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fabienne on 16.05.16.
 */
public class DataConvert2 {


    public static List<Variables> convert2(File file) {
        if (file.toString().contains(".txt")) {
            List<Variables> variableList = converttxt(file);
            return variableList;
        } else {
            List<Variables> variableList = convertlin(file);
            return variableList;
        }
    }

    public static List<Variables> converttxt(File file) {

        List<Variables> finalList = new ArrayList<>();
        try {
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


        } catch (FileNotFoundException fnfe) {
            System.out.println("It's a fnfe in the converting function");
        } catch (IOException ioe) {
            System.out.println("It's a IOException in the converting function");
        }
        return finalList;
    }

    public static List<Variables> convertlin(File file) {

        List<Variables> finalList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file.toString()));
            String firstLine = in.readLine();
            String[] variableNames = new String[Integer.parseInt(firstLine)];
            List<List<Double>> variableValues = new ArrayList<List<Double>>();
            String line;
            String[] linesplit;


            for(int i =0; i<Integer.parseInt(firstLine);i++){
                variableNames[i] = in.readLine();
                variableValues.add(new ArrayList<Double>());
            }
            in.readLine();

            for (int i=0; i<Integer.parseInt(firstLine);i++){
                line = in.readLine();
                linesplit = line.split(";");
                for (int r = 0; r< linesplit.length;r++){
                    variableValues.get(i).add(Double.parseDouble(linesplit[r]));
                }
            }
            for (int i =0; i<Integer.parseInt(firstLine); i++){
                finalList.add(new Variables(variableNames[i],variableValues.get(i)));
            }




        } catch (FileNotFoundException fnfe) {
            System.out.println("It's a fnfe in the converting function");
        } catch (IOException ioe) {
            System.out.println("It's a IOException in the converting function");
        }
        return finalList;
    }

}
