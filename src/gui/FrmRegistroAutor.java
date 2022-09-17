package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ResourceBundle;

public class FrmRegistroAutor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtFechaN;
	private JTextField txtTelefono;
	private JTextField txtGrado;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

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
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnRegistrar.setBounds(403, 393, 106, 40);
		getContentPane().add(btnRegistrar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnLimpiar.setBounds(212, 393, 106, 40);
		getContentPane().add(btnLimpiar);
		
		JLabel lblGrado = new JLabel("Grado :");
		lblGrado.setHorizontalAlignment(SwingConstants.LEFT);
		lblGrado.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblGrado.setBounds(207, 334, 112, 35);
		getContentPane().add(lblGrado);
		
		txtGrado = new JTextField();
		txtGrado.setColumns(10);
		txtGrado.setBounds(327, 342, 107, 20);
		getContentPane().add(txtGrado);
	}
}
