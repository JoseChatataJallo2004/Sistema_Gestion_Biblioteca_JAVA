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

import entidad.Alumno;
import model.AlumnoModel;
import util.JComboBoxBD;

public class FrmConsultaAlumno extends JInternalFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JComboBoxBD cboPais;
	private JTable table;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmConsultaAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Alumno");
		setBounds(100, 100, 1120, 617);
		getContentPane().setLayout(null);
		
		JLabel lblConsultaDeAlumno = new JLabel("CONSULTA DE ALUMNO");
		lblConsultaDeAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeAlumno.setFont(new Font("Tempus Sans ITC", Font.BOLD, 38));
		lblConsultaDeAlumno.setBounds(323, 37, 508, 40);
		getContentPane().add(lblConsultaDeAlumno);
		
		JLabel lblPais = new JLabel("País :");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPais.setBounds(115, 112, 90, 14);
		getContentPane().add(lblPais);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"), "[ Todos ]");
		cboPais.addItemListener(this);
		cboPais.setBounds(228, 111, 271, 22);
		getContentPane().add(cboPais);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 1084, 409);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombres", "Apellidos", "Telefono", "Dni", "Correo", "F.Nacimiento", "Fecha Registro", "Estado", "Pa\u00EDs"
			}
		));
		
		//Tamaño a las columnas
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		table.getColumnModel().getColumn(7).setPreferredWidth(170);
		table.getColumnModel().getColumn(8).setPreferredWidth(70);
		table.getColumnModel().getColumn(9).setPreferredWidth(100);
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
		//UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		//defaults.putIfAbsent("Table.alternateRowColor", new Color(176, 245, 215));
						
		//Alineación
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
						
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
		
		scrollPane.setViewportView(table);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboPais) {
			itemStateChangedCboPaisJComboBoxBD(e);
		}
	}
	protected void itemStateChangedCboPaisJComboBoxBD(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			int index = cboPais.getSelectedIndex();
			String item = cboPais.getSelectedItem().toString();
			
			System.out.println("Index >>> "+index);
			System.out.println("Item >>> "+item);
			
			AlumnoModel model = new AlumnoModel();
			List<Alumno> lst = null;
			if(index == 0) {
				lst = new ArrayList<Alumno>();
			}else if(index == 1) {
				lst = model.listaTodos();
			} else {
				String[]separados = item.split(":");
				int idPais = Integer.parseInt(separados[0]);
				lst = model.listaPorPais(idPais);
				
			}
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			for(Alumno x : lst) {
				Object[] f = {x.getIdAlumno(),
						x.getNombres(),
						x.getApellidos(),
						x.getTelefono(),
						x.getDni(),
						x.getCorreo(),
						x.getFechaNacimiento(),
						x.getFechaRegistro(),
						x.getFormatoEstado(),
						x.getNombrePais()};
				dtm.addRow(f);
			}
		}
	}
}
