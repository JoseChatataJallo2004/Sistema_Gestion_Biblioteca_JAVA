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

import entidad.Categoria;
import entidad.Pais;
import entidad.Proveedor;

import model.ProveedorModel;
import util.JComboBoxBD;
import util.Validaciones;
import javax.swing.JComboBox;

public class FrmRegistroProveedor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDni;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textCorreo;
	 private JComboBoxBD cboPais;
	 private ResourceBundle rb=ResourceBundle.getBundle("combo");
	public FrmRegistroProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Proveedor");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro Proveedor");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel.setBounds(302, 33, 375, 41);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setBounds(263, 114, 148, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setBounds(263, 145, 157, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("DNI");
		lblNewLabel_3.setBounds(263, 180, 157, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Direccion");
		lblNewLabel_4.setBounds(263, 222, 171, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Telefono");
		lblNewLabel_5.setBounds(263, 259, 171, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Correo");
		lblNewLabel_6.setBounds(263, 296, 171, 14);
		getContentPane().add(lblNewLabel_6);
		
		textNombre = new JTextField();
		textNombre.setBounds(444, 111, 267, 20);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setColumns(10);
		textApellido.setBounds(444, 142, 267, 20);
		getContentPane().add(textApellido);
		
		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(444, 177, 267, 20);
		getContentPane().add(textDni);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(444, 219, 267, 20);
		getContentPane().add(textDireccion);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(444, 256, 267, 20);
		getContentPane().add(textTelefono);
		
		textCorreo = new JTextField();
		textCorreo.setColumns(10);
		textCorreo.setBounds(444, 293, 267, 20);
		getContentPane().add(textCorreo);
		
		JButton btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnProcesar(e);
			}
		});
		btnProcesar.setBounds(263, 376, 483, 41);
		getContentPane().add(btnProcesar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBorrar(e);
			}
		});
		btnBorrar.setBounds(332, 438, 368, 31);
		getContentPane().add(btnBorrar);
		
		JLabel lblNewLabel_7 = new JLabel("Pais");
		lblNewLabel_7.setBounds(263, 335, 171, 14);
		getContentPane().add(lblNewLabel_7);
		
		 cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboPais.setBounds(444, 331, 267, 22);
		getContentPane().add(cboPais);
	}
	protected void actionPerformedBtnBorrar(ActionEvent e) {
		textNombre.setText("");
		textApellido.setText("");
		textCorreo.setText("");
		textDireccion.setText("");
		textTelefono.setText("");
		textDni.setText("");
		textNombre.requestFocus();
	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	protected void actionPerformedBtnProcesar(ActionEvent e) {
		String nombre=textNombre.getText();
		String apellido=textApellido.getText();
		String dni=textDni.getText();
		String direccion=textDireccion.getText();
		String telefono=textTelefono.getText();
		String correo=textCorreo.getText();
		int  Indecate=cboPais.getSelectedIndex();
		if (!nombre.matches(Validaciones.TEXTO)) {
			mensaje("El Nombre es de 2 a 30 caracteres");
		}else if (!apellido.matches(Validaciones.TEXTO)) {
			mensaje("El Apellido es de 2 a 30 caracteres");
		}else if (!dni.matches(Validaciones.DNI)) {
			mensaje("EL DNI es de 8 Digitos");
		}else if (!direccion.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("Tu Direccion  es de 2 a 30 caracteres ");
		}else if (!telefono.matches(Validaciones.NUMERO)) {
			mensaje("El telefono es de 9 digitos");
		}else if (!correo.matches(Validaciones.TEXTO)) {
			mensaje("Tu Correo debe ser con @gmail.com");
		}else if((Indecate ==0)) {
			mensaje("Seleccione un  Pais");
		}else {
			
			String pais = cboPais.getSelectedItem().toString();

			String idPais = pais.split(":")[0];
			

			Pais objPais = new Pais();

			objPais.setIdPais(Integer.parseInt(idPais));
			Proveedor obj = new Proveedor();
			obj.setNombre(nombre);
			obj.setApellido(apellido);
			obj.setDni(dni);
			obj.setDireccion(direccion);
			obj.setTelefono(telefono);
			obj.setCorreo(correo);
			obj.setEstado(1);
			obj.setPais(objPais);
			
			
			
			ProveedorModel model = new ProveedorModel();
			int salida = model.registroProveedor(obj);
			if (salida>0) {
				mensaje("Registro exitoso");	
			}else {
				mensaje("Error en el registro");
			}
		}
	}
}
