import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;

public class Layer
{
    /** The list of neurons in this layer*/
    private LinkedList<Neuron> layer;
    
    /** The index of this layer in the network
     * The input layer has an index of 0*/
    private int index;
    
    /** Constructor for a Layer of Neurons 
     * To be used in a Neural Network
     * @param numberOfNeurons The number of neurons in this layer*/
    public Layer(int numberOfNeurons, int numberOfPreviousNeurons, int index) {
        this.layer = new LinkedList<>();
        
        this.index = index;
        
        if(index > 0) {
            // for every neuron in this layer, randomize weight for each connection to previous neurons and bias
            for(int i = 0; i < numberOfNeurons; ++i) {
                BigDecimal bias = new BigDecimal(String.valueOf(Math.random()));
                LinkedList<BigDecimal> weights = randomizeWeights(numberOfPreviousNeurons);
                this.layer.add(new Neuron(bias, weights));
            }
        } else {
            for(int i = 0; i < numberOfNeurons; ++i) {
                BigDecimal bias = new BigDecimal("0.0");
                LinkedList<BigDecimal> weights = randomizeWeights(0);
                this.layer.add(new Neuron(bias, weights));
            }
            
        }
        
    }
    
    /** Returns the  Neuron in this layer at the index provided
     * @param The index of the Neuron in this layer
     * @return The Neuron located at the index*/
    public Neuron getNeuron(int index) {
        return this.layer.get(index);
    }
    
    /** Adds the  Neuron to this Layer
     * @param The Neuron to be added*/
    public void add(Neuron neuron) {
        this.layer.add(neuron);
    }
    
    /** Runs the currents layer using the LinkedList<BigDecimal> of inputs passed to it
     * Returns a LinkedList<BigDecimal> of the output of this layer
     * @param inputs The input data
     * @return The output data*/
    public LinkedList<BigDecimal> runLayer(LinkedList<BigDecimal> inputs) {
        LinkedList<Neuron> layer = this.layer;
        
        LinkedList<BigDecimal> output = new LinkedList<>();
        
        // for every neuron in this layer, calculate that neuron using the inputs
        for(int i = 0; i < layer.size(); ++i) {
            output.add(layer.get(i).calculate(inputs));
        }
        
        return output;
    }

    /** Randomizes the values in a double[]
     * All values are greater than and equal to 0.0 and less than 1.0 as specificed in Math.random()
     * 0.0 <= x < 1.0
     * @param values The double[] of values to be randomized
     * @return The double[] of randomized values*/
    private LinkedList<BigDecimal> randomizeWeights(int numWeights) {
        
        LinkedList<BigDecimal> weights = new LinkedList<>();
        
        // numWeight = 0, this is input layer, so weight = 0 and size = 1
        if(numWeights == 0) {
            weights.add(new BigDecimal("0"));
            return weights;
        } 
        
        // for every weight in the list, randomize that value, and return that array
        for(int i = 0; i < numWeights; ++i) {
            weights.add(new BigDecimal(String.valueOf(Math.random())));
        }
        
        return weights;
    }
    
    /** Return the size of this layer
     * @return the size*/
    public int size() {
        return this.layer.size();
    }
}
