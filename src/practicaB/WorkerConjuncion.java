package practicaB;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class WorkerConjuncion extends SwingWorker<Void, Integer> {
	private WorkerSecuenciaBits w1;
	private WorkerSecuenciaBits w2;
	private Panel panel;
	private int len; // longitud que tiene que tener la lista
	List<Integer> sec = new ArrayList<>();
	private int indice = 0;

	public WorkerConjuncion(WorkerSecuenciaBits w1, WorkerSecuenciaBits w2, Panel panel, int n) {
		this.w1 = w1;
		this.w2 = w2;
		this.panel = panel;
		this.len = n;
	}

	public Void doInBackground() throws InterruptedException, ExecutionException {
		WorkerSecuenciaBits.l.lock();
		try {
			int num;
			for (int i = 0; i < len; i++) {
				while (i >= w1.getSize() || i >= w2.getSize()) {
					WorkerSecuenciaBits.c.await();
				}
				System.out.println("Escribiendo la lista de abajo " + i);
				num = w1.getByIndex(i) & w2.getByIndex(i);
				sec.add(num);
				publish(num);
				//this.setProgress(indice * 100 / len);
			}
		} finally {
			WorkerSecuenciaBits.l.unlock();
		}
		return null;
	}

	protected void process(List<Integer> lis) {
		for (Integer i : lis) {
			panel.escribeLista(i, 2, indice);
			this.setProgress(indice * 100 / len); //aqui o en el doinbg?
			indice++;
		}

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
