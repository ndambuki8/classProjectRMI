package client;

import interfaces.Compute;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author ndambuki
 */
public class VegetableComputeTaskRegistry {
     public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "vegetable";
            Registry registry = LocateRegistry.getRegistry();
            Compute comp = (Compute) registry.lookup(name);

            AddVegetablePrice task = new AddVegetablePrice();
              VegetableHelper add = comp.executeTask(task);
              System.out.println(add);
              
              UpdateVegetablePrice uvp= new UpdateVegetablePrice();
             
              DeleteVegetablePrice dvp = new DeleteVegetablePrice();
              
              CalcVegetableCost cvp = new CalcVegetableCost();
              
              CalculateCost cc = new CalculateCost();
              
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }    
    
}
