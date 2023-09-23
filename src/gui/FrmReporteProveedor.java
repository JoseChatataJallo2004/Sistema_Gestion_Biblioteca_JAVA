package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import entidad.Proveedor;
import model.ProveedorModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;

public class FrmReporteProveedor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblReporteDeProveedor;
	private JLabel lblNombre;
	private JTextField textNombre;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	public FrmReporteProveedor() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Proveedor");
		setBounds(100, 100, 1200, 600);
		getContentPane().setLayout(null);
		
		lblReporteDeProveedor = new JLabel("Reporte de Proveedor");
		lblReporteDeProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeProveedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReporteDeProveedor.setBounds(0, 24, 1161, 36);
		getContentPane().add(lblReporteDeProveedor);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(99, 89, 93, 14);
		getContentPane().add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(254, 86, 458, 20);
		getContentPane().add(textNombre);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnFiltrar(e);
			}
		});
		btnFiltrar.setBounds(775, 85, 166, 23);
		getContentPane().add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(17, 139, 1144, 420);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());

	}
	protected void actionPerformedBtnFiltrar(ActionEvent e) {
		String nombre = textNombre.getText().trim();

		

		ProveedorModel model = new ProveedorModel();
		List<Proveedor> lista = model.listaNombre(nombre);
		// datos del reporte
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
		String jasper = "ReportePro.jasper";
		JasperPrint print = GeneradorReporte.genera(jasper, dataSource, null);
		JRViewer jRViewer = new JRViewer(print);
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
		
		
	}
}
