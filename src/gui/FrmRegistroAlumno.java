package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import util.JComboBoxBD;

public class FrmRegistroAlumno extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JTextField txtFecNac;
	private JComboBoxBD cboPais;

	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	
	public FrmRegistroAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Alumno");
		setBounds(100, 100, 569, 600);
		getContentPane().setLayout(null);
		
		JLabel lblAlumno = new JLabel("Registro de Alumno:");
		lblAlumno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlumno.setBounds(179, 34, 176, 35);
		getContentPane().add(lblAlumno);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(56, 95, 46, 14);
		getContentPane().add(lblNombres);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(228, 95, 202, 20);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(56, 136, 46, 14);
		getContentPane().add(lblApellidos);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(228, 136, 202, 20);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(56, 182, 46, 14);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(228, 182, 202, 20);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(56, 230, 46, 14);
		getContentPane().add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(228, 230, 202, 20);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(56, 279, 46, 14);
		getContentPane().add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(228, 279, 202, 20);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JLabel lblFecNac = new JLabel("Fecha de Nacimiento:");
		lblFecNac.setBounds(56, 326, 126, 14);
		getContentPane().add(lblFecNac);
		
		txtFecNac = new JTextField();
		txtFecNac.setBounds(228, 323, 202, 20);
		getContentPane().add(txtFecNac);
		txtFecNac.setColumns(10);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboPais.setToolTipText("");
		cboPais.setBounds(228, 370, 202, 22);
		getContentPane().add(cboPais);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(56, 374, 46, 14);
		getContentPane().add(lblPais);
		
		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGrabarJButton(e);
			}
		});
		btnGrabar.setBounds(179, 443, 89, 23);
		getContentPane().add(btnGrabar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnLimpiarJButton(e);
			}
		});
		btnLimpiar.setBounds(315, 443, 89, 23);
		getContentPane().add(btnLimpiar);
	}
	
	protected void actionPerformedBtnGrabarJButton(ActionEvent e) {
		
	}
	protected void actionPerformedBtnLimpiarJButton(ActionEvent e) {
	}
}
