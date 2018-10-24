import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;

import org.nevec.rjm.BigDecimalMath;

public class test
{

    public static void main(String[] args)
    {
        LinkedList<Integer> layerSizes = new LinkedList<>();
        layerSizes.add(2);
        layerSizes.add(5);
        layerSizes.add(1);
        Network net = new Network(layerSizes, "0.10");
        
        LinkedList<BigDecimal> input1 = new LinkedList<>();
        LinkedList<BigDecimal> input2 = new LinkedList<>();
        LinkedList<BigDecimal> expect1 = new LinkedList<>();
        LinkedList<BigDecimal> expect2 = new LinkedList<>();
        LinkedList<BigDecimal> output = new LinkedList<>();
        
        input1.add(new BigDecimal("0.35"));
        input1.add(new BigDecimal("0.65"));
        expect1.add(new BigDecimal("0.25"));
        
        input2.add(new BigDecimal("0.65"));
        input2.add(new BigDecimal("0.35"));
        expect2.add(new BigDecimal("0.85"));
        
        System.out.println(net.run(input1));
        net.train(expect1, input1);
        System.out.println(net.run(input1));
        for(int i = 0; i < 500; ++i) {
            // net.train(expect1, input1);
            net.train(expect2, input2);
            net.train(expect1, input1);
            
            System.out.println("Getting there: " + i);
            
            if(i%5 == 0) {
               System.out.println(net.run(input1));
            }
        }
        
        
        //System.out.println(output);

    }

}
