import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;

public class Layer
{
    /** The list of neurons in this layer*/
    private LinkedList<Neuron> layer;
    
    /** Constructor for a Layer of Neurons 
     * To be used in a Neural Network
     * @param numberOfNeurons The number of neurons in this layer*/
    public Layer(int numberOfNeurons, int numWeights) {
        
        this.layer = new LinkedList<>();
        
        if(numWeights == 0) {
            // input layer is left empty, all inputs are sent to the first hidden layer for calculations
        } else {
            for(int i = 0; i < numberOfNeurons; ++i) {
                BigDecimal bias = new BigDecimal(String.valueOf(Math.random()));
                LinkedList<BigDecimal> weights = randomizeWeights(numWeights);
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
    
    public LinkedList<Neuron> toList() {
        return this.layer;
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
        
        LinkedList<BigDecimal> output = new LinkedList<>();
        
        // for every neuron in this layer, calculate that neuron using the inputs
        for(Neuron neuron : this.layer) {
            output.add(neuron.calculate(inputs));
        }
        
        return output;
    }

    /** Randomizes the weights of a neuron
     * All values are greater than and equal to 0.0 and less than 1.0 as specificed in Math.random()
     * 0.0 <= x < 1.0
     * @param values The int value of the the number weights to be randomized/calculated
     * @return The LinkedList<BigDecimal> of randomized values*/
    private LinkedList<BigDecimal> randomizeWeights(int numWeights) {
        
        LinkedList<BigDecimal> weights = new LinkedList<>();
        
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
