package gui;

import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import entidad.Autor;
import model.AutorModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmReporteAutor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtFiltro;
	private JLabel lblNombre;
	private JLabel lblReporteDeAutor;
	private JPanel panelReporte;
	private JButton btnFiltrar;

	public FrmReporteAutor() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Autor");
		setBounds(100, 100, 1200, 600);
		getContentPane().setLayout(null);
		
		lblReporteDeAutor = new JLabel("REPORTE DE AUTOR");
		lblReporteDeAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeAutor.setFont(new Font("Tempus Sans ITC", Font.BOLD, 38));
		lblReporteDeAutor.setBounds(356, 35, 439, 40);
		getContentPane().add(lblReporteDeAutor);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(76, 128, 68, 19);
		getContentPane().add(lblNombre);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(180, 129, 148, 20);
		getContentPane().add(txtFiltro);
		txtFiltro.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(408, 128, 89, 23);
		getContentPane().add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ReporteAutor", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelReporte.setBounds(10, 170, 1164, 389);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}
	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		String filtro = txtFiltro.getText().trim();
		
		AutorModel model = new AutorModel();
		List<Autor> lista = model.listaPorNombre(filtro+"%");
		
		//Datos del reporte
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
				
		//Dise�o del reporte
		String jasper = "reporteAutor.jasper";	
				
		//Se obtiene el reporte
		JasperPrint print = GeneradorReporte.genera(jasper, dataSource, null);			
		JRViewer jRViewer = new JRViewer(print);
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
	}
}
