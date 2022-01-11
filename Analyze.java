import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Analyze {

    public static class Description{
    
        //Sum of Descriptions
        public static HashMap<String, List<List<String>>> descriptionSorting(List<List<String>> lines){


            //Return HashMap to relate Description and values in a List

            HashMap<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();

            //Loop through List of List and if the description is not already there add it and relate to the sub List

            //If the description is in the map then simply add the sublist with its description key

            for (List<String> values : lines){

                if(!map.containsKey(values.get(3))){

                    List<List<String>> sublist = new ArrayList();
                    sublist.add(values);

                    map.put(values.get(3),sublist);
                }

                else{
                    map.get(values.get(3)).add(values);
                }
            }

            return map;
        }

        public static double descriptionSum(HashMap<String, List<List<String>>> map, String Description){
            //Sum is 0 at first
            double sum = 0;

            //List to store the list from the map

            List<List<String>> sublist = new ArrayList();

            //Use Description as our key and sum values for that key to get the total of desription

            sublist = map.get(Description);

            //Cycle through the sublist and add values for the debit

            for (List<String> values : sublist){
                try{
                    sum += Double.parseDouble(values.get(4));
                }
                catch(NumberFormatException e){
                    
                }

            }

            return sum;
        }

        public static HashMap<String, Double> descriptionSums(HashMap<String, List<List<String>>> map) throws IOException{

            //Create map to store sums

            HashMap<String, Double> sums = new HashMap();

            //Now a map is made "sums" that stores all account data\

            for (String entry: map.keySet()){
                
                double sum = descriptionSum(map, entry);
                sums.put(entry, sum);

            }

            return sums;
        }

        public static HashMap<String, Double> topValues(HashMap<String, Double> sums, int places){

            //This is definlty not the most effiecent solution but its the one I came up with using code that I understand I should fix this in the future or ask someone for help

            HashMap<String, Double> ranks = new HashMap();

            for (int i = 0; i < places; i++){

                double max = 0;
                String maxentry = null;

                for (String entry : sums.keySet()){
                    if(sums.get(entry) > max){
                        max = sums.get(entry);
                        maxentry = entry;
                    }
                }

                ranks.put(maxentry, max);
                sums.remove(maxentry);
            }

            return ranks;
        }

    }

    public static class date{
        //Sum of dates
        public static HashMap<String, List<List<String>>> dateSorting(List<List<String>> lines){


            //Return HashMap to relate Description and values in a List

            HashMap<String, List<List<String>>> mapDates = new HashMap<String, List<List<String>>>();

            //Loop through List of List and if the description is not already there add it and relate to the sub List

            //If the description is in the map then simply add the sublist with its description key

            for (List<String> values : lines){

                if(!mapDates.containsKey(values.get(1))){

                    List<List<String>> sublist = new ArrayList();
                    sublist.add(values);

                    mapDates.put(values.get(1),sublist);
                }

                else{
                    mapDates.get(values.get(1)).add(values);
                }
            }
            
            return mapDates;
        }

        public static double dateSum(HashMap<String, List<List<String>>> mapDates, String Description){
            //Sum is 0 at first
            double sum = 0.0;

            //List to store the list from the map

            List<List<String>> sublist = new ArrayList();

            //Use Description as our key and sum values for that key to get the total of desription

            sublist = mapDates.get(Description);

            //Cycle through the sublist and add values for the debit

            for (List<String> values : sublist){
                try{
                    sum += Double.parseDouble(values.get(4));
                }
                catch(NumberFormatException e){
                    
                }

            }

            return sum;
        }

        public static HashMap<String, Double> dateSums(HashMap<String, List<List<String>>> mapDates) throws IOException{

            //Create map to store sums

            HashMap<String, Double> sums = new HashMap();

            //Now a map is made "sums" that stores all account data\

            for (String entry: mapDates.keySet()){
                //System.out.print(entry);
                
                double sum = dateSum(mapDates, entry);
                sums.put(entry, sum);

            }
            return sums;
        }

        public static HashMap<String, Double> topValues(HashMap<String, Double> sums, int places){

            //This is definlty not the most effiecent solution but its the one I came up with using code that I understand I should fix this in the future or ask someone for help

            HashMap<String, Double> ranks = new HashMap();

            for (int i = 0; i < places; i++){

                double max = 0;
                String maxentry = null;

                for (String entry : sums.keySet()){
                    if(sums.get(entry) > max){
                        max = sums.get(entry);
                        maxentry = entry;
                    }
                }

                ranks.put(maxentry, max);
                sums.remove(maxentry);
            }

            return ranks;
        }

    }
}
