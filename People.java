import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class People
{
    /** A map for every potential value for every type of value*/
    HashMap<String, ArrayList<String>> potentialValues;
    
    /** An ArrayList of Persons*/
    ArrayList<Person> people;
    
    /** The specific types of values that are to be used in analysis*/
    ArrayList<String> importantValues;
    
    
    
    
    
    
    /** Constructor for People object
     * @param directory The data files directory
     * @throws IOException may throw this exception*/
    public People(String directory, String numberOfPeople) throws IOException {
        
        // create the starting fields
        this.potentialValues = new HashMap<>();
        this.people = new ArrayList<Person>();
        this.importantValues = new ArrayList<String>();

        
        // string[] the names of the values
        String[] listOfValues;
        // string[] of the data for the person
        String[] arrayOfValuesForThisPerson;
        
        
        // read in the file and create an array of types of values
        BufferedReader bf = new BufferedReader(new FileReader(directory));
        String listOfValuesLongString = bf.readLine();
        listOfValues = listOfValuesLongString.split(",");
        
        for(String type : listOfValues) {
            this.potentialValues.put(type, new ArrayList<String>());
        }
        
        
        // prime the while loop, read in the first array of values
        String valuesForAPersonLongString = bf.readLine();
        arrayOfValuesForThisPerson = valuesForAPersonLongString.split(",");
        
        
        
        
        if(numberOfPeople.equals("max")) {
            
            while(valuesForAPersonLongString != null) {
                arrayOfValuesForThisPerson = valuesForAPersonLongString.split(",");
                this.add(new Person(listOfValues, arrayOfValuesForThisPerson));
                valuesForAPersonLongString = bf.readLine();
            }
            
        } else {
            
            int i = 0;
            int max = Integer.parseInt(numberOfPeople);
            
            while(i < max) {
                arrayOfValuesForThisPerson = valuesForAPersonLongString.split(",");
                this.add(new Person(listOfValues, arrayOfValuesForThisPerson));
                valuesForAPersonLongString = bf.readLine();
                ++i;
            }
            
        }
        
        
        bf.close();
        
        
    }
    
    /**Add a person*/
    public void add(Person person) {
        
        this.people.add(person);
        
        ArrayList<String> valuesOfAType;
        
        // for every type
        for(String type : this.potentialValues.keySet()) {
            
            valuesOfAType = this.potentialValues.get(type);
            
            if(!valuesOfAType.contains(person.getValueFor(type))) {
                
                valuesOfAType.add(person.getValueFor(type));
            }
        }
        
        
    }
    
    /** Returns the Person at the index provided
     * @param i The index of the Person to be returned*/
    public Person getPerson(int i) {
        return this.people.get(i);
    }
    
    /** Returns an ArrayList<String> holding all the different kinds of values for the type
     * @param type The type to be analyzed
     * @return An ArrayList<String> of the different values type has */
    public ArrayList<String> getPotentialValuesOf(String type) {
        //StringBuffer x = new StringBuffer("\"" + type + "\"");
        //type = String.valueOf(x);
        
        try {
            this.potentialValues.get(type);
        } catch(Exception e) {
            System.out.println("Exception thrown in accessing potential values of " + type + " in class People!");
        }
        
        Collections.sort(this.potentialValues.get(type));
        return this.potentialValues.get(type);
    }
    
    /** Constructs the input array for the person object passed to it
     * using the ArrayList<String> of important values
     * Use addToImportantValues to modify this field
     * @param person The person whose data will be used to construct the input vector/array
     * @return A double[] of the input data*/
    public double[] constructInputArray(Person person) {
        
        Collections.sort(this.importantValues);
        
        int inputLayerSize = 1000;
        
        double[] input = new double[inputLayerSize];
        int i = 0;
        
        inputLayerSize = 0;
        
        for(String type : this.importantValues) {
            
            
            try {
                
                // see if value of the type given is a double
                input[i] = Double.valueOf(person.getValueFor(type));
                inputLayerSize += 1; 
                
            } catch(Exception e) {
                
                // if value of the type is not a double, get list of potential values and iterate through that instead
                ArrayList<String> potentialValues = this.getPotentialValuesOf(type);
                
                inputLayerSize += potentialValues.size();
                
                for(String potentialValue : potentialValues) {
                    
                    if(person.getValueFor(type).equals(potentialValue)) {
                        input[i] = 1;
                    } else {
                        input[i] = 0.0;
                    }
                    
                    ++i;
                }
                
                continue;
            }
            
            ++i;
        }
        
        
        // we now have an double[] input with all the inputs
        // the arraylist should have all been sorted before using so that every input array is passing in the same vector
        // for each neuron
        
        // remember
        // the double[] input is oversize
        // all of the empty indices are 0.0
        // you set the "potential-values" inputs as Double.NaN
        // now need to cut to a perfect size array
        // change all the Double.NaN to 0.0
        
        // this is what the method below does
        // now return the input array
        return fixInputArray(input, inputLayerSize);
        
    }
    
    private static double[] fixInputArray(double[] oldInput, int size) {
        
        double[] newInput = new double[size];
        
        for(int i = 0; i < newInput.length; ++i) {
            newInput[i] = oldInput[i];
        }
        
        return newInput;
        
    }

    
    /** Adds the specific types and the various values associated with that type to the HashMap of types that 
     * should be used in analysis
     * @param type The specific type to be added*/
     public void addToImportantValues(String type) {
        
        StringBuffer x = new StringBuffer("\"" + type + "\"");
        type = String.valueOf(x);
        this.importantValues.add(type);
        Collections.sort(this.importantValues);
    }
    
    /** Returns the list of types that are important for analysis
     * @return The list of important types*/
    public ArrayList<String> getListOfImportantValues(){
        Collections.sort(this.importantValues);
        return this.importantValues;
    }
}
