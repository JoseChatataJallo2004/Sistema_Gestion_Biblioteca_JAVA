package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import entidad.Categoria;
import entidad.Libro;
import model.LibroModel;
import util.JComboBoxBD;
import util.Validaciones;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmCrudLibro extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblMantenimientoLibro;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField textTitulo;
	private JTextField textAnio;
	private JTextField textSerie;
	private JCheckBox chkEstado;
	 private JComboBoxBD cboCategoriaa;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnIngresar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	
	// -1 indica que no se ha selecionado nada en la grilla o Jtable
		int idSeleccionado = -1;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	public FrmCrudLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Libro");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		lblMantenimientoLibro = new JLabel("Mantenimiento Libro");
		lblMantenimientoLibro.setOpaque(true);
		lblMantenimientoLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoLibro.setForeground(Color.WHITE);
		lblMantenimientoLibro.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoLibro.setBackground(Color.RED);
		lblMantenimientoLibro.setBounds(89, 11, 744, 59);
		getContentPane().add(lblMantenimientoLibro);
		
		lblNewLabel = new JLabel("T\u00edtulo");
		lblNewLabel.setBounds(96, 102, 118, 14);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("A\u00f1o");
		lblNewLabel_1.setBounds(96, 139, 78, 14);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Serie");
		lblNewLabel_2.setBounds(97, 167, 92, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Estado");
		lblNewLabel_3.setBounds(89, 205, 108, 14);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Categor\u00eda");
		lblNewLabel_4.setBounds(89, 241, 108, 14);
		getContentPane().add(lblNewLabel_4);
		
		textTitulo = new JTextField();
		textTitulo.setBounds(199, 99, 324, 20);
		getContentPane().add(textTitulo);
		textTitulo.setColumns(10);
		
		textAnio = new JTextField();
		textAnio.setColumns(10);
		textAnio.setBounds(199, 136, 324, 20);
		getContentPane().add(textAnio);
		
		textSerie = new JTextField();
		textSerie.setColumns(10);
		textSerie.setBounds(199, 164, 324, 20);
		getContentPane().add(textSerie);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setSelected(true);
		chkEstado.setBounds(203, 201, 97, 23);
		getContentPane().add(chkEstado);
		
		cboCategoriaa = new JComboBoxBD(rb.getString("SQL_CATEGORIA"));
		cboCategoriaa.setBounds(199, 237, 324, 22);
		getContentPane().add(cboCategoriaa);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 295, 864, 264);
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
				"Codigo", "Titulo", "A\u00F1o", "Serie", "Estado", "IdCategoria", "Categoria"
			}
		));
		scrollPane.setViewportView(table);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnIngresar(e);
			}
		});
		btnIngresar.setBounds(678, 98, 155, 23);
		getContentPane().add(btnIngresar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		btnEliminar.setBounds(678, 135, 155, 23);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizar(e);
			}
		});
		btnActualizar.setBounds(678, 173, 155, 23);
		getContentPane().add(btnActualizar);
		//TamaÃ±o a las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		scrollPane.setViewportView(table);
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
		
		//alineaciÃ³n
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		//Desabilitar la edicion en las celdas
		table.setDefaultEditor(Object.class, null);
		//Se oculta la fila 4
		table.getColumnModel().getColumn(5).setMinWidth(0);
		table.getColumnModel().getColumn(5).setMaxWidth(0);
		table.getColumnModel().getColumn(5).setWidth(0);
		lista();
	}
	void limpiarCajasTexto() {
		textTitulo.setText("");
		textSerie.setText("");
		textAnio.setText("");
		cboCategoriaa.setSelectedIndex(0);
		chkEstado.setSelected(true);
		textTitulo.requestFocus();
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		registra();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		elimina();
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualiza();
	}
	private void registra() {
		String ti=textTitulo.getText();
		String annio=textAnio.getText();
		String ser=textSerie.getText();
		int  Indecate=cboCategoriaa.getSelectedIndex();
		if (!ti.matches(Validaciones.TEXTO)) {
			mensaje("El T\u00edtulo  es de 2 a 30 caracteres");
		}else if (!annio.matches(Validaciones.ANNO)) {
			mensaje("El Año es de 4 d\u00edgitos");
		}else if (!ser.matches(Validaciones.PLACA)) {
			mensaje("La Serie son 2 Letras May\u00fasculas y 4 D\u00edgitos");
		}else if((Indecate ==0)) {
			mensaje("Seleccione una Categor\u00eda");
		}else {
			
			String categoria = cboCategoriaa.getSelectedItem().toString();
			String idCategoria = categoria.split(":")[0];
			
			Categoria objcategoria = new Categoria();

			objcategoria.setIdCategoria(Integer.parseInt(idCategoria));
		
			Libro obj = new Libro();
			obj.setTitulo(ti);
			obj.setAnnio(Integer.parseInt(annio));
			obj.setSerie(ser);
			obj.setEstado(chkEstado.isSelected()?1:0);
			obj.setCategoria(objcategoria);
			
			LibroModel model = new LibroModel();
			int salida = model.registroLibro(obj);
			if (salida>0) {
				lista();
				mensaje("Registro exitoso");	
				limpiarCajasTexto();
			}else {
				mensaje("Error en el registro");
			}
			
		}
		
	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	} 
	private void lista() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		LibroModel l=new LibroModel();
		List<Libro> lista=l.listaTodos();
		for(Libro x:lista) {
			Object[] fila= {x.getIdLibro(),x.getTitulo(),x.getAnnio(),x.getSerie(),getStrEstado(x.getEstado()) ,x.getCategoria().getIdCategoria(),x.getCategoria().getDescripcion()
		};
			dtm.addRow(fila);
	}
	}
	private void elimina() {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else {
			LibroModel model = new LibroModel();
			int salida = model.eliminarLibro(idSeleccionado);
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
		int anio=(Integer )table.getValueAt(fila, 2);
		String serie=(String )table.getValueAt(fila, 3);
		String estado=(String )table.getValueAt(fila, 4);
		int idCate =(Integer)table.getValueAt(fila, 5);
		String categoria=(String )table.getValueAt(fila, 6);
		
		textTitulo.setText(titulo);
		textAnio.setText(String.valueOf(anio));
		textSerie.setText(serie);
		cboCategoriaa.setSelectedItem(idCate + ": " + categoria);
		chkEstado.setSelected(estado.equals("Activo")?true:false);
	}
	
	protected void mouseClickedTable(MouseEvent e) {
		busca();
	}
	public String getStrEstado(int estado) {
		if (estado == 1) return "Activo";
		else			 return "Inactivo";
	}
	
	private void actualiza() {
		String ti=textTitulo.getText();
		String annio=textAnio.getText();
		String ser=textSerie.getText();
		int  Indecate=cboCategoriaa.getSelectedIndex();
		if (!ti.matches(Validaciones.TEXTO)) {
			mensaje("El T\u00edtulo  es de 2 a 30 caracteres");
		}else if (!annio.matches(Validaciones.ANNO)) {
			mensaje("El Año es de 4 d\u00edgitos");
		}else if (!ser.matches(Validaciones.PLACA)) {
			mensaje("La Serie son 2 Letras May\u00fasculas y 4 D\u00edgitos");
		}else if((Indecate ==0)) {
			mensaje("Seleccione una Categor\u00eda");
		}else {
			
			String categoria = cboCategoriaa.getSelectedItem().toString();
			String idCategoria = categoria.split(":")[0];
			
			Categoria objcategoria = new Categoria();

			objcategoria.setIdCategoria(Integer.parseInt(idCategoria));
		
			Libro obj = new Libro();
			obj.setIdLibro(idSeleccionado);
			obj.setTitulo(ti);
			obj.setAnnio(Integer.parseInt(annio));
			obj.setSerie(ser);
			obj.setEstado(chkEstado.isSelected()?1:0);
			obj.setCategoria(objcategoria);
			
			LibroModel model = new LibroModel();
			int salida = model.actualizaLibro(obj);
			if (salida>0) {
				lista();
				idSeleccionado=-1;
				mensaje("Se Actualizo correctamente");	
				limpiarCajasTexto();
			}else {
				mensaje("Error en el registro");
			}
			
		}
		
	}
}
