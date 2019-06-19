package practicaB;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.*;
public class WorkerSecuenciaBits extends SwingWorker<Void,Integer> {
	private Panel pan;
	private int n;	//numero de bits a producir
	private int a;	//esta a sirve para diferenciar el área sobre la que estamos trabajando
	List<Integer> secuencia=new ArrayList<>();
	static Lock l=new ReentrantLock();
	static Condition c=l.newCondition();
	int indice=0;
	public WorkerSecuenciaBits(Panel p,int n,int a) {
		pan=p;
		this.n=n;
		this.a=a;
	}
	public Void doInBackground()throws InterruptedException, ExecutionException{
		
		Random r=new Random();
		int bit;
		
			for(int i=0;i<n;i++) {
				l.lock();
				System.out.println(a);
				bit=r.nextInt(2);
				secuencia.add(bit);
				publish(bit);
				c.signal();
				l.unlock();
			}
		return null;
	}
	
	//sobreescribir process
	protected void process(List<Integer> lis) {
		pan.escribeLista(lis.get(indice),a);//lis o sec?
		indice++;
		
	}
	public List<Integer> getLista(){
		return secuencia;
	}
//	public void done() {
//	
//		try {
//			pan.escribeLista(this.get(),a);
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
//	}
}
