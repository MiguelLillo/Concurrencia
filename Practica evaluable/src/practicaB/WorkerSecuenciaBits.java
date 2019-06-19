package practicaB;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
public class WorkerSecuenciaBits extends SwingWorker<List<Integer>,Integer> {
	private Panel pan;
	private int n;	//numero de bits a producir
	private int a;	//esta a sirve para diferenciar el área sobre la que estamos trabajando
	int indice=0;
	public WorkerSecuenciaBits(Panel p,int n,int a) {
		pan=p;
		this.n=n;
		this.a=a;
	}
	public List<Integer> doInBackground(){
		Random r=new Random();
		List<Integer> l=new ArrayList<>();
		for(int i=0;i<n;i++) {
			System.out.println(a);
			publish(r.nextInt(2));
			//l.add(r.nextInt(2));	//publish
		}
		return null;
	}
	
	//sobreescribir process
	protected void process(List<Integer> bits) {
		
		
	}
	public void done() {
	
		try {
			pan.escribeLista(this.get(),a);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
