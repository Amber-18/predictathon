

import java.util.Arrays;
import java.util.HashMap;

public class Person
{
    private HashMap<String, String> data;
    
    public Person(String[] typesOfValues, String[] values) {
        
        int index = 0;
        data = new HashMap<>();
        
        for(int i = 0; i < typesOfValues.length; ++i) {
            data.put(typesOfValues[index], values[index]);
            index++;
        }
    }

}
