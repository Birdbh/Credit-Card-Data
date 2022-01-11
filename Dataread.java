import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;

import java.util.Jswing;
import java.awt.event.WindowListener;

class Main{
    public static void main(String[] args) throws IOException {

    //Get data

    List<List<String>> readData = readData();
    List<List<String>> cleanDescription = cleanDescription(readData);

    //Descriptions

    HashMap<String, List<List<String>>> descriptionSorting = Analyze.Description.descriptionSorting(cleanDescription);
    HashMap<String, Double> descriptionSums = Analyze.Description.descriptionSums(descriptionSorting);
    //int inputamount = GUI.TextField.ActionPerformed();
    HashMap<String, Double> topValues = Analyze.Description.topValues(descriptionSums, 12);
    //GUI.main(args, topValues);

    //Dates

    HashMap<String, List<List<String>>> dateSorting = Analyze.date.dateSorting(cleanDescription);
    HashMap<String, Double> dateSums = Analyze.date.dateSums(dateSorting);
    HashMap<String, Double> topValuesdate = Analyze.date.topValues(dateSums, 10);
    GUI.main(args, topValuesdate);

    }

    public static List<List<String>> cleanDescription(List<List<String>> lines){

        int indextracker = -1;

        //Save only first two words of Description

        for(List<String> values : lines){
            indextracker++;

            //Get description data

            String description = values.get(3);

            //Remove Quotations
            
            description = description.replace("\"","");

            //Remove #

            description = description.replace("#","");

            //Remove .

            description = description.replace(".","");

            //Remove &

            description = description.replace("&","");

            //Put words of Description into an array

            String descriptionArray[] = description.split(" ");

            //Remove the second part of strings where there is a descripton and then a number of identifier

            for (int i = 0; i < descriptionArray.length; i++){

                if (descriptionArray[i].indexOf('*') != -1){
                    descriptionArray[i] = descriptionArray[i].substring(0, descriptionArray[i].indexOf('*'));
                    
                }
                if (descriptionArray[i].indexOf('-') != -1){
                    descriptionArray[i] = descriptionArray[i].substring(0, descriptionArray[i].indexOf('-'));

                    //Remove the left over words that have numbers in them

                    descriptionArray[i] = descriptionArray[i].replace("\\w*\\d\\w* *", "");
                }

                //Remove the left over words that have numbers in them

                char[] chars = descriptionArray[i].toCharArray();
                
                boolean containsNumber = false;

                for(char c : chars){
                    if(Character.isDigit(c)){
                        containsNumber = true;
                    }
                }

                if (containsNumber){
                    descriptionArray[i] = "";
                }
            }

            //Remove left over words that have numbers in them


            //Combine first and second word

            String newDescription;

            if (descriptionArray.length > 1){
                newDescription = descriptionArray[0] + " " + descriptionArray[1];
            }
            else{
                newDescription = descriptionArray[0];
            }

            //values.set(4, debit);

            lines.get(indextracker).set(3, newDescription);
        }

        //Remove Spaces from the Front of Description

        return lines;
    }

    public static List<List<String>> readData() throws IOException{

    //Read data From file

    BufferedReader csvReader = new BufferedReader(new FileReader("Account.csv"));

    //String data from CSV file

    String line = "";
    String splitBy = ",";

    //List of Lists to store data
    
    List<List<String>> lines = new ArrayList();

    //Read through CSV file and add values to List and List of Lists

    while ((line = csvReader.readLine()) != null){
        List<String> values = Arrays.asList(line.split(splitBy));
        lines.add(values);
    }

    //Close Reader

    csvReader.close();

    return lines;
    }
}