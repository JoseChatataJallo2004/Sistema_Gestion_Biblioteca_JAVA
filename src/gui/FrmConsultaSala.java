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

import entidad.Sala;
import model.SalaModel;
import util.JComboBoxBD;

public class FrmConsultaSala extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblConsultaDeSala;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable table;
	private JComboBoxBD cboSede;
	private ResourceBundle rb=ResourceBundle.getBundle("combo");
	
	public FrmConsultaSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Sala");
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		lblConsultaDeSala = new JLabel("Consulta de Sala");
		lblConsultaDeSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeSala.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaDeSala.setBounds(221, 24, 572, 36);
		getContentPane().add(lblConsultaDeSala);
		
		lblNewLabel = new JLabel("Sede");
		lblNewLabel.setBounds(84, 93, 101, 14);
		getContentPane().add(lblNewLabel);
		
		cboSede = new JComboBoxBD(rb.getString("SQL_SEDE"), " [Todos]" );
		cboSede.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemStateChangedCboSede(e);
			}
		});
		cboSede.setBounds(171, 89, 253, 22);
		getContentPane().add(cboSede);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 148, 921, 379);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Numero", "Piso", "N\u00B0 Alumnos", "Recursos", "Fecha Registro", "Estado", "Sede"
			}
		));
		//Tamaño a las columnas

				table.getColumnModel().getColumn(0).setPreferredWidth(30);

				table.getColumnModel().getColumn(1).setPreferredWidth(60);

				table.getColumnModel().getColumn(2).setPreferredWidth(30);

				table.getColumnModel().getColumn(3).setPreferredWidth(50);

				table.getColumnModel().getColumn(4).setPreferredWidth(150);

				table.getColumnModel().getColumn(5).setPreferredWidth(200);
				
				table.getColumnModel().getColumn(6).setPreferredWidth(100);

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

				table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);

				table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

				table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		scrollPane.setViewportView(table);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}

	protected void itemStateChangedCboSede(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			int index = cboSede.getSelectedIndex();
			String item = cboSede.getSelectedItem().toString();		
			System.out.println("Index >>> " + index);
			System.out.println("Item >>> " + item);		
			
			SalaModel model=new SalaModel();
			List<Sala> lst=null;
			if(index==0) {
				lst=new ArrayList<Sala>();
			}else if (index==1) {
				lst=model.listaTodos();
			}else {
				String[]separados=item.split(":");
				int idSede=Integer.parseInt(separados[0]);
				lst=model.listaSede(idSede);
			}
			DefaultTableModel dtm=(DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			for (Sala x : lst) {
				Object[] f= {x.getIdSala(),
						x.getNumero(),
						x.getPiso(),
						x.getNumAlumnos(),
						x.getRecursos(),
						x.getFechaRegistro(),
						x.getEstado()==1?"Activo":"Inactivo",
						x.getSede().getNombre()
							};
				dtm.addRow(f);
			}
		}
		
		
		
		
		
		
		
	}
}
