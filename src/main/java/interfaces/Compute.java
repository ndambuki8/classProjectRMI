package interfaces;

import interfaces.Task;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ndambuki
 */
public interface Compute extends Remote{
    <T> T executeTask(Task<T> t) throws RemoteException;
    
}
