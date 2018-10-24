import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import org.nevec.rjm.BigDecimalMath;

public class Neuron
{
    /** The bias of the neuron */
    private BigDecimal bias;
    
    /** The array of the weights of connections to previous layer of neurons*/
    private LinkedList<BigDecimal> weights;
    
    /** The value of the neuron after calculations*/
    private BigDecimal value;
    
    /** Constructor of a Neuron object
     * @param bias The double value of the bias of the neuron
     * @param weights A double[] of the weights*/
    public Neuron(BigDecimal bias, LinkedList<BigDecimal> weights) {
        this.value = new BigDecimal("0.0");
        this.bias = bias;
        this.weights = weights;
    }
    
    /** Sets the value of a specific weight
     * @param index The specific weight to set
     * @param value The value of the weight to be set*/
    public void setWeights(LinkedList<BigDecimal> weights) {
        this.weights = weights;
    }
    
    public void setWeight(int index, BigDecimal weight) {
        this.weights.set(index, weight);
    }
    
    /** Sets the value of a specific weight
     * @param value The value of the bias*/
    public void setBias(BigDecimal value) {
        this.bias = value;
    }
    
    /** Returns the bias of the neuron
     * @return The bias of the neuron*/
    public BigDecimal getBias() {
        return this.bias;
    }
    
    /** Returns the specific weight of the neuron
     * @return The specific weight of the neuron*/
    public BigDecimal getWeight(int i) {
        return this.weights.get(i);
    }
    
    /** Returns the BigDecimal value of this neuron
     * @return The BigDecimal object of the value of this neuron*/
    public BigDecimal getValue() {
        return this.value;
    }
    
    /** Calculates the value of the neuron
     * Should be passed an int[] of values
     * Sums the products of each weight multipled by each respective value and add this neurons bias
     * It them returns the value of the neuron
     * @param values double[] of the values of each neuron in the previous layer
     * @return A double of the neurons value*/
    public BigDecimal calculate(LinkedList<BigDecimal> values){
        
        if(values.size() != this.weights.size()) {
            System.out.println("Input values for this neuron are not compatible with the number of weights this neuron has!");
        }
        // the sum of the products of each weight multiplied by each respective value of the previous
        BigDecimal sum = new BigDecimal(0);
        BigDecimal WxV = new BigDecimal(0);
        
        List<Pair<BigDecimal, BigDecimal>> pairs = zip(values);
        
        // calculate the sum
        for(Pair<BigDecimal, BigDecimal> pair : pairs) {
            WxV = pair.getKey().multiply(pair.getValue());
            sum = sum.add(WxV);
        }
        
        // add the bias, pass through sigmoid, set the neurons value, and return
        sum = sum.add(this.bias);
        sum = sigmoid(sum);
        this.value = sum;
        
        // return the value of this neuron
        return this.value;
    
    }
        
    /**Applies the sigmoid function on the parameter and returns the output as a BigDecimal
     * @param x The input of the sigmoid function
     * @return The output of the sigmoid function*/
    public static BigDecimal sigmoid(BigDecimal x){
        
        // create a mathcontext object, 220 precision
        MathContext mc = new MathContext(120, RoundingMode.HALF_EVEN);
        BigDecimal one = new BigDecimal("1");
        
        // get e for the sigmoid
        BigDecimal e = BigDecimalMath.exp(mc);
        
        // e ^ x
        BigDecimal result = BigDecimalMath.pow(e, x);
        
        // e^-x = 1 / e^x
        result = one.divide(result, mc);
        
        // add one
        result = result.add(one);
        
        // divide by one again
        result = one.divide(result, mc);
        
        return result;

    }
    
    private LinkedList<Pair<BigDecimal, BigDecimal>> zip(LinkedList<BigDecimal> values) {
        LinkedList<Pair<BigDecimal, BigDecimal>> pairs = new LinkedList<>();
        
        for(int i = 0; i < values.size(); ++i) {
            pairs.add(new Pair<BigDecimal, BigDecimal>(this.weights.get(i), values.get(i)));
        }
        
        return pairs;
    }
    
    /** Returns the number of weights this neuron has
     * @return The number of weights*/
    public int size() {
        return this.weights.size();
    }
    
    public LinkedList<BigDecimal> getWeights(){
        return this.weights;
    }
    
    

}
