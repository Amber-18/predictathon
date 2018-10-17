import java.util.ArrayList;
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
     * @param types The various types a Person can have*/
    public People(String[] types) {
        this.types = new HashMap<>();
        
        for(String type : types) {
            this.types.put(type, new ArrayList<String>());
        }
        
        this.people = new ArrayList<>();
        this.importantTypes = new ArrayList<>();
    }
    
    /**Add a person*/
    public void add(Person person) {
        
        this.people.add(person);
        
        ArrayList<String> valuesOfAType;
        
        // for every type
        for(String type : this.types.keySet()) {
            
            valuesOfAType = this.types.get(type);
            
            if(!valuesOfAType.contains(person.getValue(type))) {
                
                valuesOfAType.add(person.getValue(type));
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
        StringBuffer x = new StringBuffer("\"" + type + "\"");
        type = String.valueOf(x);
        return this.potentialValues.get(type);
    }

    
    /** Adds the specific types and the various values associated with that type to the HashMap of types that 
     * should be used in analysis
     * @param type The specific type to be added*/
    public void addToImportantValues(String type) {
        
        StringBuffer x = new StringBuffer("\"" + type + "\"");
        type = String.valueOf(x);
        this.importantValues.add(type);
    }
    
    /** Returns the list of types that are important for analysis
     * @return The list of important types*/
    public ArrayList<String> getImportantTypes(){
        return this.importantValues;
    }
}
