package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidad.Categoria;
import entidad.Libro;
import model.LibroModel;
import util.JComboBoxBD;
import util.Validaciones;

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
	   JButton btnLimpiar;
	   private JComboBoxBD cboCategori;
	   private JLabel lblNewLabel_4;
	   
	   private ResourceBundle rb=ResourceBundle.getBundle("combo");
	public FrmRegistroLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Libro");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("T\u00edtulo");
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
		
		lblNewLabel_3 = new JLabel("Categor\u00eda");
		lblNewLabel_3.setFont(new Font("NSimSun", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(285, 346, 128, 33);
		getContentPane().add(lblNewLabel_3);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(345, 492, 242, 33);
		getContentPane().add(btnLimpiar);
		
		cboCategori = new JComboBoxBD(rb.getString("SQL_CATEGORIA"));
		cboCategori.setBounds(464, 346, 254, 43);
		getContentPane().add(cboCategori);
		
		lblNewLabel_4 = new JLabel("Registro Libro");
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		lblNewLabel_4.setBounds(499, 40, 280, 28);
		getContentPane().add(lblNewLabel_4);
	}
	public void actionPerformed(ActionEvent e) {
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
		int  Indecate=cboCategori.getSelectedIndex();
		if (!ti.matches(Validaciones.TEXTO)) {
			mensaje("El T\u00edtulo  es de 2 a 30 caracteres");
		}else if (!annio.matches(Validaciones.ANNO)) {
			mensaje("El Año es de 4 d\u00edgitos");
		}else if (!ser.matches(Validaciones.PLACA)) {
			mensaje("La Serie son 2 Letras May\u00fasculas y 4 D\u00edgitos");
		}else if((Indecate ==0)) {
			mensaje("Seleccione una Categor\u00eda");
		}else {
			
			String categoria = cboCategori.getSelectedItem().toString();

			String idCategoria = categoria.split(":")[0];
			

			Categoria objcategoria = new Categoria();

			objcategoria.setIdCategoria(Integer.parseInt(idCategoria));
		
			Libro obj = new Libro();
			obj.setTitulo(ti);
			obj.setAnnio(Integer.parseInt(annio));
			obj.setSerie(ser);
			obj.setEstado(1);
			obj.setCategoria(objcategoria);
			
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
		cboCategori.setSelectedIndex(0);
		texttitulo.requestFocus();
		
		
	}
}
