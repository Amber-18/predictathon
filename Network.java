import java.util.ArrayList;
import java.util.HashMap;

public class Network
{
    /** A Map of the neurons for each layer in the network*/
    private HashMap<Integer, ArrayList<Neuron>> network;
    
    /** The output data of the network*/
    private double[] outputData;
    
    public Network(int[] layerSizes) {
        
        this.network = new HashMap<>();
        ArrayList<Neuron> layer = new ArrayList<>();
        double bias;
        double[] weights;
        int sizeOfLayer;
        int sizeOfPreviousLayer;
        
        // for every layer in the network
        for(int layerIndex = 1; layerIndex < layerSizes.length; ++layerIndex) {
            
            // get the size of that layer and the previous layer
            sizeOfLayer = layerSizes[layerIndex];
            sizeOfPreviousLayer = layerSizes[layerIndex-1];
            
            // for every neuron in that layer, randomize the neuron, and add it to layer
            for(int neuron = 0; neuron < sizeOfLayer; ++neuron) {
                bias = Math.random();
                weights = randomize(new double[sizeOfPreviousLayer]);
                layer.add(new Neuron(layerIndex, bias, weights));
            }
            
            // add the layer of neurons to the network
            this.network.put(new Integer(layerIndex), layer);
            
        }
        
    }
    
    /** Randomizes the values in a double[]
     * All values are greater than and equal to 0.0 and less than 1.0 as specificed in Math.random()
     * 0.0 <= x < 1.0
     * @param values The double[] of values to be randomized
     * @return The double[] of randomized values*/
    private double[] randomize(double[] values) {
        
        // for every value in values, randomize that value, and return that array
        for(int i = 0; i < values.length; ++i) {
            values[i] = Math.random();
        }
        
        return values;
    }
    
    /** Runs the network
     * @param inputdata A double[] of the input data
     * @return A double[] of the output data*/
    public double[] run(double[] inputData){
        
        Neuron neuron;
        double[] outputData = new double[0];

        // for every layer in the network after the input layer
        for(int layerNumber = 1; layerNumber < this.network.size(); ++layerNumber){
            
            // output data for every neuron this layer
            outputData = new double[this.network.get(layerNumber).size()];
            
            // for every  neuron in that layer, calculate the value of that neuron
            for(int neuronIndex = 0; neuronIndex <this.network.get(layerNumber).size(); ++neuronIndex) {
                neuron = this.network.get(layerNumber).get(neuronIndex);
                neuron.calculate(inputData);
                
                outputData[neuronIndex] = neuron.getValue();
            }
            
            // the output data for this layer is the input data for the next layer
            inputData = outputData;
            
            
            
            
        }
        
        // after iterating through every layer in the network return the output data
        this.outputData = outputData;
        return outputData;
    }
    
    /** Evaluates TODO*/
    public void train() {
        // 
        // TODO
    }

}
