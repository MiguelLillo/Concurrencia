package practicaB;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class WorkerConjuncion extends SwingWorker<Void, Integer>{
	private WorkerSecuenciaBits w1;
	private WorkerSecuenciaBits w2;
	private Panel panel;
	private int len;	//longitud que tiene que tener la lista
	List<Integer> sec=new ArrayList<>();
	private int indice;	//posicion por la que vamos
	public WorkerConjuncion(WorkerSecuenciaBits w1,WorkerSecuenciaBits w2, Panel panel, int n) {
		this.w1=w1;
		this.w2=w2;
		this.panel=panel;
		this.len=n;
	}
	
	public Void doInBackground() throws InterruptedException, ExecutionException{
		WorkerSecuenciaBits.l.lock();
		//WorkerSecuenciaBits.c.await();
		
		List<Integer> l1;
		List<Integer> l2;
		int num;
		for(int i=0;i<len;i++) {
			l1=w1.getLista();
			l2=w2.getLista();
			while(i>=l1.size() ||i>=l2.size()			) {
				WorkerSecuenciaBits.c.await();
			}
			System.out.println("Escribiendo la lista de abajo");
			num=l1.get(i)&l2.get(i);
			sec.add(num);
			publish(num);
			//panel.nProgreso(i*100/n);//asi no vale
		}
		WorkerSecuenciaBits.l.unlock();
		return null;
	}
	protected void process(List<Integer> lis) {
		panel.escribeLista(lis.get(indice),2);	//lis o sec?
		indice++;
		
	}
//	public void done() {
//		try {
//			panel.escribeLista(get(), 2);
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}

	
}
