package practicaB;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class Controlador implements ActionListener, PropertyChangeListener{
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
				WorkerConjuncion wcon=new WorkerConjuncion(w,w1,panel,n);
				wcon.addPropertyChangeListener(this);
				w.execute();
				w1.execute();
				wcon.execute();
			} catch (Exception ie) {
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getPropertyName().equals("progress")){
			panel.nProgreso((Integer) evt.getNewValue());
		}
		
	}

}
