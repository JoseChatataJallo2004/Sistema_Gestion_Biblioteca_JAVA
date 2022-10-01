package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.SwingConstants;

import entidad.Autor;
import entidad.Grado;
import model.AutorModel;
import util.JComboBoxBD;
import util.Validaciones;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ResourceBundle;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class FrmRegistroAutor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtFechaN;
	private JTextField txtTelefono;
	
	private JComboBoxBD cboGrado;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	private JButton btnRegistrar;
	private JButton btnLimpiar;

	public FrmRegistroAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Autor");
		setBounds(100, 100, 740, 600);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("REGISTRO DE AUTOR");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 26));
		lblTitulo.setBounds(212, 55, 297, 40);
		getContentPane().add(lblTitulo);
		
		JLabel lblNombres = new JLabel("Nombres  :");
		lblNombres.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombres.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblNombres.setBounds(207, 150, 107, 35);
		getContentPane().add(lblNombres);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(327, 158, 182, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos  :");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblApellidos.setBounds(207, 196, 107, 35);
		getContentPane().add(lblApellidos);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(327, 204, 182, 20);
		getContentPane().add(txtApellido);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento  :");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaNacimiento.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblFechaNacimiento.setBounds(207, 242, 150, 35);
		getContentPane().add(lblFechaNacimiento);
		
		txtFechaN = new JTextField();
		txtFechaN.setColumns(10);
		txtFechaN.setBounds(355, 250, 107, 20);
		getContentPane().add(txtFechaN);
		
		JLabel lblTelefono = new JLabel("Teléfono  :");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblTelefono.setBounds(207, 288, 112, 35);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(327, 296, 107, 20);
		getContentPane().add(txtTelefono);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnRegistrar.setBounds(403, 393, 106, 40);
		getContentPane().add(btnRegistrar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnLimpiar.setBounds(212, 393, 106, 40);
		getContentPane().add(btnLimpiar);
		
		JLabel lblGrado = new JLabel("Grado :");
		lblGrado.setHorizontalAlignment(SwingConstants.LEFT);
		lblGrado.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblGrado.setBounds(207, 334, 112, 35);
		getContentPane().add(lblGrado);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"));
		cboGrado.setBounds(327, 341, 107, 20);
		getContentPane().add(cboGrado);
	}
	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiarJButton(e);
		}
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrarJButton(e);
		}
	}
	protected void actionPerformedBtnRegistrarJButton(ActionEvent e) {
		String nom = txtNombre.getText().trim();
		String ape = txtApellido.getText().trim();
		String fec = txtFechaN.getText().trim();
		String tel = txtTelefono.getText().trim();
		int indexGrado = cboGrado.getSelectedIndex();
		
		if(!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		} else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
		} else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("La fecha tiene como formato YYYY-MM-dd");
		} else if (!tel.matches(Validaciones.NUMERO)) {
			mensaje("El n\u00famero telefonico es de 9 d\u00edgitos ");
		} else if(indexGrado == 0) {
			mensaje("Seleccione un grado");
		} else {
			String grado = cboGrado.getSelectedItem().toString();
			String idGrado = grado.split(":")[0];
			
			Grado objGrado = new Grado();
			objGrado.setIdGrado(Integer.parseInt(idGrado));
			
			Autor objAutor = new Autor();
			objAutor.setNombres(nom);
			objAutor.setApellidos(ape);
			objAutor.setFechaNacimiento(Date.valueOf(fec));
			objAutor.setTelefono(tel);
			objAutor.setGrado(objGrado);
			
			AutorModel model = new AutorModel();
			int salida = model.insertarAutor(objAutor);
			if(salida>0) {
				mensaje("Se insert\u00f3 correctamente");
			} else {
				mensaje ("Error en el registro");
			}
		}
	}
	protected void actionPerformedBtnLimpiarJButton(ActionEvent e) {
		txtNombre.setText("");
		txtApellido.setText("");
		txtFechaN.setText("");
		txtTelefono.setText("");
		cboGrado.setSelectedIndex(0);	
	}
}
