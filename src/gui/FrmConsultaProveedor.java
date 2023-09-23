package gui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entidad.Libro;
import entidad.Proveedor;
import model.LibroModel;
import model.ProveedorModel;
import util.JComboBoxBD;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmConsultaProveedor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblConsultaDeProveedor;
	private JLabel lblPais;
	private JScrollPane scrollPane;
	private JTable table;
	private JComboBoxBD cboPais;
	private ResourceBundle rb=ResourceBundle.getBundle("combo");
	public FrmConsultaProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Proveedor");
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		lblConsultaDeProveedor = new JLabel("Consulta de Proveedor");
		lblConsultaDeProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeProveedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaDeProveedor.setBounds(196, 25, 572, 36);
		getContentPane().add(lblConsultaDeProveedor);
		
		lblPais = new JLabel("Pais");
		lblPais.setBounds(107, 103, 113, 14);
		getContentPane().add(lblPais);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"),"[ Todos ]");
		cboPais.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				itemStateChangedCboPais(e);
			}
		});
		cboPais.setBounds(241, 99, 454, 22);
		getContentPane().add(cboPais);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 161, 964, 398);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombres", "Apellidos", "Dni", "Direccion", "Telefono", "Correo", "Fecha Registro", "Estado", "Pais"
			}
		));
		scrollPane.setViewportView(table);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}

	protected void itemStateChangedCboPais(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			int index = cboPais.getSelectedIndex();
			String item = cboPais.getSelectedItem().toString();		
			System.out.println("Index >>> " + index);
			System.out.println("Item >>> " + item);		
			
			ProveedorModel model=new ProveedorModel();
			List<Proveedor> lst=null;
			if(index==0) {
				lst=new ArrayList<Proveedor>();
			}else if (index==1) {
				lst=model.listaTodos();
			}else {
				String[]separados=item.split(":");
				int idpais=Integer.parseInt(separados[0]);
				lst=model.listapais(idpais);
			}
			DefaultTableModel dtm=(DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			for (Proveedor x : lst) {
				Object[] f= {
						x.getIdProveedor(),
						x.getNombre(),
						x.getApellido(),
						x.getDni(),
						x.getDireccion(),
						x.getTelefono(),
						x.getCorreo(),
						x.getFechaRegistro(),
						x.getEstado()==1?"Activo":"Inactivo",
						x.getPais().getNombre()
							};
				dtm.addRow(f);
			}
		}
	}
}
