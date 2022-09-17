package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import entidad.Sala;
import model.SalaModel;
import util.Validaciones;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistroSala extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	 JTextField txtNumero;
	 JTextField txtPiso;
	 JTextField txtRecursos;
	 JTextField txtNumAlum;
	 JTextField txtidSede;
	 JButton btnProcesar;
	 private JButton btnLimpiar;
	 private JButton btnVer;

	public FrmRegistroSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Sala");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO SALA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 34));
		lblNewLabel.setBounds(101, 22, 702, 45);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Número:");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1.setBounds(142, 121, 109, 45);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Piso:");
		lblNewLabel_1_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(142, 200, 109, 45);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Núm.Alumnos:");
		lblNewLabel_1_1_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(533, 121, 170, 45);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Recursos:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(533, 200, 89, 45);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Sede:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(311, 291, 170, 45);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(261, 130, 124, 31);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(261, 209, 124, 31);
		getContentPane().add(txtPiso);
		
		txtRecursos = new JTextField();
		txtRecursos.setColumns(10);
		txtRecursos.setBounds(679, 209, 124, 31);
		getContentPane().add(txtRecursos);
		
		txtNumAlum = new JTextField();
		txtNumAlum.setColumns(10);
		txtNumAlum.setBounds(679, 130, 124, 31);
		getContentPane().add(txtNumAlum);
		
		txtidSede = new JTextField();
		txtidSede.setColumns(10);
		txtidSede.setBounds(400, 300, 124, 31);
		getContentPane().add(txtidSede);
		
		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(this);
		btnProcesar.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnProcesar.setBounds(363, 409, 209, 42);
		getContentPane().add(btnProcesar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnLimpiar.setBounds(400, 478, 143, 38);
		getContentPane().add(btnLimpiar);
		
		btnVer = new JButton("ver");
		btnVer.addActionListener(this);
		btnVer.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		btnVer.setBounds(556, 300, 109, 27);
		getContentPane().add(btnVer);
	}
	
	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVer) {
			actionPerformedBtnVerJButton(e);
		}
		if (e.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiarJButton(e);
		}
		if (e.getSource() == btnProcesar) {
			actionPerformedBtnProcesarJButton(e);
		}
	}
	protected void actionPerformedBtnProcesarJButton(ActionEvent e) {
		String num = txtNumero.getText();
		String pis = txtPiso.getText();
		String nalum = txtNumAlum.getText();
		String rec = txtRecursos.getText();
		String sed = txtidSede.getText();
		if(!num.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El numero es 1 letra y 3 digitos");
		}else if(!pis.matches(Validaciones.NUMERO)) {
			mensaje("El piso es solo digitos");
		}else if(!nalum.matches(Validaciones.NUMERO)) {
			mensaje("El número de alumnos es solo digitos");
		}else if(!rec.matches(Validaciones.TEXTO)) {
			mensaje("El recurso es 2 a 20 caracteres");
		}else if(!sed.matches(Validaciones.NUMERO)) {
			mensaje("La sede es solo digitos");
		}else {
			Sala obj = new Sala();
			obj.setNumero(num);
			obj.setPiso(Integer.parseInt(pis));
			obj.setNumAlumnos(Integer.parseInt(nalum));
			obj.setRecursos(rec);
			obj.setEstado(1);
			obj.setIdSede(Integer.parseInt(sed));
			
			SalaModel model = new SalaModel();
			int s = model.insertarSala(obj);
			if (s>0) {
				mensaje("Resgistro exitoso");
			}else {
				mensaje("Error en el registro");
			}
		}	
	}
	protected void actionPerformedBtnLimpiarJButton(ActionEvent e) {
		
	}
	protected void actionPerformedBtnVerJButton(ActionEvent e) {
		
	}
}
