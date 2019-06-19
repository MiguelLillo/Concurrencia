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
	private int indice=0;
	public WorkerSecuenciaBits(Panel p,int n,int a) {
		pan=p;
		this.n=n;
		this.a=a;
	}
	public Void doInBackground()throws InterruptedException, ExecutionException{
		
		Random r=new Random();
		int bit;
		
			for(int i=0;i<n;i++) {
				Thread.sleep(1);
				l.lock();
				try {
					System.out.println(a);
					bit=r.nextInt(2);
					secuencia.add(bit);
					publish(bit);
					c.signal();
				}finally {
					l.unlock();
				}
			}
		return null;
	}
	
	//sobreescribir process
	protected void process(List<Integer> lis) {
		for (Integer i:lis) {
			pan.escribeLista(i,a,indice);
			indice++;
		}
		
	}
	public List<Integer> getLista(){
		return secuencia;
	}
	public int getSize() {
		return secuencia.size();
	}
	public Integer getByIndex(int idx) {
		return secuencia.get(idx);
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
