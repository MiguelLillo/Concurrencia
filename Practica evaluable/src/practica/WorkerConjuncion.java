package practica;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class WorkerConjuncion extends SwingWorker<List<Integer>,Void>{
	private WorkerSecuenciaBits w1;
	private WorkerSecuenciaBits w2;
	private Panel panel;
	public WorkerConjuncion(WorkerSecuenciaBits w1,WorkerSecuenciaBits w2, Panel panel) {
		this.w1=w1;
		this.w2=w2;
		this.panel=panel;
	}
	
	public List<Integer> doInBackground() throws InterruptedException, ExecutionException{
		List<Integer> l1=w1.get();
		List<Integer> l2=w2.get();
		List<Integer> res=new ArrayList<>();
		int n=l1.size();
		for(int i=0;i<n;i++) {
			res.add(l1.get(i)&l2.get(i));
			//panel.nProgreso(i*100/n);//asi no vale
		}
		return res;
	}
	public void done() {
		try {
			panel.escribeLista(get(), 2);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
