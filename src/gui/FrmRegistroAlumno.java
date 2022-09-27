package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidad.Alumno;
import entidad.Pais;
import model.AlumnoModel;
import util.JComboBoxBD;
import util.Validaciones;

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
	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	
	protected void actionPerformedBtnGrabarJButton(ActionEvent e) {
		String nom = txtNombres.getText();
		String ape = txtApellidos.getText();
		String telef = txtTelefono.getText();
		String dni = txtDNI.getText();
		String email = txtCorreo.getText();
		String fecNac = txtFecNac.getText();
		int indexPais = cboPais.getSelectedIndex();
		
		if(!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		} else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
		} else if (!telef.matches(Validaciones.NUMERO)) {
			mensaje("El telefono tiene de de 0 a 1000 numeros");
		} else if (!dni.matches(Validaciones.DNI)) {
			mensaje("El DNI tiene 8 digitos");
		} else if(!email.matches(Validaciones.CORREO)) {
			mensaje("El correo presenta fallos en el dominio");
		} else if(!fecNac.matches(Validaciones.FECHA)) {
			mensaje("La fecha tiene como formato YYYY-MM-dd");		
		} else if(indexPais == 0) {
			mensaje("Seleccione un pais");
		}else {
			String pais = cboPais.getSelectedItem().toString();
			String idPais = pais.split(":")[0];
			
			Pais objPais = new Pais();
			objPais.setIdPais(Integer.parseInt(idPais));
			
			Alumno objAlu = new Alumno();
			objAlu.setNombres(nom);
			objAlu.setApellidos(ape);
			objAlu.setTelefono(telef);
			objAlu.setDni(dni);
			objAlu.setCorreo(email);
			objAlu.setFechaNacimiento(Date.valueOf(fecNac));
			objAlu.setPais(objPais);
			
			AlumnoModel model = new AlumnoModel();			
			int salida = model.insertarAlumno(objAlu);
			if(salida>0) {
				mensaje("Se insertó correctamente");
			} else {
				mensaje ("Error en el registro");
			}
		}
		
	}
	protected void actionPerformedBtnLimpiarJButton(ActionEvent e) {
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtDNI.setText("");
		txtCorreo.setText("");
		txtFecNac.setText("");
		cboPais.setSelectedIndex(0);
	}
}
