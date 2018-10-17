import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class People
{
    /** The different values for every type that a Person has*/
    HashMap<String, ArrayList<String>> types;
    
    /** An ArrayList of Persons*/
    ArrayList<Person> people;
    
    /** The specific types a person can have that are used in analysis*/
    ArrayList<String> importantTypes;
    
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
    public ArrayList<String> getValuesOf(String type) {
        
        StringBuffer x = new StringBuffer("\"" + type + "\"");
        String a = String.valueOf(x);
        
        if(this.types.containsKey(a)) {
            return this.types.get(a);
        } else {
            ArrayList<String> y = new ArrayList<>();
            y.add("Doesn't hold that type!");
            return y;
        }
    }
    
    /** Returns an ArrayList<String> of all the different types 
     * @return An ArrayList<String> of all the different types */
    public ArrayList<String> getTypes() {
        ArrayList<String> listOfAllTypes = new ArrayList<>();
        listOfAllTypes.addAll(this.types.keySet());
        return listOfAllTypes;
    }
    
    /** Adds the specific types and the various values associated with that type to the HashMap of types that 
     * should be used in analysis
     * @param type The specific type to be added*/
    public void addToImportantTypes(String type) {
        
        StringBuffer x = new StringBuffer("\"" + type + "\"");
        type = String.valueOf(x);
        
        if(this.types.containsKey(type)) {
            this.importantTypes.add(type);
        } else {
            System.out.println("Doesn't hold that type!");
        }
    }
    
    /** Returns the list of types that are important for analysis
     * @return The list of important types*/
    public ArrayList<String> getImportantTypes(){
        return this.importantTypes;
    }
}
