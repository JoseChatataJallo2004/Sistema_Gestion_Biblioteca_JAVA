package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Autor;
import model.AutorModel;
import util.JComboBoxBD;

public class FrmConsultaAutor extends JInternalFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JComboBoxBD cboGrado;
	private JTable table;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmConsultaAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Autor");
		setBounds(100, 100, 1140, 645);
		getContentPane().setLayout(null);
		
		JLabel lblConsulta = new JLabel("CONSULTA DE AUTOR");
		lblConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsulta.setFont(new Font("Tempus Sans ITC", Font.BOLD, 38));
		lblConsulta.setBounds(347, 33, 439, 40);
		getContentPane().add(lblConsulta);
		
		JLabel lblGrado = new JLabel("Grado :");
		lblGrado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGrado.setBounds(118, 148, 90, 14);
		getContentPane().add(lblGrado);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"),"[ Todos ]");
		cboGrado.addItemListener(this);
		cboGrado.setBounds(233, 147, 268, 22);
		getContentPane().add(cboGrado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 199, 1104, 400);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Apellido", "Fecha Nacimiento", "Telefono", "Fecha Registro", "Estado", "Grado"
			}
		));
		
		//Tamaño a las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		scrollPane.setViewportView(table);
				
		//desabilita mover las columnas
		table.getTableHeader().setReorderingAllowed(false);
				
		//desabilita el cambio de tamaño
		table.getTableHeader().setResizingAllowed(false);
				 
		//selecciona una sola fila
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Desabilitar la edicion en las celdas
		table.setDefaultEditor(Object.class, null);
		
		
		//Colores alternados de la fila
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		defaults.putIfAbsent("Table.alternateRowColor", new Color(176, 245, 215));
				
		//Alineación
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
				
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		
		
		scrollPane.setViewportView(table);
	}
	
	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboGrado) {
			itemStateChangedCboGradoJComboBox(e);
		}
	}
	protected void itemStateChangedCboGradoJComboBox(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			int index = cboGrado.getSelectedIndex();
			String item = cboGrado.getSelectedItem().toString();
			
			System.out.println("Index >>> "+index);
			System.out.println("Item >>> "+item);
			
			AutorModel model = new AutorModel();
			List<Autor> lst = null;
			if(index == 0) {
				lst = new ArrayList<Autor>();
			}else if(index == 1) {
				lst = model.listaTodos();
			} else {
				String[]separados = item.split(":");
				int idGrado = Integer.parseInt(separados[0]);
				lst = model.listaPorGrado(idGrado);
				
			}
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			for(Autor x : lst) {
				Object[] f = {x.getIdAutor(),
						x.getNombres(),
						x.getApellidos(),
						x.getFechaNacimiento(),
						x.getTelefono(),
						x.getFechaRegistro(),
						x.getFormatoEstado(),
						x.getDescripcionGrado()};
				dtm.addRow(f);
			}
		}
	}
	
}
