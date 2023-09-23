package gui;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import entidad.Pais;
import model.AlumnoModel;
import util.JComboBoxBD;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

public class FrmCrudAlumno extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JTextField txtFechaNac;
	private JTable table;
	private JButton btnIngresar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JCheckBox chkActivo;

	int idSeleccionado = -1;
	
	private JComboBoxBD cboPais;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");	
	
	
	public FrmCrudAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Alumno");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Mantenimiento Alumno");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblTitulo.setBounds(195, 20, 420, 40);
		getContentPane().add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombres:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(57, 78, 69, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellido.setBounds(57, 103, 69, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefono.setBounds(57, 128, 69, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDni.setBounds(57, 153, 69, 14);
		getContentPane().add(lblDni);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorreo.setBounds(57, 178, 69, 14);
		getContentPane().add(lblCorreo);
		
		JLabel lblFecha = new JLabel("Fecha Nacimiento:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFecha.setBounds(57, 203, 111, 14);
		getContentPane().add(lblFecha);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(57, 228, 69, 14);
		getContentPane().add(lblPais);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(193, 76, 336, 20);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(193, 101, 336, 20);
		getContentPane().add(txtApellidos);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(193, 126, 150, 20);
		getContentPane().add(txtTelefono);
		
		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(193, 151, 150, 20);
		getContentPane().add(txtDNI);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(193, 176, 336, 20);
		getContentPane().add(txtCorreo);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setColumns(10);
		txtFechaNac.setBounds(193, 201, 150, 20);
		getContentPane().add(txtFechaNac);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboPais.setBounds(193, 225, 200, 22);
		getContentPane().add(cboPais);
		
		chkActivo = new JCheckBox("Activo");
		chkActivo.setBounds(193, 254, 97, 23);
		getContentPane().add(chkActivo);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnIngresarJButton(e);
			}
		});
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnIngresar.setBounds(628, 74, 89, 23);
		getContentPane().add(btnIngresar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminarJButton(e);
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEliminar.setBounds(628, 124, 89, 23);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizarJButton(e);
			}
		});
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnActualizar.setBounds(628, 174, 89, 23);
		getContentPane().add(btnActualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 297, 864, 254);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseClickedTableJTable(e);
			}
		});
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"C\u00F3digo", "Nombre", "Apellido", "Telefono", "DNI", "Correo", "Fecha Nacimiento", "idPais", "Pais", "Estado"
				}
			));
		scrollPane.setViewportView(table);
		
		//Tamaño de las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(80);
		table.getColumnModel().getColumn(9).setPreferredWidth(70);
		scrollPane.setViewportView(table);
		
		//deshabilitar mover las columnas
		table.getTableHeader().setReorderingAllowed(false);
		
		//deshabilitar cambio de tamaño
		table.getTableHeader().setResizingAllowed(false);
		
		//selecciona una sola fila
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		//color de la fila seleccionada
		table.setSelectionBackground(Color.LIGHT_GRAY);
		
		//alineacion
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
					
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
			
		//Desabilitar la edicion en las celdas
		table.setDefaultEditor(Object.class, null);			
		
		//Se oculta la columna 7 (idPais)
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setWidth(0);
		
		lista();
	}
	
	private void lista() {
		//El que maneja la data del Table
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		AlumnoModel m = new AlumnoModel();
		List<Alumno> lista = m.listaTodos();
		
		for(Alumno x: lista) {
			Object[] fila = {x.getIdAlumno(), x.getNombres(), x.getApellidos(), x.getTelefono(), x.getDni(), x.getCorreo(), x.getFechaNacimiento(), x.getPais().getIdPais(), x.getPais().getNombre(), getStrEstado(x.getEstado())};
			dtm.addRow(fila);
		}
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	void limpiarCajasTexto() {
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtDNI.setText("");
		txtCorreo.setText("");
		txtFechaNac.setText("");
		cboPais.setSelectedIndex(0);
		chkActivo.setSelected(true);
		
		txtNombres.requestFocus();
	}
			
	protected void actionPerformedBtnIngresarJButton(ActionEvent e) {
		registra();
	}
	protected void actionPerformedBtnEliminarJButton(ActionEvent e) {
		elimina();
	}
	protected void actionPerformedBtnActualizarJButton(ActionEvent e) {
		actualiza();
	}
	
	
	
	private void registra() {
		String nom = txtNombres.getText().trim();
		String ape = txtApellidos.getText().trim();
		String tel = txtTelefono.getText().trim();
		String fec = txtFechaNac.getText().trim();
		String dni = txtDNI.getText().trim();
		String correo = txtCorreo.getText().trim();
		int posPais = cboPais.getSelectedIndex();		
		
		if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if(!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
		}else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("la fecha tiene formato YYYY-MM-dd");
		}else if (posPais == 0) {
			mensaje("Selecciona un Pais");
		}else if (!dni.matches(Validaciones.DNI)) {
			mensaje("El DNI tiene 8 digitos");
		}else {
			String pais = cboPais.getSelectedItem().toString();
			String idPais = pais.split(":")[0];
			
			Pais objPais = new Pais();
			objPais.setIdPais(Integer.parseInt(idPais));
			
			Alumno objAlu = new Alumno();
			objAlu.setNombres(nom);
			objAlu.setFechaNacimiento(Date.valueOf(fec));
			//Si esta seleccionado ponemos 1, caso contrario, 0
			objAlu.setEstado(chkActivo.isSelected()?1:0);
			objAlu.setPais(objPais);
			objAlu.setApellidos(ape);
			objAlu.setCorreo(correo);
			objAlu.setDni(dni);
			objAlu.setTelefono(tel);
			
			AlumnoModel model = new AlumnoModel();
			int salida = model.insertarAlumno(objAlu);
			if (salida > 0) {
				lista();
				limpiarCajasTexto();
				mensaje("Se inserto correctamente");
			}else {
				mensaje("Error en el Registro");
			}
		}
	}
	
	private void busca() {
		//Obtener la fila
		int fila = table.getSelectedRow();
		
		//Obtener el valor de las celdas
		//idSeleccionado es una variable global
		idSeleccionado = (Integer) table.getValueAt(fila, 0);
		String nombre = (String) table.getValueAt(fila, 1);
		String apellido = (String) table.getValueAt(fila, 2);
		String telefono = (String) table.getValueAt(fila, 3);
		String dni = (String) table.getValueAt(fila, 4);
		String correo = (String) table.getValueAt(fila, 5);
		Date fecNac = (Date) table.getValueAt(fila, 6);
		int idPais = (Integer) table.getValueAt(fila, 7);
		String pais = (String) table.getValueAt(fila, 8);
		String estado = (String) table.getValueAt(fila, 9);
		
		txtNombres.setText(nombre);
		txtApellidos.setText(apellido);
		txtTelefono.setText(telefono);
		txtDNI.setText(dni);
		txtCorreo.setText(correo);
		txtFechaNac.setText(String.valueOf(fecNac));
		cboPais.setSelectedItem(idPais + ": " + pais);
		chkActivo.setSelected(estado.equals("Activo")?true:false);
	}
	
	private void elimina() {
		if(idSeleccionado == -1){
			mensaje("Seleccione una fila");
		}else {
			AlumnoModel model = new AlumnoModel();
			int salida = model.eliminaAlumno(idSeleccionado);
			if (salida > 0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se elimino correctamente");
			}else {
				mensaje("Error en la eliminacion");
			}
		}
	}
	
	private void actualiza() {
		String nom = txtNombres.getText().trim();
		String ape = txtApellidos.getText().trim();
		String tel = txtTelefono.getText().trim();
		String fec = txtFechaNac.getText().trim();
		String dni = txtDNI.getText().trim();
		String correo = txtCorreo.getText().trim();
		int posPais = cboPais.getSelectedIndex();		
		
		if(idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("la fecha tiene formato YYYY-MM-dd");
		}else if (posPais == 0) {
			mensaje("Selecciona un Pa�s");
		}else if (!dni.matches(Validaciones.DNI)) {
			mensaje("El DNI tiene 8 digitos");
		}else {							
			String pais = cboPais.getSelectedItem().toString();
			String idPais = pais.split(":")[0];
			
			Pais objPais = new Pais();
			objPais.setIdPais(Integer.parseInt(idPais));
			
			Alumno objAlu = new Alumno();
			objAlu.setIdAlumno(idSeleccionado);
			objAlu.setNombres(nom);
			objAlu.setFechaNacimiento(Date.valueOf(fec));
			//Si esta seleccionado ponemos 1, caso contrario, 0
			objAlu.setEstado(chkActivo.isSelected()?1:0);
			objAlu.setPais(objPais);
			objAlu.setApellidos(ape);
			objAlu.setCorreo(correo);
			objAlu.setDni(dni);
			objAlu.setTelefono(tel);
			
			AlumnoModel model = new AlumnoModel();
			int salida = model.actualizaClub(objAlu);
			if (salida > 0) {
				lista();
				limpiarCajasTexto();
				mensaje("Se ha actualizado correctamente");
			}else {
				mensaje("Error al actualizar");
			}
		}
	}
	
	protected void mouseClickedTableJTable(MouseEvent e) {
		busca();
	}
	
	public String getStrEstado(int estado) {
		if(estado == 1) return "Activo";
		else 			return "Inactivo";
	}
}
