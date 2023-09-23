package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidad.Alumno;
import entidad.Categoria;
import entidad.Tesis;
import model.TesisModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmRegistroTesis extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textTitulo;
	private JTextField textTema;
	private JTextField textFecha;
	private JButton btnProcesar;
	private JButton btnBorrar;
	private JLabel lblNewLabel_4;
	private JComboBoxBD cboAlumno;
	 private ResourceBundle rb=ResourceBundle.getBundle("combo");
	public FrmRegistroTesis() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Tesis");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Registro Tesis");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(322, 11, 339, 102);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Titulo");
		lblNewLabel_1.setBounds(182, 136, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Tema");
		lblNewLabel_2.setBounds(182, 205, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Fecha de Creacion");
		lblNewLabel_3.setBounds(182, 256, 163, 14);
		getContentPane().add(lblNewLabel_3);
		
		textTitulo = new JTextField();
		textTitulo.setBounds(355, 133, 390, 20);
		getContentPane().add(textTitulo);
		textTitulo.setColumns(10);
		
		textTema = new JTextField();
		textTema.setColumns(10);
		textTema.setBounds(355, 202, 390, 20);
		getContentPane().add(textTema);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(355, 253, 276, 20);
		getContentPane().add(textFecha);
		
		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnProcesar(e);
			}
		});
		btnProcesar.setBounds(199, 350, 520, 45);
		getContentPane().add(btnProcesar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBorrar(e);
			}
		});
		btnBorrar.setBounds(236, 427, 454, 39);
		getContentPane().add(btnBorrar);
		
		lblNewLabel_4 = new JLabel("Alumno");
		lblNewLabel_4.setBounds(182, 310, 67, 14);
		getContentPane().add(lblNewLabel_4);
		
		cboAlumno = new JComboBoxBD(rb.getString("SQL_ALUMNO"));
		cboAlumno.setBounds(355, 306, 276, 22);
		getContentPane().add(cboAlumno);
	}
	protected void actionPerformedBtnBorrar(ActionEvent e) {
		textTitulo.setText("");
		textTema.setText("");
		textFecha.setText("");
		textTitulo.requestFocus();
		cboAlumno.setSelectedIndex(0);
	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	protected void actionPerformedBtnProcesar(ActionEvent e) {
		String titulo=textTitulo.getText();
		String tema=textTema.getText();
		String fecha=textFecha.getText();
		int  Indecate=cboAlumno.getSelectedIndex();
		if (!titulo.matches(Validaciones.TEXTO)) {
			mensaje("El T\u00edtulo  es de 2 a 30 caracteres");
		}else if (!tema.matches(Validaciones.TEXTO)) {
			mensaje("El Tema  es de 2 a 30 caracteres");
		}else if (!fecha.matches(Validaciones.FECHA)) {
			mensaje("La Fecha es AAAA-MM-DD");
		}else if((Indecate ==0)) {
			mensaje("Seleccione un alumno");
		}else {
			
			String alumno = cboAlumno.getSelectedItem().toString();

			String idalumno = alumno.split(":")[0];
			

			Alumno objalumno = new Alumno();

			objalumno.setIdAlumno(Integer.parseInt(idalumno)); 
			
			Tesis obj = new Tesis();
			obj.setTitulo(titulo);
			obj.setTema(tema);
			obj.setFechaCreacion(Date.valueOf(fecha));
			obj.setEstado(1);
			obj.setAlumno(objalumno);
			
			TesisModel model = new TesisModel();
			int salida = model.registroTesis(obj);
			if (salida>0) {
				mensaje("Registro exitoso");	
			}else {
				mensaje("Error en el registro");
			}
		}
	}
}
