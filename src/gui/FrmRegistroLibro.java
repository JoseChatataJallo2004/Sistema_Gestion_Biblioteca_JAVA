package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import util.Conversiones;
import util.Validaciones;
import entidad.Registro_Libro;
import model.LibroModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

public class FrmRegistroLibro extends JInternalFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	 JLabel lblNewLabel;
	 JLabel lblNewLabel_1;
	 JLabel lblNewLabel_2;
	 JButton btnNewButton;
	 JTextField texttitulo;
	 JTextField textanhio;
	 JTextField textSerie;
	  JLabel lblNewLabel_3;
	  JTextField textCategoria;
	   JButton btnLimpiar;
	   JLabel lblNewLabel_4;
	   JButton btnVer;
	   private JComboBox cboCategori;

	public FrmRegistroLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Libro");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("Titulo");
		lblNewLabel.setFont(new Font("NSimSun", Font.PLAIN, 20));
		lblNewLabel.setBounds(285, 96, 111, 28);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Año");
		lblNewLabel_1.setFont(new Font("NSimSun", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(285, 173, 111, 28);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Serie");
		lblNewLabel_2.setFont(new Font("NSimSun", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(285, 258, 111, 33);
		getContentPane().add(lblNewLabel_2);
		
		btnNewButton = new JButton("Procesar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(254, 422, 421, 43);
		getContentPane().add(btnNewButton);
		
		texttitulo = new JTextField();
		texttitulo.setBounds(464, 96, 254, 43);
		getContentPane().add(texttitulo);
		texttitulo.setColumns(10);
		
		textanhio = new JTextField();
		textanhio.addKeyListener(this);
		textanhio.setColumns(10);
		textanhio.setBounds(464, 173, 254, 43);
		getContentPane().add(textanhio);
		
		textSerie = new JTextField();
		textSerie.addKeyListener(this);
		textSerie.setColumns(10);
		textSerie.setBounds(464, 248, 254, 43);
		getContentPane().add(textSerie);
		
		lblNewLabel_3 = new JLabel("Categoria");
		lblNewLabel_3.setFont(new Font("NSimSun", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(285, 346, 128, 33);
		getContentPane().add(lblNewLabel_3);
		
		textCategoria = new JTextField();
		textCategoria.addKeyListener(this);
		textCategoria.setBounds(464, 336, 254, 43);
		getContentPane().add(textCategoria);
		textCategoria.setColumns(10);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(345, 492, 242, 33);
		getContentPane().add(btnLimpiar);
		
		lblNewLabel_4 = new JLabel("REGISTRO DE LIBROS");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("NSimSun", Font.PLAIN, 42));
		lblNewLabel_4.setBounds(230, 25, 501, 33);
		getContentPane().add(lblNewLabel_4);
		
		btnVer = new JButton("Ver");
		btnVer.addActionListener(this);
		btnVer.setBounds(735, 346, 89, 23);
		getContentPane().add(btnVer);
		
		cboCategori = new JComboBox();
		cboCategori.setBounds(464, 389, 254, 22);
		getContentPane().add(cboCategori);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVer) {
			actionPerformedBtnVerJButton(e);
		}
		if (e.getSource() == btnLimpiar) {
			actionPerformedBtnLimpiarJButton(e);
		}
		if (e.getSource() == btnNewButton) {
			actionPerformedBtnNewButtonJButton(e);
		}
	}
	protected void actionPerformedBtnNewButtonJButton(ActionEvent e) {
		String ti=texttitulo.getText();
		String annio=textanhio.getText();
		String ser=textSerie.getText();
		String cate=textCategoria.getText();
		if (!ti.matches(Validaciones.TEXTO)) {
			mensaje("El Titulo  es de 2 a 30 caracteres");
		}else if (!annio.matches(Validaciones.ANNO)) {
			mensaje("El Año es de 4 digitos");
		}else if (!ser.matches(Validaciones.Serie_libro)) {
			mensaje("La Serie son 2 Letras y 10 Digitos");
		
		}else if (!cate.matches(Validaciones.categoria)) {
			mensaje("La Categoria  debe ser 1=NOVELA , 2=CUENTO , 3=POESIA o 4=Enciclopedia");
		}else {
			Registro_Libro obj = new Registro_Libro();
			obj.setTitulo(ti);
			obj.setAnnio(Integer.parseInt(annio));
			obj.setSerie(ser);
			obj.setEstado(1);
			obj.setCategoria(Integer.parseInt(cate));
			
			LibroModel model = new LibroModel();
			int salida = model.registroLibro(obj);
			if (salida>0) {
				mensaje("Registro exitoso");	
			}else {
				mensaje("Error en el registro");
			}
			
		}
	}
		public void mensaje(String ms){
			JOptionPane.showMessageDialog(this, ms);
		}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == textCategoria) {
			keyTypedTextCategoriaJTextField(e);
		}
		
		if (e.getSource() == textanhio) {
			keyTypedTextanhioJTextField(e);
		}
	}
	protected void keyTypedTextanhioJTextField(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar())) {
			e.consume();
		}
		
		String textoQueVaGenerarse = textanhio.getText().trim() + e.getKeyChar();
		if (textoQueVaGenerarse.length()>4) {
			e.consume();
		}
	}
	
	protected void actionPerformedBtnLimpiarJButton(ActionEvent e) {
		textanhio.setText("");
		texttitulo.setText("");
		textSerie.setText("");
		textCategoria.setText("");
		texttitulo.requestFocus();
		
		
	}
	
	protected void keyTypedTextCategoriaJTextField(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar())) {
			e.consume();
		}
		
		String textoQueVaGenerarse = textCategoria.getText().trim() + e.getKeyChar();
		if (textoQueVaGenerarse.length()>1) {
			e.consume();
		}
	}
	protected void actionPerformedBtnVerJButton(ActionEvent e) {
		mensaje(" Categorias " + "\n"
				+ "1: Novela " + "\n"
				+ "2: Cuento" + "\n"
				+ "3: Poesía " + "\n"
				+ "4: Enciclopedia "		
				);
	}
}
