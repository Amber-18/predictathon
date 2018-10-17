

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Person
{
    /** The values associated with each type*/
    private HashMap<String, String> data;
    
    /** Constructor for a Person
     * @param typesOfValues The types of values that the person has
     * @param values The values for each type of value*/
    public Person(String[] typesOfValues, String[] values) {
        
        int index = 0;
        data = new HashMap<>();
        
        for(int i = 0; i < typesOfValues.length; ++i) {
            data.put(typesOfValues[index], values[index]);
            index++;
        }
    }
    
    /** Return the String value that the person has for the type given
     * @param type The type of value to return
     * @return The value returned based on the type*/
    public String getValue(String type) {
        return this.data.get(type);
    }
    
    /** Return string representation of the person
     * @return String representation of the Person*/
    public String toString(){
        
        StringBuffer format = new StringBuffer();
        
        for(String type : data.keySet()) {
            format.append(type + " : " + data.get(type) + "\n");
        }
        
        return String.valueOf(format);
       
    }

    /** When passed an arraylist of the different types to get values for, it returns a HashMap of the types
     * and their values for this Person
     * @param importantTypes The ArrayList of types you need values for
     * @return A HashMap of the importantTypes and their values*/
    public HashMap<String, String> getValues(ArrayList<String> importantTypes) {
        
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
}
