
public class Value
{
    
    private String value;
    
    private String type;
    
    private boolean isImportant;
    
    public Value(String type, String value) {
        this.value= value;
        this.type = type;
    }
    
    public void isImportant(boolean isImportant) {
        this.isImportant = isImportant;
    }
    
    public boolean isImportant() {
        return this.isImportant;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getType() {
        return this.type;
    }

}
