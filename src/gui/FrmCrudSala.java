package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import entidad.Sala;
import entidad.Sede;
import model.LibroModel;
import model.SalaModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmCrudSala extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblMantenimientoSala;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JComboBoxBD cboSede;
	private JTextField textRecursos;
	private JTextField textNA;
	private JTextField textPiso;
	private JTextField textNumero;
	private JCheckBox chkEstado;
	private JButton btnIngresar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	int idSeleccionado = -1;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	private JScrollPane scrollPane;
	private JTable table;
	public FrmCrudSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Sala");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		lblMantenimientoSala = new JLabel("Mantenimiento Sala");
		lblMantenimientoSala.setOpaque(true);
		lblMantenimientoSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoSala.setForeground(Color.WHITE);
		lblMantenimientoSala.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoSala.setBackground(Color.RED);
		lblMantenimientoSala.setBounds(82, 22, 744, 59);
		getContentPane().add(lblMantenimientoSala);
		
		lblNewLabel = new JLabel("Numero");
		lblNewLabel.setBounds(82, 115, 83, 14);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Piso");
		lblNewLabel_1.setBounds(82, 150, 83, 14);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Numero de Alumnos");
		lblNewLabel_2.setBounds(82, 186, 166, 14);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Recursos");
		lblNewLabel_3.setBounds(82, 227, 122, 14);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Estado");
		lblNewLabel_4.setBounds(82, 269, 83, 14);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Sede");
		lblNewLabel_5.setBounds(82, 312, 98, 14);
		getContentPane().add(lblNewLabel_5);
		
		cboSede = new JComboBoxBD(rb.getString("SQL_SEDE"));
		cboSede.setBounds(214, 308, 234, 22);
		getContentPane().add(cboSede);
		
		textRecursos = new JTextField();
		textRecursos.setColumns(10);
		textRecursos.setBounds(214, 224, 234, 20);
		getContentPane().add(textRecursos);
		
		textNA = new JTextField();
		textNA.setColumns(10);
		textNA.setBounds(214, 183, 234, 20);
		getContentPane().add(textNA);
		
		textPiso = new JTextField();
		textPiso.setColumns(10);
		textPiso.setBounds(214, 147, 234, 20);
		getContentPane().add(textPiso);
		
		textNumero = new JTextField();
		textNumero.setColumns(10);
		textNumero.setBounds(214, 112, 234, 20);
		getContentPane().add(textNumero);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setSelected(true);
		chkEstado.setBounds(217, 265, 114, 23);
		getContentPane().add(chkEstado);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnIngresar(e);
			}
		});
		btnIngresar.setBounds(674, 111, 152, 23);
		getContentPane().add(btnIngresar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnEliminar(e);
			}
		});
		btnEliminar.setBounds(674, 160, 152, 23);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnActualizar(e);
			}
		});
		btnActualizar.setBounds(674, 218, 152, 23);
		getContentPane().add(btnActualizar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 349, 864, 210);
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
				"Codigo", "Numero", "Piso", "Numero de Alumnos", "Recursos", "Estado", "IdSede", "Sede"
			}
		));
		scrollPane.setViewportView(table);
		
		//TamaÃ±o a las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
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
				table.getColumnModel().getColumn(6).setMinWidth(0);
				table.getColumnModel().getColumn(6).setMaxWidth(0);
				table.getColumnModel().getColumn(6).setWidth(0);
				lista();
			lista();
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		ingresar();
	}
	protected void mouseClickedTable(MouseEvent e) {
		busca();
	}
	
	public String getStrEstado(int estado) {
		if (estado == 1) return "Activo";
		else			 return "Inactivo";
	}
	private void lista() {
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		SalaModel s=new SalaModel();
		List<Sala> lista=s.listaTodos();
		for(Sala x:lista) {
			Object[] fila= {x.getIdSala(),x.getNumero(),x.getPiso(),x.getNumAlumnos(),x.getRecursos(),getStrEstado(x.getEstado()) ,x.getSede().getIdSede(),x.getSede().getNombre()
		};
			dtm.addRow(fila);
	}
	}
	private void ingresar() {
		String num = textNumero.getText();
		String pis = textPiso.getText();
		String nalum = textNA.getText();
		String rec = textRecursos.getText();
		int indexSede = cboSede.getSelectedIndex();
		
		if(!num.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El n\u00famero es 1 letra y 3 digitos");
		}else if(!pis.matches(Validaciones.NUMERO)) {
			mensaje("El piso es 0 a 1000 digitos");
		}else if(!nalum.matches(Validaciones.NUMERO)) {
			mensaje("El n\u00famero es 0 a 1000 digitos");
		}else if(!rec.matches(Validaciones.TEXTO)) {
			mensaje("El recurso es 2 a 20 caracteres");
		}else if((indexSede ==0)) {
			mensaje("Seleccione una Sede");
		}else {
			String sed = cboSede.getSelectedItem().toString();
			String idSede = sed.split(":")[0];
			
			Sede objSede = new Sede();
			objSede.setIdSede(Integer.parseInt(idSede));
			
			
			Sala obj = new Sala();
			obj.setNumero(num);
			obj.setPiso(Integer.parseInt(pis));
			obj.setNumAlumnos(Integer.parseInt(nalum));
			obj.setRecursos(rec);
			obj.setEstado(chkEstado.isSelected()?1:0);
			obj.setSede(objSede);
			
			
			
			
			SalaModel model = new SalaModel();
			int s = model.insertarSala(obj);
			if (s>0) {
				lista();
				mensaje("Resgistro exitoso");
				limpiarCajasTexto();
			}else {
				mensaje("Error en el registro");
			}
		}	
	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	} 
	void limpiarCajasTexto() {
		textNumero.setText("");
		textPiso.setText("");
		textNA.setText("");
		textRecursos.setText("");
		cboSede.setSelectedIndex(0);
		chkEstado.setSelected(true);
		textNumero.requestFocus();
	}
	private void elimina() {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else {
			SalaModel model = new SalaModel();
			int salida = model.eliminarSala(idSeleccionado);
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
		String  numero=(String )table.getValueAt(fila, 1);
		int piso=(Integer )table.getValueAt(fila, 2);
		int na=(Integer )table.getValueAt(fila, 3);
		String recursos=(String )table.getValueAt(fila, 4);
		String estado=(String )table.getValueAt(fila, 5);
		int idSede =(Integer)table.getValueAt(fila, 6);
		String sede=(String )table.getValueAt(fila, 7);
		
		textNumero.setText(numero);
		textPiso.setText(String.valueOf(piso));
		textNA.setText(String.valueOf(na));
		textRecursos.setText(recursos);
		chkEstado.setSelected(estado.equals("Activo")?true:false);
		cboSede.setSelectedItem(idSede + ": " + sede);
		
	}
	private void actualizar() {
		String num = textNumero.getText();
		String pis = textPiso.getText();
		String nalum = textNA.getText();
		String rec = textRecursos.getText();
		int indexSede = cboSede.getSelectedIndex();
		
		if(!num.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El n\u00famero es 1 letra y 3 digitos");
		}else if(!pis.matches(Validaciones.NUMERO)) {
			mensaje("El piso es 0 a 1000 digitos");
		}else if(!nalum.matches(Validaciones.NUMERO)) {
			mensaje("El n\u00famero es 0 a 1000 digitos");
		}else if(!rec.matches(Validaciones.TEXTO)) {
			mensaje("El recurso es 2 a 20 caracteres");
		}else if((indexSede ==0)) {
			mensaje("Seleccione una Sede");
		}else {
			String sed = cboSede.getSelectedItem().toString();
			String idSede = sed.split(":")[0];
			
			Sede objSede = new Sede();
			objSede.setIdSede(Integer.parseInt(idSede));
			
			
			Sala obj = new Sala();
			obj.setIdSala(idSeleccionado);
			obj.setNumero(num);
			obj.setPiso(Integer.parseInt(pis));
			obj.setNumAlumnos(Integer.parseInt(nalum));
			obj.setRecursos(rec);
			obj.setEstado(chkEstado.isSelected()?1:0);
			obj.setSede(objSede);
			
			
			
			
			SalaModel model = new SalaModel();
			int s = model.actualizaSala(obj);
			if (s>0) {
				lista();
				idSeleccionado=-1;
				mensaje("Se Actualizo correctamente");
				limpiarCajasTexto();
			}else {
				mensaje("Error en el registro");
			}
		}	
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		elimina();
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualizar();
	}
}
