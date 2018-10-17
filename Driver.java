

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Driver
{

    public static void main(String[] args) throws IOException
    {
        // create starting variables
        People people = createPeople();
        
        // the types that we are focused on
        people.addToImportantTypes("country");
        people.addToImportantTypes("pageviews");
        people.addToImportantTypes("hits");
        people.addToImportantTypes("timeSinceLastVisit");
        
        int numCountries = people.getValuesOf("country").size();
        
        
        int inputLayerSize = 3 + numCountries;
        
        int[] layerSizes = new int[3];
        
        layerSizes[0] = inputLayerSize;
        layerSizes[1] = inputLayerSize + 20;
        layerSizes[2] = 2;
        
        Network network = new Network(layerSizes);
        
        double[] inputData = new double[inputLayerSize];
        
        //just run it for one person
        HashMap<String, String> mapOfInput = people.getPerson(1).getValues(people.getImportantTypes());
        
        inputData[0] = Double.valueOf(mapOfInput.get("pageviews"));
        inputData[1] = Double.valueOf(mapOfInput.get("hits"));
        inputData[2] = Double.valueOf(mapOfInput.get("timeSinceLastVisit"));
        
        inputData = inputForCountries(inputData, people.getValuesOf("country"));
        
        
        network.run(inputData);
        
        
        

    }
    
    /** Creates and returns the People object containing the Person's created from the data file*/
    public static People createPeople() throws IOException {
        
        String[] typesOfValues;
        String[] arrayOfValues;
        String values;
        People people;
        
        // read in the file and create an array of types of values
        BufferedReader bf = new BufferedReader(new FileReader("src/Train.csv"));
        String typesOfValuesLongString = bf.readLine();
        typesOfValues = typesOfValuesLongString.split(",");
        
        
        // prime the while loop, read in the first array of values
        values = bf.readLine();
        arrayOfValues = values.split(",");
        
        people = new People(typesOfValues);
        
        int i = 0;
        // values != null
        while(i < 50000) {
            arrayOfValues = values.split(",");
            
            people.add(new Person(typesOfValues, arrayOfValues));
            
            values = bf.readLine();
            
            ++i;
        }
        
        return people;
        
    }
    
    public static double[] inputForAVariableType(double[] inputData, ArrayList<String> types) {
        
        Collections.sort(types);
        
        
        int i = 0;
        // TODO current work is here, and null double[] index is actually 0.0, not null
        while(inputData[i] != 0.0) {
            ++i;
        }
    }

}
