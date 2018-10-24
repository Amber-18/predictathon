import java.util.Iterator;

public class Pair <TheType1, TheType2>
{
    private final TheType1 value1;
    
    private final TheType2 value2;
    
    public Pair(TheType1 value1, TheType2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
    
    public TheType2 get(TheType1 value1) {
        return this.value2;
    }
    
    public TheType1 getKey() {
        return this.value1;
    }
    
    public TheType2 getValue() {
        return this.value2;
    }
}
