package client;

import interfaces.Task;
import java.io.Serializable;
import java.util.Scanner;
/**
 *
 * @author ndambuki
 */
public class AddVegetablePrice implements Task<VegetableHelper>, Serializable{
    private static final long serialVersionUID = 227L;

    @Override
    public VegetableHelper execute() {
       VegetableHelper veg=new VegetableHelper();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Veg Name");
        String n=sc.nextLine();
        System.out.println("Enter the Price: ");
        int p = sc.nextInt();
         veg.setName(n);
         veg.setPrice(p);
       
       return veg;
    }


        
}
