package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Sala;
import entidad.Sede;
import model.SalaModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmRegistroSala extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	 JTextField txtNumero;
	 JTextField txtPiso;
	 JTextField txtRecursos;
	 JTextField txtNumAlum;
	 JButton btnProcesar;
	 private JButton btnLimpiar;
	 private JComboBoxBD cboSede;
	 private ResourceBundle rb = ResourceBundle.getBundle("combo");

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
		
		JLabel lblNewLabel_1 = new JLabel("N\u00famero:");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1.setBounds(142, 121, 109, 45);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Piso:");
		lblNewLabel_1_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(142, 200, 109, 45);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Num.Alumnos:");
		lblNewLabel_1_1_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(533, 121, 170, 45);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Recursos:");
		lblNewLabel_1_1_1_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(533, 200, 89, 45);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Sede:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(304, 291, 170, 45);
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
		
		cboSede = new JComboBoxBD(rb.getString("SQL_SEDE"));
		cboSede.setBounds(402, 298, 155, 35);
		getContentPane().add(cboSede);
	}
	
	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	
	public void actionPerformed(ActionEvent e) {
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
		int indexSede = cboSede.getSelectedIndex();
		
		if(!num.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El n\u00famero es 1 letra y 3 digitos");
		}else if(!pis.matches(Validaciones.NUMERO)) {
			mensaje("El piso es 0 a 1000 digitos");
		}else if(!nalum.matches(Validaciones.NUMERO)) {
			mensaje("El n\u00famero es 0 a 1000 digitos");
		}else if(!rec.matches(Validaciones.TEXTO)) {
			mensaje("El recurso es 2 a 20 caracteres");
		}else if((indexSede ==0)) {
			mensaje("Seleccione una Sede");
		}else {
			String sed = cboSede.getSelectedItem().toString();
			String idSede = sed.split(":")[0];
			
			Sede objSede = new Sede();
			objSede.setIdSede(Integer.parseInt(idSede));
			
			
			Sala obj = new Sala();
			obj.setNumero(num);
			obj.setPiso(Integer.parseInt(pis));
			obj.setNumAlumnos(Integer.parseInt(nalum));
			obj.setRecursos(rec);
			obj.setSede(objSede);
			obj.setEstado(1);
			
			
			
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
		txtNumero.setText("");
		txtNumAlum.setText("");
		txtPiso.setText("");
		txtRecursos.setText("");
		cboSede.setSelectedIndex(0);
		txtNumero.requestFocus();	
	}
}
