package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import entidad.Alumno;
import model.AlumnoModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;

public class FrmReporteAlumno extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblReporteDeAlumno;
	private JLabel lblNombre;
	private JTextField txtFiltro;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	public FrmReporteAlumno() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de alumno");
		setBounds(100, 100, 1200, 600);
		getContentPane().setLayout(null);
		
		lblReporteDeAlumno = new JLabel("REPORTE DE ALUMNO");
		lblReporteDeAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeAlumno.setFont(new Font("Tempus Sans ITC", Font.BOLD, 38));
		lblReporteDeAlumno.setBounds(377, 28, 439, 40);
		getContentPane().add(lblReporteDeAlumno);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(76, 114, 68, 19);
		getContentPane().add(lblNombre);
		
		txtFiltro = new JTextField();
		txtFiltro.setColumns(10);
		txtFiltro.setBounds(180, 115, 148, 20);
		getContentPane().add(txtFiltro);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(408, 114, 89, 23);
		getContentPane().add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ReporteAlumno", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelReporte.setBounds(10, 156, 1164, 389);
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
		
		AlumnoModel model = new AlumnoModel();
		List<Alumno> lista = model.listaPorNombre(filtro+"%");
		
		//Datos del reporte
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
				
		//Dise�o del reporte
		String jasper = "reporteAlumno.jasper";	
				
		//Se obtiene el reporte
		JasperPrint print = GeneradorReporte.genera(jasper, dataSource, null);			
		JRViewer jRViewer = new JRViewer(print);
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
	}
}
