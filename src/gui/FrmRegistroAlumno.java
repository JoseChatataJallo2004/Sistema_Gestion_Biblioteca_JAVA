package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class FrmRegistroAlumno extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public FrmRegistroAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Alumno");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblAlumno = new JLabel("Registro de Alumno:");
		lblAlumno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlumno.setBounds(465, 28, 230, 81);
		getContentPane().add(lblAlumno);
	}
}
