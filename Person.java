

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Person
{
    /** The values associated with each type*/
    private ArrayList<Value> data;
    
    /** Constructor for a Person
     * @param typesOfValues The types of values that the person has
     * @param values The values for each type of value*/
    public Person(String[] typesOfValues, String[] values) {
        
        int index = 0;
        data = new ArrayList<>();
        
        for(int i = 0; i < typesOfValues.length; ++i) {
            data.add(new Value(typesOfValues[index], values[index]));
            index++;
        }
    }
    
    /** Return the String value that the person has for the type given
     * @param type The type of value to return
     * @return The value returned based on the type*/
    public String getValueFor(String type) {
        
        StringBuffer sb = new StringBuffer("\"" + type + "\"");
        type = String.valueOf(sb);
        
        for(int i = 0; i < this.data.size(); ++i) {
            
            if(this.data.get(i).getType().equals(type)) {
                return this.data.get(i).getValue();
            }
        }
        
        return null;
    }

    /** When passed an arraylist of the different types to get values for, it returns a HashMap of the types
     * and their values for this Person
     * @param importantTypes The ArrayList of types you need values for
     * @return A HashMap of the importantTypes and their values*/
    public HashMap<String, String> getImportantValues(ArrayList<String> importantTypes) {
        
        HashMap<String, String> values = new HashMap<String, String>();
        
        for(String type : importantTypes) {
            
            StringBuffer x = new StringBuffer("\"" + type + "\"");
            type = String.valueOf(x);
            
            if(this.data.containsKey(type)) {
                values.put(type, this.data.get(type));
            }
        }
        
        return values;
    }
    
    
    public double[] constructInputArray(ArrayList<String> importantValues) {
        
        int inputLayerSize;
        
        for(String type : importantValues) {
            
            StringBuffer sb = new StringBuffer("\"" + type + "\"");
            type = String.valueOf(sb);
            
            try {
                Double.valueOf(this.getValueFor(type));
                inputLayerSize += 1;
            } catch(NumberFormatException e) {
                // TODO put this in people so you can calculate how many potential values each important value can have
                // you can iterate through people.getperson(i) to find each input value
            }
        }
        
        
        for(int i = 0;)
        
        double[] input = new double[inputLayerSize];
        this.importantValues;
    }
}
