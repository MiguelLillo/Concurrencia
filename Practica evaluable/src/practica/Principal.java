package practica;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Principal {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame ventana = new JFrame("Práctica evaluable");
				crearGUI(ventana);
			}
		});

	}
	public static void crearGUI(JFrame v) {
		Panel panel=new Panel();
		Controlador ctr=new Controlador(panel);
		panel.controlador(ctr);
		
		v.setContentPane(panel);
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.pack();
		v.setVisible(true);
		
	}

}
