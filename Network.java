import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Network
{
    /** An ArrayList of Layers of Neurons in the Network*/    
    LinkedList<Layer> network;
    
    /** The output data of the network*/
    private LinkedList<BigDecimal> outputData;
    
    /** The learning rate of this network*/
    private BigDecimal learningRate;
    
    private int inputSize;
    
    /** Takes in an int[] holding the sizes of the layer of the network
     * The length of the array is the number of layers
     * The value of the int in each index is the number of neurons each layer is to have
     * @param layerSizes The int[] of layer sizes for the neural network*/
    public Network(LinkedList<Integer> layerSizes, String learningRate) {
        
        this.learningRate = new BigDecimal(learningRate);
        this.inputSize = (int)layerSizes.get(0);
        this.network = new LinkedList<Layer>();

        // add all the layers to the network
        int numWeights = 0;
        for(Integer layerSize : layerSizes) {
            this.network.add(new Layer(layerSize, numWeights));
            numWeights = layerSize.intValue();
        }
        
        // remove the input layer it is not needed anymore
        this.network.remove(0);
        
    }
    
    /** Runs the network
     * Input a double[] of the input data
     * The input array should be the same length as the number of input neurons in the network
     * @param inputdata A double[] of the input data
     * @return A double[] of the output data*/
    public LinkedList<BigDecimal> run(LinkedList<BigDecimal> input){
        
        // confirm array of input data is compatible with number of input neurons
        if(input.size() != this.inputSize)
            System.out.println("Input array is not compatible with inputs in order to run this network!");
        
        
        LinkedList<BigDecimal> output = new LinkedList<>();
        
        // for every layer in the network
        for(Layer layer : this.network) {
            
            // run the layer and assign its output to the next layers input
            output = layer.runLayer(input);
            input = output;
        }
        
        // after all layers have ran, return the final output
        this.outputData = output;
        return output;
    }

    /** Returns the double[] of the final data this network produces
     * @return The final data*/
    public LinkedList<BigDecimal> getFinalData() {
        return this.outputData;
    }
    
    /** Trains the network
     * @param expected The expected out of this network when given the input
     * @param input The input given to the network*/
    public void train(LinkedList<BigDecimal> expected, LinkedList<BigDecimal> input) {
        
        if(expected.size() != this.network.getLast().size()) {
            System.out.println("Size of expected array is not compatible with output of this network!");
        }
        
        LinkedList<BigDecimal> output = this.run(input);
        BigDecimal originalCost = calculateCost(expected, output);
        BigDecimal costForHigher;
        BigDecimal costForLower;
        BigDecimal bias;
        BigDecimal higherWeight;
        BigDecimal lowerWeight;
        BigDecimal higherBias;
        BigDecimal lowerBias;
        int index;
        

        // for every layer in this network, starting with the last layer
        for(int i = this.network.size()-1; i >= 0; --i) {
            
            Layer layer = this.network.get(i);
            
            // for every neuron in that layer
            for(Neuron neuron : layer.toList()) {
                LinkedList<BigDecimal> weights = new LinkedList<>();
                index = 0;
                
                // for every weight in that neuron
                for(BigDecimal weight : neuron.getWeights()) {
                    
                    higherWeight = weight.add(this.learningRate);
                    lowerWeight = weight.subtract(this.learningRate);
                    
                    neuron.setWeight(index, higherWeight);
                    output = this.run(input);
                    costForHigher = calculateCost(expected, output);
                    
                    neuron.setWeight(index, lowerWeight);
                    output = this.run(input);
                    costForLower = calculateCost(expected, output);
                    
                    // if costs are the same, make no changes
                    
                    // if costForLower is higher than
                    if(costForLower.compareTo(costForHigher) > 0) {
                        weights.add(higherWeight);
                    } else if(costForLower.compareTo(costForHigher) < 0){
                        weights.add(lowerWeight);
                        
                    // if previous ifs == 0, then if original cost is greater, change bias to randomly to lower one
                    } else if(originalCost.compareTo(costForLower) > 0) {
                        weights.add(new BigDecimal(String.valueOf(Math.random())));
                    }
                    
                    ++index;
                }
                
                neuron.setWeights(weights);
                
                bias = neuron.getBias();
                higherBias = bias.add(this.learningRate);
                lowerBias = bias.subtract(this.learningRate);
                
                neuron.setBias(lowerBias);
                output = this.run(input);
                costForLower = calculateCost(expected, output);
                
                neuron.setBias(higherBias);
                output = this.run(input);
                costForHigher = calculateCost(expected, output);
                
                
                // if costs are the same, make no changes
                // if costForLower is higher than
                if(costForLower.compareTo(costForHigher) > 0) {
                    neuron.setBias(higherBias);
                } else if(costForLower.compareTo(costForHigher) < 0){
                    neuron.setBias(lowerBias);
                        
                // if previous ifs == 0, then if original cost is greater, change bias to randomly to lower one
                } else if(originalCost.compareTo(costForLower) > 0) {
                    bias = bias.add(new BigDecimal(String.valueOf(Math.random())));
                }
            }
        }
    }
    
    /** Calculates the cost of the network
     * Is given an expected output and the actual output
     * Returns a double of the sums of the differences between the two arrays
     * @param expected The expected output
     * @param output The actual output
     * @return The cost*/
    private BigDecimal calculateCost(LinkedList<BigDecimal> expected, LinkedList<BigDecimal> output) {
        
        if(expected.size() != output.size()) {
            System.out.println("Expected output is not the same size as actual output when calculating the cost!");
        }
        
        BigDecimal cost = new BigDecimal("0.0");
        
        // for every value in the expected list of values
        // get difference between expected and output, absolute value it, add to overall cost
        for(int i = 0; i < expected.size(); ++i) {
            
            BigDecimal subtraction = expected.get(i).subtract(output.get(i)).abs();
            
            cost = cost.add(subtraction);
            
        }
        
        return cost;
        
        
    }

}
