package practica;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
public class WorkerSecuenciaBits extends SwingWorker<List<Integer>,Void> {
	private Panel pan;
	private int n;	//numero de bits a producir
	private int a;	//esta a sirve para diferenciar el área sobre la que estamos trabajando
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
			l.add(r.nextInt(2));	//publish
		}
		return l; //null
	}
	
	//sobreescribir process
	public void done() {
	
		try {
			pan.escribeLista(this.get(),a);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
