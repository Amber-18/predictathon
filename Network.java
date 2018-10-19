import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Network
{
    /** An ArrayList of Layers of Neurons in the Network*/    
    LinkedList<Layer> network;
    
    /** The output data of the network*/
    private double[] outputData;
    
    /** Takes in an int[] holding the sizes of the layer of the network
     * The length of the array is the number of layers
     * The value of the int in each index is the number of neurons each layer is to have
     * @param layerSizes The int[] of layer sizes for the neural network*/
    public Network(int[] layerSizes) {
        
        this.network = new LinkedList<Layer>();
        
        // add the input layer, size of previous layer is 0
        this.network.add(new Layer(layerSizes[0], 0, 0));
        
        // add all the other layers
        for(int i = 1; i < layerSizes.length; ++i) {
            this.network.add(new Layer(layerSizes[i], layerSizes[i-1], i));
        }
        
    }
    
    /** Runs the network
     * Input a double[] of the input data
     * The input array should be the same length as the number of input neurons in the network
     * @param inputdata A double[] of the input data
     * @return A double[] of the output data*/
    public double[] run(double[] inputData){
        
        // confirm array of input data is compatible with number of input neurons
        if(inputData.length != this.network.get(0).size()) {
            
            System.out.println(inputData.length);
            System.out.println(this.network.get(2).size());
            System.out.println("Input array is not compatible with this network!");
        }
        
        double[] output = {-999};
        double[] input = inputData;
        
        // for every layer in the network
        for(int i = 1; i < this.network.size(); ++i) {
            
            // run the layer and assign its output to the next layers input
            output = this.network.get(i).runLayer(input);
            input = output;
        }
        
        // after all layers have ran, return the final output
        this.outputData = output;
        return output;
    }

    /** Returns the double[] of the final data this network produces
     * @return The final data*/
    public double[] getFinalData() {
        return this.outputData;
    }
    
    /** Trains the network TODO*/
    public void train(double[] expected) {
        
    }

}
