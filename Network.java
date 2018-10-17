
public class Network{
    /**class creates a Network object with variables below

    /**number of layers in the network*/
    private int numLayers;

    /**sizes of each layer in the network organized in an array*/
    private int[] layerSizes;

    /**sizes of outer layers*/
    private int inputSize;
    private int outputSize;

    /**triple array for the weights, each connection has a weight
    * 1 is layer, 2 is current neuron,
    * 3 is previous neuron it is connected to*/
    private double[][][] weights;

    /**double array for the biases, each neuron has a bias
    * 1 is layer number, input layer is found at [0]
    * 2 is neuron number, first neuron is found at [0]*/
    private double[][] bias;

    /**value equal to the number of neurons in the largest layer of the network*/
    private int max;

    /** double array for the output data created by each iteration of 
    * computation through the layers of the network
    * eventually output will hold the real output data because 
    * it will have reached the last layer*/ 
    private double[] output;

    /**Constructor for the network
     * Length of array is the number of layers
     * Each index in the array is the number of neurons in that layer
     * @param layerSizes int[] of the sizes of each layer in the network*/
    public Network(int[] layerSizes){
        this.layerSizes = layerSizes;
        this.numLayers = layerSizes.length;
        this.inputSize = layerSizes[0];
        this.outputSize = layerSizes[numLayers - 1];

        // calculate the max
        this.max = 0;
        for(int i = 0; i < numLayers; ++i){
            if(this.max < layerSizes[i]){
                this.max = layerSizes[i];
            }
        }


        this.weights = new double[numLayers][max][max];
        this.bias = new double[numLayers][max];
        this.output = new double[max];
        
    }

    /** Runs the network
     * @param inputdata A double[] of the input data
     * @return A double[] of the output data*/
    public double[] run(double[] inputdata){
        
        // input data in input as paramter double array, assign values in this 
        // array to output
        for(int i = 0; i < inputdata.length; ++i){
            this.output[i] = inputdata[i];
        }

        // randomize the weights and biases
        //  for(int i = 1; i <= this.numLayers; ++i){
        //      randomize(i);
        //  }

        // step through each layer of the network, doing all the computations
        for(int i = 1; i < this.numLayers; ++i){
            analyze(i);
        }

        double[] outputdata = new double[outputSize];

        for(int i = 0; i < this.outputSize; ++i){
            outputdata[i] = this.output[i];
        }

        return outputdata;
    }

    /**Applies the sigmoid function on the parameter and returns the output as a double
     * @param x The input of the sigmoid function
     * @return The output of the sigmoid function*/
    private static double sigmoid(double x){

        x = 1 / (1 + Math.exp((-1)*x));
        return x;

    }

    /** Method used by "run" to do the calcuations for each layer*/
    private void analyze(int currentLayer){
        
        // currentLayer must be 2 or greater, must start at first hidden layer
        // else you get error, see numPrevNeurons below, if you start at input, there
        // is no previous layer --> error
        // input layer has a value of 1, so input layer 1, layerSizes[0] --> input layer
        // get number of neurons in the current layer, start at the first hidden layer
        // in other words, start of layer number 2, layerSizes[1] --> first hidden layer
        
        // number of neurons in current layer
        int numCurrentNeurons = this.layerSizes[currentLayer];
        
        // number of neurons in previous layer
        int numPrevNeurons = this.layerSizes[currentLayer - 1];

        //value of the sum --> weight*value of all connections
        //add bias to sum, multiply by sigmoid, store result in current neuron
        double sum = 0;

        //for loop stepping through all the neuron in the current layer
        //storing the value in the output array
        for(int i = 0; i < numCurrentNeurons; ++i){
            
            //create a for loop to step through all the connections
            //connecting to the previous layer from the current layer
            //one neuron at a time
            for(int k = 0; k < numPrevNeurons; k++){

                //triple array for the weights, each connection has a weight
                //1 is layer number, 2 is current neuron,
                //3 is previous neuron it is connected to
                sum = this.weights[currentLayer][i][k] * this.output[i];
            }

            //double array for the biases, each neuron has a bias
            //1 is layer number, input has value of 0
            //2 is neuron number, first neuron has value of 0
            sum = sum + this.bias[currentLayer][i];

            // multiply by the sigmoid function
            sum = sigmoid(sum);

            // store result in the current neuron
            this.output[i] = sum;

            
        }

        // do you need to return this.output to run() ? I think not but maybe it is so?
    
    }

    public void randomize(){
        int currentLayer;
        
        for(int j = 1; j < this.numLayers; ++j){
            
            currentLayer = j;

        // step through each connection just like analyze function
        // randomize the weight of each connection
        int numCurrentNeurons = this.layerSizes[currentLayer];

        int numPrevNeurons = this.layerSizes[currentLayer - 1];
        
        // for every current neuron
        for(int i = 0; i < numCurrentNeurons; ++i){
            
            // randomize the bias
            this.bias[currentLayer][i] = Math.random();
            
            // for every previous neuron connected to the current neuron, randomize that connection
            for(int k = 0; k < numPrevNeurons; k++){

                this.weights[currentLayer][i][k] = Math.random();
            }
        }
        }
    }
}
