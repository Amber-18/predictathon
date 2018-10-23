import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Network
{
    /** An ArrayList of Layers of Neurons in the Network*/    
    LinkedList<Layer> network;
    
    /** The output data of the network*/
    private LinkedList<BigDecimal> outputData;
    
    /** The learning rate of this network*/
    private BigDecimal learningCurve;
    
    /** Takes in an int[] holding the sizes of the layer of the network
     * The length of the array is the number of layers
     * The value of the int in each index is the number of neurons each layer is to have
     * @param layerSizes The int[] of layer sizes for the neural network*/
    public Network(int[] layerSizes) {
        
        this.learningCurve = new BigDecimal("0.01");
        
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
    public LinkedList<BigDecimal> run(LinkedList<BigDecimal> input){
        
        // confirm array of input data is compatible with number of input neurons
        if(input.size() != this.network.get(0).size()) {
            System.out.println("Input array is not compatible with inputs in order to run this network!");
        }
        
        LinkedList<BigDecimal> output = new LinkedList<>();
        
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
        
        this.run(input);
        
        LinkedList<BigDecimal> output = this.outputData;

        // for every layer in this network, starting with the last layer
        for(int layerX = this.network.size()-1; layerX > 0; --layerX) {
            Layer layer = this.network.get(layerX);
            
            // for every neuron in that layer
            for(int neuronX = 0; neuronX < layer.size(); ++neuronX) {
                Neuron neuron = layer.getNeuron(neuronX);
                
                // for every weight in that neuron
                for(int weightX = 0; weightX < neuron.size(); ++weightX) {
                    
                    BigDecimal weight = neuron.getWeight(weightX);
                    BigDecimal bias = neuron.getBias();
                    BigDecimal originalCost;
                    BigDecimal costForHigher;
                    BigDecimal costForLower;
                    
                    BigDecimal higherWeight = weight.add(this.learningCurve);
                    BigDecimal lowerWeight = weight.subtract(this.learningCurve);
                    BigDecimal higherBias = bias.add(this.learningCurve);
                    BigDecimal lowerBias = bias.subtract(this.learningCurve);
                    
                    originalCost = calculateCost(expected, output);
                    
                    neuron.setWeight(weightX, higherWeight);
                    output = this.run(input);
                    costForHigher = calculateCost(expected, output);
                    
                    neuron.setWeight(weightX, lowerWeight);
                    output = this.run(input);
                    costForLower = calculateCost(expected, output);
                    
                    // if costs are the same, make no changes
                    
                    // if costForLower is higher than
                    if(costForLower.compareTo(costForHigher) > 0) {
                        neuron.setWeight(weightX, higherWeight);
                    } else if(costForLower.compareTo(costForHigher) < 0){
                        neuron.setWeight(weightX, lowerWeight);
                        
                        // if previous ifs == 0, then if original cost is greater, change bias to randomly to lower one
                    } else if(originalCost.compareTo(costForLower) > 0) {
                        neuron.setWeight(weightX, lowerWeight);
                    }
                    
                    // do the same for the biases
                    neuron.setBias(higherBias);
                    output = this.run(input);
                    costForHigher = calculateCost(expected, output);
                    
                    neuron.setBias(lowerBias);
                    output = this.run(input);
                    costForLower = calculateCost(expected, output);
                    
                    // if costForLower is higher than
                    if(costForLower.compareTo(costForHigher) > 0) {
                        neuron.setBias(higherBias);
                    } else if(costForLower.compareTo(costForHigher) < 0){
                        neuron.setBias(lowerBias);
                        
                        // if previous ifs == 0, then if original cost is greater, change bias to randomly to lower one
                    } else if(originalCost.compareTo(costForLower) > 0) {
                        neuron.setBias(lowerBias);
                    }
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
