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

public class FrmRegistroAutor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtFechaN;
	private JTextField txtTelefono;

	public FrmRegistroAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Autor");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("REGISTRO DE AUTOR");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 26));
		lblTitulo.setBounds(273, 26, 297, 40);
		getContentPane().add(lblTitulo);
		
		JLabel lblNombres = new JLabel("Nombres  :");
		lblNombres.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombres.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblNombres.setBounds(268, 121, 107, 35);
		getContentPane().add(lblNombres);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(388, 129, 182, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos  :");
		lblApellidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblApellidos.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblApellidos.setBounds(268, 167, 107, 35);
		getContentPane().add(lblApellidos);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(388, 175, 182, 20);
		getContentPane().add(txtApellido);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento  :");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaNacimiento.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblFechaNacimiento.setBounds(268, 213, 150, 35);
		getContentPane().add(lblFechaNacimiento);
		
		txtFechaN = new JTextField();
		txtFechaN.setColumns(10);
		txtFechaN.setBounds(416, 221, 107, 20);
		getContentPane().add(txtFechaN);
		
		JLabel lblTelefono = new JLabel("Telefono  :");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		lblTelefono.setBounds(268, 259, 134, 35);
		getContentPane().add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(388, 267, 107, 20);
		getContentPane().add(txtTelefono);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(0, 128, 0));
		btnRegistrar.setBackground(Color.DARK_GRAY);
		btnRegistrar.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnRegistrar.setBounds(463, 343, 107, 40);
		getContentPane().add(btnRegistrar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Sitka Text", Font.BOLD, 14));
		btnLimpiar.setBackground(Color.DARK_GRAY);
		btnLimpiar.setBounds(280, 343, 107, 40);
		getContentPane().add(btnLimpiar);
	}
}
