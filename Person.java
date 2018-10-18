

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
        
        //StringBuffer sb = new StringBuffer("\"" + type + "\"");
        //type = String.valueOf(sb);
        
        for(int i = 0; i < this.data.size(); ++i) {
            
            if(this.data.get(i).getType().equals(type)) {
                return this.data.get(i).getValue();
            }
        }
        
        return null;
    }
    
}
