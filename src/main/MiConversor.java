package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import claseenum.EnumMoneda;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MiConversor  {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox<?> cmb;
	private JLabel lbl;
	
	public double dolar = 446.32;
	public double euro = 467.77;
	public double libra = 348.32;
	public double valorInput =0.00;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 581, 472);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(10, 11, 124, 30);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox();
		cmb.setModel(new DefaultComboBoxModel(EnumMoneda.values()));
		cmb.setBounds(10, 52, 124, 30);
		frame.getContentPane().add(cmb);
		//evento del boton
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				convertir();
			}
		});
		btn.setBounds(144, 52, 89, 30);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(144, 14, 89, 27);
		frame.getContentPane().add(lbl);
	}
	
	public void convertir(){
		
		if(vali(txt.getText())) {
			EnumMoneda moneda =(EnumMoneda) cmb.getSelectedItem();
			
			switch (moneda) {
			case pesos_dolar: 
				pesosAMoneda(dolar);
				break;
			case pesos_euro: 
				pesosAMoneda(euro);
				break;
			case pesos_libra: 
				pesosAMoneda(libra);
				break;
			case dolar_pesos: 
				monedaAPesos(dolar);
				break;
			case euro_pesos: 
				monedaAPesos(euro);
				break;
			case libra_pesos: 
				monedaAPesos(libra);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			}
		}
		
	}
	
	public void pesosAMoneda(double moneda) {
		double resultado = valorInput / moneda;
		lbl.setText(redondear(resultado));
}

public void monedaAPesos(double moneda) {
	double resultado = valorInput * moneda;
	lbl.setText(redondear(resultado));
}

public String redondear(double valor) {
	DecimalFormat df = new DecimalFormat("0.00");
	df.setRoundingMode(RoundingMode.HALF_UP);
	return df.format(valor);
}

public boolean vali(String texto) {
	try {
		double x = Double.parseDouble(texto);
		if(x>0) {
			valorInput = x;
			return true;
		}
	} catch (NumberFormatException e) {
		lbl.setText("Solamente numeros !!");
		return false;
	}
	return false;
}
	
	
}
