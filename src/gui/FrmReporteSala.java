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

import entidad.Sala;
import model.SalaModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;

public class FrmReporteSala extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblReporteDeSala;
	private JLabel lblPiso;
	private JTextField textPiso;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	public FrmReporteSala() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Sala");
		setBounds(100, 100, 1200, 600);
		getContentPane().setLayout(null);
		
		lblReporteDeSala = new JLabel("Reporte de Sala");
		lblReporteDeSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeSala.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReporteDeSala.setBounds(10, 28, 1161, 36);
		getContentPane().add(lblReporteDeSala);
		
		lblPiso = new JLabel("Piso");
		lblPiso.setBounds(131, 93, 93, 14);
		getContentPane().add(lblPiso);
		
		textPiso = new JTextField();
		textPiso.setColumns(10);
		textPiso.setBounds(262, 90, 348, 20);
		getContentPane().add(textPiso);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnFiltrar(e);
			}
		});
		btnFiltrar.setBounds(705, 89, 166, 23);
		getContentPane().add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(27, 139, 1144, 420);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());

	}
	protected void actionPerformedBtnFiltrar(ActionEvent e) {
		
String piso=textPiso.getText().trim();
		
		SalaModel model =new SalaModel();
		List<Sala> lista=model.listaporNombre(piso);
		
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
		
		String jasper = "Sala.jasper";
		//Se obtiene el reporte
		JasperPrint print = GeneradorReporte.genera(jasper, dataSource, null);
		
		JRViewer jRViewer = new JRViewer(print);
		
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
		
	}
}
