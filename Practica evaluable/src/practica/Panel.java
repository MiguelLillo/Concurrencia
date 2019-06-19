package practica;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.*;


@SuppressWarnings("serial")
public class Panel extends JPanel{
	private JLabel eti=new JLabel("¿Cuántos bits quieres producir");
	private JTextField bits=new JTextField(8);
	private JTextArea a1=new JTextArea(15,20);
	private JTextArea a2=new JTextArea(15,20);
	private JTextArea res=new JTextArea(15,42);
	private JScrollPane scroll = new JScrollPane(a1);
	private JScrollPane scroll1 = new JScrollPane(a2);
	private JScrollPane scroll2 = new JScrollPane(res);
	private JTextArea[] areas=new JTextArea[3];
	
	private JLabel sec1=new JLabel("Secuencia 1");
	private JLabel sec2=new JLabel("Secuencia 2");
	private JLabel sec12=new JLabel("Secuencia 1 & Secuencia 2");
	
	private JProgressBar prog=new JProgressBar(0,100);
	public Panel() {
		areas[0]=a1;
		areas[1]=a2;
		areas[2]=res;
		String NORTH=BorderLayout.NORTH;
		String WEST=BorderLayout.WEST;
		String CENTER=BorderLayout.CENTER;
		String EAST=BorderLayout.EAST;
		String SOUTH=BorderLayout.SOUTH;
		JPanel nor=new JPanel(new FlowLayout());
		nor.add(eti);
		nor.add(bits);
		
		JPanel west=new JPanel(new BorderLayout());
		west.add(scroll,NORTH);
		west.add(sec1,SOUTH);
		JPanel east=new JPanel(new BorderLayout());
		east.add(scroll1,NORTH);
		east.add(sec2,SOUTH);
		JPanel sur=new JPanel(new BorderLayout());
		sur.add(scroll2,NORTH);
		sur.add(sec12,SOUTH);
		JPanel cen=new JPanel(new BorderLayout());
		cen.add(west,WEST);
		cen.add(east,EAST);
		cen.add(sur,SOUTH);
		
		this.setLayout(new BorderLayout());
		this.add(nor,NORTH);
		this.add(cen,CENTER);
		prog.setStringPainted(true);
		this.add(prog,SOUTH);		
	}
	public int getBits() {
		return Integer.parseInt(bits.getText());
	}
	public void textoArea(String tx,int area) {
		areas[area].setText(tx);
	}
	public void limpiaAreas() {
		areas[0].setText("");
		areas[1].setText("");
		areas[2].setText("");
	}
	public void controlador(Controlador ctr){
		bits.addActionListener(ctr);
		bits.setActionCommand("BITS");
	}
	public void escribeLista(List<Integer> l,int area) {
		int esp;
		esp=area==2?62:27;
		for(int i=0;i<l.size();i++) {
			areas[area].append(l.get(i).toString());
			if ((i+1)%esp==0) areas[area].append("\n");
		}
	}
	public void nProgreso(int n){
		prog.setValue(n);
		//setProgress
	}
}
