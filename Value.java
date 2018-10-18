
public class Value
{
    
    private String value;
    
    private String type;
    
    public Value(String type, String value) {
        this.value= value;
        this.type = type;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getType() {
        return this.type;
    }

}
