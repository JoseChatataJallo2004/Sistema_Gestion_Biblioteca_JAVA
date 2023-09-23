package gui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Libro;
import model.LibroModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;

public class FrmReporteLibro extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblReporteDeLibro;
	private JLabel lblNewLabel;
	private JTextField textNombre;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	public FrmReporteLibro() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Libro");
		setBounds(100, 100, 1200, 600);
		getContentPane().setLayout(null);
		
		lblReporteDeLibro = new JLabel("Reporte de Libro");
		lblReporteDeLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeLibro.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReporteDeLibro.setBounds(10, 23, 1161, 36);
		getContentPane().add(lblReporteDeLibro);
		
		lblNewLabel = new JLabel("Titulo");
		lblNewLabel.setBounds(107, 97, 93, 14);
		getContentPane().add(lblNewLabel);
		
		textNombre = new JTextField();
		textNombre.setBounds(211, 94, 458, 20);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnFiltrar(e);
			}
		});
		btnFiltrar.setBounds(732, 93, 166, 23);
		getContentPane().add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(30, 139, 1144, 420);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());

	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	protected void actionPerformedBtnFiltrar(ActionEvent e) {
		String nombre=textNombre.getText().trim();
		
		LibroModel model =new LibroModel();
		List<Libro> lista=model.listaporNombre(nombre);
		
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
		
		String jasper = "ReporteLibro.jasper";
		//Se obtiene el reporte
		JasperPrint print = GeneradorReporte.genera(jasper, dataSource, null);
		
		JRViewer jRViewer = new JRViewer(print);
		
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
	
}
}
