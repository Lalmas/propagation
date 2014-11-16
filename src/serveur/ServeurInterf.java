package serveur;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServeurInterf extends java.rmi.Remote 
{
	
	public String showDetails() throws RemoteException; 
		
	public String getFatherHost() throws RemoteException; 
	
	public int setFatherHost(String nom) throws RemoteException; 
	
	
	public ServeurInterf getSon(String nom) throws RemoteException; 
	
		
	public int addSon(ServeurInterf nom) throws RemoteException;
	
	
	public String getHost() throws RemoteException; 
	
	
	public int setHost(String nom) throws RemoteException; 
	
	
	public int bindHimSelf() throws RemoteException;
	
	
	public int propage(String message) throws RemoteException;
	
	
	
	
	
	
	

}
