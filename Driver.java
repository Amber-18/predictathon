

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Driver
{

    public static void main(String[] args) throws IOException
    {
        BufferedReader bf = new BufferedReader(new FileReader("src/predictathon/Train.csv"));
        String typesOfValuesLongString = bf.readLine();
        String[] typesOfValues = typesOfValuesLongString.split(",");
        
        String values = bf.readLine();
        String[] arrayOfValues;
        ArrayList<Person> people = new ArrayList<>();
        
        while(values != null) {
            arrayOfValues = values.split(",");
            
            people.add(new Person(typesOfValues, arrayOfValues));
            
            values = bf.readLine();
        }
        
        System.out.println(people.size());
        
        
        bf.close();

    }

}
