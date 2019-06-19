package practica;

import java.awt.event.*;

public class Controlador implements ActionListener {
	private Panel panel;

	public Controlador(Panel panel) {
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("BITS")) {
			
			try {
				int n = panel.getBits();
				panel.limpiaAreas();
				panel.nProgreso(0);
				WorkerSecuenciaBits w = new WorkerSecuenciaBits(panel,n,0);
				WorkerSecuenciaBits w1 = new WorkerSecuenciaBits(panel,n,1);
				WorkerConjuncion wcon=new WorkerConjuncion(w,w1,panel);
				//wcon.addPropertyChangeListener(this);
				w.execute();
				w1.execute();
				wcon.execute();
				// panel.escribeLista(listaPrimos(n));//tarea costosa
			} catch (Exception ie) {
			}
		}
	}

}
