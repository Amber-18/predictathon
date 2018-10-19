
public class Neuron
{
    /** The bias of the neuron */
    private double bias;
    
    /** The array of the weights of connections to previous layer of neurons*/
    private double[] weights;
    
    /** The value of the neuron after calculations*/
    private double value;
    
    /** Constructor of a Neuron object
     * @param bias The double value of the bias of the neuron
     * @param weights A double[] of the weights*/
    public Neuron(double bias, double[] weights) {
        this.value = Double.NaN;
        this.bias = bias;
        this.weights = weights;
    }
    
    /** Sets the value of a specific weight
     * @param index The specific weight to set
     * @param value The value of the weight to be set*/
    public void setWeight(int index, double value) {
        this.weights[index] = value;
    }
    
    /** Sets the value of a specific weight
     * @param value The value of the bias*/
    public void setBias(double value) {
        this.bias = value;
    }
    
    /** Returns the bias of the neuron
     * @return The bias of the neuron*/
    public double getBias() {
        return this.bias;
    }
    
    /** Returns the specific weight of the neuron
     * @return The specific weight of the neuron*/
    public double getWeight(int i) {
        return this.weights[i];
    }
    
    /** Returns the double value of this neuron
     * @return The double value of this neuron*/
    public double getValue() {
        return this.value;
    }
    
    /** Calculates the value of the neuron
     * Should be passed an int[] of values
     * Sums the products of each weight multipled by each respective value and add this neurons bias
     * It them returns the value of the neuron
     * @param values double[] of the values of each neuron in the previous layer
     * @return A double of the neurons value*/
    public double calculate(double[] values){
        
        // the sum of the products of each weight multiplied by each respective value of the previous
        double sum = 0;
        
        // calculate the sum
        for(int i = 0; i < values.length; ++i) {
            sum = sum + (values[i] * this.weights[i]);
        }
        
        // add the bias, pass through sigmoid, set the neurons value, and return
        sum = sum + bias;
        sum = sigmoid(sum);
        this.value = sum;
        
        return this.value;
    
    }
        
    /**Applies the sigmoid function on the parameter and returns the output as a double
     * @param x The input of the sigmoid function
     * @return The output of the sigmoid function*/
    public static double sigmoid(double x){

        x = 1 / (1 + Math.exp((-1)*x));
        return x;

    }
    
    
    
    

}
