

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
        People people = new People("src/Train.csv", "9000");
        
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
        
        System.out.println(Arrays.toString(layerSizes));
        
        Network network = new Network(layerSizes);
        
        double[] inputData = people.constructInputArray(people.getPerson((int)(Math.random() * 2000)));
        
        double[] outputData = network.run(inputData);
        
        double[] expected = {1.0, 1.0};
        
        boolean isEqual = false;
        
        for(int i = 0; i < 300; ++i) {
            inputData = people.constructInputArray(people.getPerson((int)(Math.random() * 2000)));
            outputData = network.run(inputData);
            
            if(expected[0] != outputData[0] && expected[1] != outputData[1])
                isEqual = true;
            
            if(isEqual) {
                System.out.println(Arrays.toString(outputData));
            }
        }
        
        
        // all the outputs are the same vector!, make sure this isn't a bug!
        // otherwise, no other bugs i think
        

    }
}
