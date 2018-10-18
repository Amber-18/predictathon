

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
        People people = new People("src/Train.csv", "2000");
        
        // the types that we are focused on
        people.addToImportantValues("country");
        people.addToImportantValues("pageviews");
        people.addToImportantValues("hits");
        people.addToImportantValues("timeSinceLastVisit");
        
        // make sure a random sampling of input arrays all have the same dimensions
        int size1 = people.constructInputArray(people.getPerson((int)(Math.random() * 2000))).length;
        int size2 = people.constructInputArray(people.getPerson((int)(Math.random() * 2000))).length;
        int size3 = people.constructInputArray(people.getPerson((int)(Math.random() * 2000))).length;
        int size4 = people.constructInputArray(people.getPerson((int)(Math.random() * 2000))).length;
        
        if(size1 == size2 && size1 == size3 && size1 == size4) {
            System.out.println("Inputs are functioning!");
            
        } else {
            System.out.println("Inputs are not functioning! Help!");
        }
        
        int[] layerSizes = {size1, size1+20, 2};
        
        // System.out.println(Arrays.toString(layerSizes));
        
        Network network = new Network(layerSizes);
        
        double[] inputData = people.constructInputArray(people.getPerson((int)(Math.random() * 2000)));
        
        double[] outputData = network.run(inputData);
        
        // System.out.println(Arrays.toString(outputData));
        
        // need to fix the network arrays layer sizes, output is wrong size!
        

    }
}
