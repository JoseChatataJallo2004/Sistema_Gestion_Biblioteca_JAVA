package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Autor;
import entidad.Grado;
import model.AutorModel;
import util.JComboBoxBD;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class FrmCrudAutor extends JInternalFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtFecha;
	private JTextField txtTelefono;
	private JTable table;
	
	
	int idSeleccionado = -1;

	
	private JComboBoxBD cboGrado;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	private JButton btnIngreso;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JCheckBox chkEstado;

	public FrmCrudAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Autor");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblMantenimientoDeAutor = new JLabel("MANTENIMIENTO DE AUTOR");
		lblMantenimientoDeAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoDeAutor.setFont(new Font("Tempus Sans ITC", Font.BOLD, 26));
		lblMantenimientoDeAutor.setBounds(195, 37, 439, 40);
		getContentPane().add(lblMantenimientoDeAutor);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(34, 111, 81, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(34, 161, 81, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblFec = new JLabel("Fecha de Nacimiento");
		lblFec.setBounds(330, 111, 130, 14);
		getContentPane().add(lblFec);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(330, 161, 74, 14);
		getContentPane().add(lblTelefono);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"));
		cboGrado.setBounds(700, 107, 150, 22);
		getContentPane().add(cboGrado);
		
		JLabel lblGrado = new JLabel("Grado");
		lblGrado.setBounds(632, 111, 46, 14);
		getContentPane().add(lblGrado);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(125, 108, 165, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(125, 158, 165, 20);
		getContentPane().add(txtApellido);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(470, 108, 114, 20);
		getContentPane().add(txtFecha);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(470, 158, 114, 20);
		getContentPane().add(txtTelefono);
		
		btnIngreso = new JButton("Ingreso");
		btnIngreso.addActionListener(this);
		btnIngreso.setBounds(201, 199, 89, 23);
		getContentPane().add(btnIngreso);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(350, 199, 89, 23);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(474, 199, 89, 23);
		getContentPane().add(btnActualizar);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setSelected(true);
		chkEstado.setBounds(632, 157, 97, 23);
		getContentPane().add(chkEstado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 233, 864, 326);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id Autor", "Nombres", "Apellidos", "Fecha Nacimiento", "Telefono", "Fecha Registro", "Estado", "Id Grado", "Descripcion"
			}
		));
		
		//TamaÃ±o a las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		scrollPane.setViewportView(table);
				
		//desabilita mover las columnas
		table.getTableHeader().setReorderingAllowed(false);
						
		//desabilita el cambio de tamaÃ±o
		table.getTableHeader().setResizingAllowed(false);
				
		//selecciona una sola fila
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						
		//color de la fila seleccionada
		table.setSelectionBackground(Color.GREEN);
				
		//alineacion
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
				
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
				
		//Desabilitar la edicion en las celdas
		table.setDefaultEditor(Object.class, null);
				
		//Se oculta la fila 4
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setWidth(0);
				
		scrollPane.setViewportView(table);
		lista();
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	void limpiarCajasTexto() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtFecha.setText("");
		txtTelefono.setText("");
		cboGrado.setSelectedIndex(0);
		txtNombre.requestFocus();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizarJButton(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminarJButton(e);
		}
		if (e.getSource() == btnIngreso) {
			actionPerformedBtnIngresoJButton(e);
		}
	}
	protected void actionPerformedBtnIngresoJButton(ActionEvent e) {
		registra();
	}
	protected void actionPerformedBtnEliminarJButton(ActionEvent e) {
		elimina();
	}
	protected void actionPerformedBtnActualizarJButton(ActionEvent e) {
		actualiza();
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTableJTable(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTableJTable(MouseEvent e) {
		busca();
	}
	
	private void lista() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		AutorModel m = new AutorModel();
		List<Autor> lista = m.listaTodos();
		
		for(Autor x: lista) {
			Object[] fila = {x.getIdAutor(),x.getNombres(), x.getApellidos(), x.getFechaNacimiento(), x.getTelefono(), x.getFechaRegistro(),
					getStrEstado(x.getEstado()), x.getGrado().getIdGrado(), x.getGrado().getDescripcion()};
			dtm.addRow(fila);
		}
	}
	private void registra() {
		String nom = txtNombre.getText().trim();
		String ape = txtApellido.getText().trim();
		String fec = txtFecha.getText().trim();
		String tel = txtTelefono.getText().trim();
		int posGrado = cboGrado.getSelectedIndex();
		
		if(!nom.matches(Validaciones.TEXTO)) {
			mensaje("El Nombre es de 2 a 20 caracteres");
		} else if(!ape.matches(Validaciones.TEXTO)) {
			mensaje("El Apellido es de 2 a 20 caracteres");
		} else if(!fec.matches(Validaciones.FECHA)) {
			mensaje("La fecha tiene formato de YYYY-mm-dd");
		} else if(!tel.matches(Validaciones.NUMERO)) {
			mensaje("El numero de telefono debe ser de 9 digitos");
		} else if(posGrado == 0) {
			mensaje("Selecciona un grado");
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
			objAutor.setEstado(chkEstado.isSelected()?1:0);
			AutorModel model = new AutorModel();
			int salida = model.insertarAutor(objAutor);
			if(salida > 0) {
				lista();
				limpiarCajasTexto();
				mensaje("Se insertó correctamente");
			} else {
				mensaje("Error en el Registro");
			}
		}
		
	}
	private void busca() {
		int fila = table.getSelectedRow();
		
		idSeleccionado = (Integer) table.getValueAt(fila, 0);
		String nombre = (String) table.getValueAt(fila, 1);
		String apellido = (String) table.getValueAt(fila, 2);
		Date fechaN = (Date) table.getValueAt(fila, 3);
		String telefono = (String) table.getValueAt(fila, 4);
		String estado = (String) table.getValueAt(fila, 6);
		int idGrado = (Integer) table.getValueAt(fila, 7);
		String Grado = (String) table.getValueAt(fila, 8);
		
		txtNombre.setText(nombre);
		txtApellido.setText(apellido);
		txtFecha.setText(String.valueOf(fechaN));
		txtTelefono.setText(telefono);
		cboGrado.setSelectedItem(idGrado + ": "+ Grado);
		chkEstado.setSelected(estado.equals("Activo")?true:false);
		
	}
	private void elimina() {
		if(idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		} else {
			AutorModel model = new AutorModel();
			int salida = model.eliminaAutor(idSeleccionado);
			if(salida > 0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se elimino correctamente");
			} else {
				mensaje("Error en la eliminacion");
			}
		}
	}
	
	
	private void actualiza() {
		String nom = txtNombre.getText().trim();
		String ape = txtApellido.getText().trim();
		String fec = txtFecha.getText().trim();
		String tel = txtTelefono.getText().trim();
		int posGrado = cboGrado.getSelectedIndex();
		
		if(!nom.matches(Validaciones.TEXTO)) {
			mensaje("El Nombre es de 2 a 20 caracteres");
		} else if(!ape.matches(Validaciones.TEXTO)) {
			mensaje("El Apellido es de 2 a 20 caracteres");
		} else if(!fec.matches(Validaciones.FECHA)) {
			mensaje("La fecha tiene formato de YYYY-mm-dd");
		} else if(!tel.matches(Validaciones.NUMERO)) {
			mensaje("El numero de telefono debe ser de 9 digitos");
		} else if(posGrado == 0) {
			mensaje("Selecciona un grado");
		} else {
			String grado = cboGrado.getSelectedItem().toString();
			String idGrado = grado.split(":")[0];
			
			Grado objGrado = new Grado();
			objGrado.setIdGrado(Integer.parseInt(idGrado));
			
			Autor objAutor = new Autor();
			objAutor.setIdAutor(idSeleccionado);
			objAutor.setNombres(nom);
			objAutor.setApellidos(ape);
			objAutor.setFechaNacimiento(Date.valueOf(fec));
			objAutor.setTelefono(tel);
			objAutor.setEstado(chkEstado.isSelected()?1:0);
			objAutor.setGrado(objGrado);
			
			AutorModel model = new AutorModel();
			int salida = model.actualizaAutor(objAutor);
			if(salida > 0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se actualizó correctamente");
			} else {
				mensaje ("Error en la Actualización");
			}
		}
	}
	
	public String getStrEstado(int estado) {
		if(estado == 1) return "Activo";
		else 			return "Inactivo";
	}
}
