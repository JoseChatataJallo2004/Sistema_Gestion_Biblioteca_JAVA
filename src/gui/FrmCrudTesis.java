package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import entidad.Tesis;
import model.LibroModel;
import model.TesisModel;
import util.JComboBoxBD;
import util.Validaciones;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmCrudTesis extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblMantenimientoTesis;
	private JLabel lblNewLabel;
	private JTextField textTitulo;
	private JLabel lblNewLabel_1;
	private JTextField textTema;
	private JLabel lblNewLabel_2;
	private JTextField textfc;
	private JLabel lblNewLabel_3;
	private JCheckBox chkEstado;
	private JLabel lblNewLabel_4;
	private JComboBoxBD cboAlumno;
	private JButton btnIngresar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JScrollPane scrollPane;
	private JTable table;

	
		int idSeleccionado = -1;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	public FrmCrudTesis() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Tesis");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		lblMantenimientoTesis = new JLabel("Mantenimiento Tesis");
		lblMantenimientoTesis.setOpaque(true);
		lblMantenimientoTesis.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoTesis.setForeground(Color.WHITE);
		lblMantenimientoTesis.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoTesis.setBackground(Color.ORANGE);
		lblMantenimientoTesis.setBounds(73, 22, 744, 59);
		getContentPane().add(lblMantenimientoTesis);
		
		lblNewLabel = new JLabel("Título");
		lblNewLabel.setBounds(73, 112, 118, 14);
		getContentPane().add(lblNewLabel);
		
		textTitulo = new JTextField();
		textTitulo.setColumns(10);
		textTitulo.setBounds(201, 109, 324, 20);
		getContentPane().add(textTitulo);
		
		lblNewLabel_1 = new JLabel("Tema");
		lblNewLabel_1.setBounds(73, 148, 78, 14);
		getContentPane().add(lblNewLabel_1);
		
		textTema = new JTextField();
		textTema.setColumns(10);
		textTema.setBounds(201, 140, 324, 20);
		getContentPane().add(textTema);
		
		lblNewLabel_2 = new JLabel("Fecha Creacion");
		lblNewLabel_2.setBounds(73, 183, 118, 14);
		getContentPane().add(lblNewLabel_2);
		
		textfc = new JTextField();
		textfc.setColumns(10);
		textfc.setBounds(201, 180, 324, 20);
		getContentPane().add(textfc);
		
		lblNewLabel_3 = new JLabel("Estado");
		lblNewLabel_3.setBounds(73, 224, 108, 14);
		getContentPane().add(lblNewLabel_3);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setSelected(true);
		chkEstado.setBounds(201, 220, 97, 23);
		getContentPane().add(chkEstado);
		
		lblNewLabel_4 = new JLabel("Alumno");
		lblNewLabel_4.setBounds(73, 258, 108, 14);
		getContentPane().add(lblNewLabel_4);
		
		cboAlumno = new JComboBoxBD(rb.getString("SQL_ALUMNO"));
		cboAlumno.setBounds(201, 254, 324, 22);
		getContentPane().add(cboAlumno);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnIngresar(e);
			}
		});
		btnIngresar.setBounds(662, 108, 155, 23);
		getContentPane().add(btnIngresar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		btnEliminar.setBounds(662, 162, 155, 23);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizar(e);
			}
		});
		btnActualizar.setBounds(662, 215, 155, 23);
		getContentPane().add(btnActualizar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 304, 864, 239);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedTable(e);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Titulo", "Tema", "Fecha Creacion", "Estado", "idAlumno", "Alumno"
			}
		));
		scrollPane.setViewportView(table);
		lista();
	}
	
	void limpiarCajasTexto() {
		textTitulo.setText("");
		textTema.setText("");
		textfc.setText("");
		cboAlumno.setSelectedIndex(0);
		chkEstado.setSelected(true);
		textTitulo.requestFocus();
	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	} 
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		registra();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		elimina();
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizar();
	}
	
	private void lista() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		TesisModel l=new TesisModel();
		List<Tesis> lista=l.listaTodos();
		for(Tesis x:lista) {
			Object[] fila= {x.getIdtesis(),x.getTitulo(),x.getTema(),x.getFechaCreacion(),getStrEstado(x.getEstado()) ,x.getAlumno().getIdAlumno(),x.getAlumno().getNombres()
		};
			dtm.addRow(fila);
	}
	}
	public String getStrEstado(int estado) {
		if (estado == 1) return "Activo";
		else			 return "Inactivo";
	}
	
	
	private void elimina() {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else {
			TesisModel model = new TesisModel();
			int salida = model.eliminarTesis(idSeleccionado);
			if (salida > 0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se eliminó correctamente");
			}else {
				mensaje("Error en la eliminación");
			}
		}
	}
	
	private void busca() {
		
		
		//get obetner , select es seleccionar la fina 
		int fila=table.getSelectedRow();
		//getvalues al obtiene el valor de las celdas 
		idSeleccionado=(Integer)table.getValueAt(fila, 0);
		String titulo=(String )table.getValueAt(fila, 1);
		String tema=(String )table.getValueAt(fila, 2);
		Date fc=(Date )table.getValueAt(fila, 3);
		String estado=(String )table.getValueAt(fila, 4);
		int  idAlumno=(Integer)table.getValueAt(fila, 5);
		String alumno=(String )table.getValueAt(fila, 6);
		
		textTitulo.setText(titulo);
		textTema.setText(tema);
		textfc.setText(String.valueOf(fc));
		cboAlumno.setSelectedItem(idAlumno + " : " + alumno);
		chkEstado.setSelected(estado.equals("Activo")?true:false);
	}
	
	
	protected void mouseClickedTable(MouseEvent e) {
		busca();
	}
	
	private void registra(){
		String titulo=textTitulo.getText();
		String tema=textTema.getText();
		String fecha=textfc.getText();
		int  Indecate=cboAlumno.getSelectedIndex();
		if (!titulo.matches(Validaciones.TEXTO)) {
			mensaje("El T\u00edtulo  es de 2 a 30 caracteres");
		}else if (!tema.matches(Validaciones.TEXTO)) {
			mensaje("El Tema  es de 2 a 30 caracteres");
		}else if (!fecha.matches(Validaciones.FECHA)) {
			mensaje("La Fecha es AAAA-MM-DD");
		}else if((Indecate ==0)) {
			mensaje("Seleccione un alumno");
		}else {
			
			String alumno = cboAlumno.getSelectedItem().toString();

			String idalumno = alumno.split(":")[0];
			

			Alumno objalumno = new Alumno();

			objalumno.setIdAlumno(Integer.parseInt(idalumno)); 
			
			Tesis obj = new Tesis();
			obj.setTitulo(titulo);
			obj.setTema(tema);
			obj.setFechaCreacion(Date.valueOf(fecha));
			obj.setEstado(chkEstado.isSelected()?1:0);
			obj.setAlumno(objalumno);
			
			TesisModel model = new TesisModel();
			int salida = model.registroTesis(obj);
			if (salida>0) {
				lista();
				mensaje("Registro exitoso");	
				limpiarCajasTexto();
			}else {
				mensaje("Error en el registro");
			}
		}
		
		
		}
	private void actualizar() {
		String titulo=textTitulo.getText();
		String tema=textTema.getText();
		String fecha=textfc.getText();
		int  Indecate=cboAlumno.getSelectedIndex();
		if (!titulo.matches(Validaciones.TEXTO)) {
			mensaje("El T\u00edtulo  es de 2 a 30 caracteres");
		}else if (!tema.matches(Validaciones.TEXTO)) {
			mensaje("El Tema  es de 2 a 30 caracteres");
		}else if (!fecha.matches(Validaciones.FECHA)) {
			mensaje("La Fecha es AAAA-MM-DD");
		}else if((Indecate ==0)) {
			mensaje("Seleccione un alumno");
		}else {
			
			String alumno = cboAlumno.getSelectedItem().toString();

			String idalumno = alumno.split(":")[0];
			

			Alumno objalumno = new Alumno();

			objalumno.setIdAlumno(Integer.parseInt(idalumno)); 
			
			Tesis obj = new Tesis();
			obj.setIdtesis(idSeleccionado);
			obj.setTitulo(titulo);
			obj.setTema(tema);
			obj.setFechaCreacion(Date.valueOf(fecha));
			obj.setEstado(chkEstado.isSelected()?1:0);
			obj.setAlumno(objalumno);
			
			TesisModel model = new TesisModel();
			int salida = model.actualizaTesis(obj);
			if (salida>0) {
				lista();
				idSeleccionado=-1;
				mensaje("Se Actualizo Correctamento");	
				limpiarCajasTexto();
			}else {
				mensaje("Error en el registro");
			}
		}
		
		
		}
	}

