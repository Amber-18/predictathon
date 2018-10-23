

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
        People people = new People("src/Train.csv", "2001");
        
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
        
        // int[] layerSizes = {size1, size1+20, 2};
        
        // System.out.println(Arrays.toString(layerSizes));
        // double[] input = people.constructInputArray(people.getPerson((int)(Math.random() * 2000)));
        
        
        
        int[] layerSizes = {1, 20, 1};
        double[] input = {0.1, 0.9};
        double[] expected = {0.35, 0.65};
        
        
        Network network = new Network(layerSizes);
        
        int j = 0;
        
        for(int i = 0; i < 2000; ++i) {
            System.out.println();
            double X = expected[j];
            double Y = input[j];
            double[] x = {X};
            double[] y = {Y};
            
            System.out.println(Arrays.toString(x));
            network.run(y);
            network.train(x, y);
            network.run(y);
            System.out.println(Arrays.toString(network.getFinalData()));
            ++j;
            
            try {
                double xx = expected[j];
            } catch(Exception e) {
                j = 0;
            }
        }
        
        double[] inputs = {0.5};
        
        System.out.println(Arrays.toString(network.run(inputs)));

        
        

    }
}
