

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Driver
{

    public static void main(String[] args) throws IOException
    {
        // create starting variables
        ArrayList<Person> people = new ArrayList<>();
        String[] typesOfValues;
        String[] arrayOfValues;
        String values;
        
        // read in the file and create an array of types of values
        BufferedReader bf = new BufferedReader(new FileReader("src/predictathon/Train.csv"));
        String typesOfValuesLongString = bf.readLine();
        typesOfValues = typesOfValuesLongString.split(",");
        
        
        // prime the while loop, read in the first array of values
        values = bf.readLine();
        
        while(values != null) {
            arrayOfValues = values.split(",");
            
            people.add(new Person(typesOfValues, arrayOfValues));
            
            values = bf.readLine();
        }
        
        
        
        
        bf.close();

    }

}
