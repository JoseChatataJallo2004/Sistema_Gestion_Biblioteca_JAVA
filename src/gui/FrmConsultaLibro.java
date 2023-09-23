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

import entidad.Libro;
import model.LibroModel;
import util.JComboBoxBD;

public class FrmConsultaLibro extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblConsultaDeLibro;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable table;
	private JComboBoxBD cboCategoria;

	private ResourceBundle rb=ResourceBundle.getBundle("combo");
	public FrmConsultaLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Libro");
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		lblConsultaDeLibro = new JLabel("Consulta de Libro");
		lblConsultaDeLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeLibro.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaDeLibro.setBounds(223, 25, 572, 36);
		getContentPane().add(lblConsultaDeLibro);
		
		lblNewLabel = new JLabel("Categoria");
		lblNewLabel.setBounds(98, 110, 113, 14);
		getContentPane().add(lblNewLabel);
		
		cboCategoria = new JComboBoxBD(rb.getString("SQL_CATEGORIA"),"[ Todos ]");
		cboCategoria.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemStateChangedCboCategoria(e);
			}
		});
		cboCategoria.setBounds(201, 106, 251, 22);
		getContentPane().add(cboCategoria);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 156, 852, 376);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Titulo", "A\u00F1o", "Serie","Fecha Registro", "Estado", "Categoria"
			}
		));
		//Tamaño a las columnas

				table.getColumnModel().getColumn(0).setPreferredWidth(30);

				table.getColumnModel().getColumn(1).setPreferredWidth(150);

				table.getColumnModel().getColumn(2).setPreferredWidth(60);

				table.getColumnModel().getColumn(3).setPreferredWidth(150);

				table.getColumnModel().getColumn(4).setPreferredWidth(180);

				table.getColumnModel().getColumn(5).setPreferredWidth(120);
				
				table.getColumnModel().getColumn(5).setPreferredWidth(100);

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
	protected void itemStateChangedCboCategoria(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			int index = cboCategoria.getSelectedIndex();
			String item = cboCategoria.getSelectedItem().toString();		
			System.out.println("Index >>> " + index);
			System.out.println("Item >>> " + item);		
			
			LibroModel model=new LibroModel();
			List<Libro> lst=null;
			if(index==0) {
				lst=new ArrayList<Libro>();
			}else if (index==1) {
				lst=model.listaTodos();
			}else {
				String[]separados=item.split(":");
				int idCategoria=Integer.parseInt(separados[0]);
				lst=model.listaCategoria(idCategoria);
			}
			DefaultTableModel dtm=(DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			for (Libro x : lst) {
				Object[] f= {x.getIdLibro(),
						x.getTitulo(),
						x.getAnnio(),
						x.getSerie(),
						x.getFechaRegistro(),
						x.getEstado()==1?"Activo":"Inactivo",
						x.getCategoria().getDescripcion()
							};
				dtm.addRow(f);
			}
		}
	}
}
