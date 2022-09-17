package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistroAlumno extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JTextField textField_2;

	public FrmRegistroAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Alumno");
		setBounds(100, 100, 569, 600);
		getContentPane().setLayout(null);
		
		JLabel lblAlumno = new JLabel("Registro de Alumno:");
		lblAlumno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlumno.setBounds(179, 34, 176, 35);
		getContentPane().add(lblAlumno);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(56, 95, 46, 14);
		getContentPane().add(lblNombres);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(228, 95, 202, 20);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(56, 136, 46, 14);
		getContentPane().add(lblApellidos);
		
		textField = new JTextField();
		textField.setBounds(228, 136, 202, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(56, 182, 46, 14);
		getContentPane().add(lblTelefono);
		
		textField_1 = new JTextField();
		textField_1.setBounds(228, 182, 202, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(56, 230, 46, 14);
		getContentPane().add(lblDNI);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(228, 230, 202, 20);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(56, 279, 46, 14);
		getContentPane().add(lblCorreo);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(228, 279, 202, 20);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		JLabel lblFecNac = new JLabel("Fecha de Nacimiento:");
		lblFecNac.setBounds(56, 326, 126, 14);
		getContentPane().add(lblFecNac);
		
		textField_2 = new JTextField();
		textField_2.setBounds(228, 323, 202, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox cboPais = new JComboBox();
		cboPais.setToolTipText("Afganistán\r\nIslas Gland\r\nAlbania\r\nAlemania\r\nAndorra\r\nAngola\r\nAnguilla\r\nAntártida\r\nAntigua y Barbuda\r\nAntillas Holandesas\r\nArabia Saudí\r\nArgelia\r\nArgentina\r\nArmenia\r\nAruba\r\nAustralia\r\nAustria\r\nAzerbaiyán\r\nBahamas\r\nBahréin\r\nBangladesh\r\nBarbados\r\nBielorrusia\r\nBélgica\r\nBelice\r\nBenin\r\nBermudas\r\nBhután\r\nBolivia\r\nBosnia y Herzegovina\r\nBotsuana\r\nIsla Bouvet\r\nBrasil\r\nBrunéi\r\nBulgaria\r\nBurkina Faso\r\nBurundi\r\nCabo Verde\r\nIslas Caimán\r\nCamboya\r\nCamerún\r\nCanadá\r\nRepública Centroafricana\r\nChad\r\nRepública Checa\r\nChile\r\nChina\r\nChipre\r\nIsla de Navidad\r\nCiudad del Vaticano\r\nIslas Cocos\r\nColombia\r\nComoras\r\nRepública Democrática del Congo\r\nCongo\r\nIslas Cook\r\nCorea del Norte\r\nCorea del Sur\r\nCosta de Marfil\r\nCosta Rica\r\nCroacia\r\nCuba\r\nDinamarca\r\nDominica\r\nRepública Dominicana\r\nEcuador\r\nEgipto\r\nEl Salvador\r\nEmiratos Árabes Unidos\r\nEritrea\r\nEslovaquia\r\nEslovenia\r\nEspaña\r\nIslas ultramarinas de Estados Unidos\r\nEstados Unidos\r\nEstonia\r\nEtiopía\r\nIslas Feroe\r\nFilipinas\r\nFinlandia\r\nFiyi\r\nFrancia\r\nGabón\r\nGambia\r\nGeorgia\r\nIslas Georgias del Sur y Sandwich del Sur\r\nGhana\r\nGibraltar\r\nGranada\r\nGrecia\r\nGroenlandia\r\nGuadalupe\r\nGuam\r\nGuatemala\r\nGuayana Francesa\r\nGuinea\r\nGuinea Ecuatorial\r\nGuinea-Bissau\r\nGuyana\r\nHaití\r\nIslas Heard y McDonald\r\nHonduras\r\nHong Kong\r\nHungría\r\nIndia\r\nIndonesia\r\nIrán\r\nIraq\r\nIrlanda\r\nIslandia\r\nIsrael\r\nItalia\r\nJamaica\r\nJapón\r\nJordania\r\nKazajstán\r\nKenia\r\nKirguistán\r\nKiribati\r\nKuwait\r\nLaos\r\nLesotho\r\nLetonia\r\nLíbano\r\nLiberia\r\nLibia\r\nLiechtenstein\r\nLituania\r\nLuxemburgo\r\nMacao\r\nARY Macedonia\r\nMadagascar\r\nMalasia\r\nMalawi\r\nMaldivas\r\nMalí\r\nMalta\r\nIslas Malvinas\r\nIslas Marianas del Norte\r\nMarruecos\r\nIslas Marshall\r\nMartinica\r\nMauricio\r\nMauritania\r\nMayotte\r\nMéxico\r\nMicronesia\r\nMoldavia\r\nMónaco\r\nMongolia\r\nMontserrat\r\nMozambique\r\nMyanmar\r\nNamibia\r\nNauru\r\nNepal\r\nNicaragua\r\nNíger\r\nNigeria\r\nNiue\r\nIsla Norfolk\r\nNoruega\r\nNueva Caledonia\r\nNueva Zelanda\r\nOmán\r\nPaíses Bajos\r\nPakistán\r\nPalau\r\nPalestina\r\nPanamá\r\nPapúa Nueva Guinea\r\nParaguay\r\nPerú\r\nIslas Pitcairn\r\nPolinesia Francesa\r\nPolonia\r\nPortugal\r\nPuerto Rico\r\nQatar\r\nReino Unido\r\nReunión\r\nRuanda\r\nRumania\r\nRusia\r\nSahara Occidental\r\nIslas Salomón\r\nSamoa\r\nSamoa Americana\r\nSan Cristóbal y Nevis\r\nSan Marino\r\nSan Pedro y Miquelón\r\nSan Vicente y las Granadinas\r\nSanta Helena\r\nSanta Lucía\r\nSanto Tomé y Príncipe\r\nSenegal\r\nSerbia y Montenegro\r\nSeychelles\r\nSierra Leona\r\nSingapur\r\nSiria\r\nSomalia\r\nSri Lanka\r\nSuazilandia\r\nSudáfrica\r\nSudán\r\nSuecia\r\nSuiza\r\nSurinam\r\nSvalbard y Jan Mayen\r\nTailandia\r\nTaiwán\r\nTanzania\r\nTayikistán\r\nTerritorio Británico del Océano Índico\r\nTerritorios Australes Franceses\r\nTimor Oriental\r\nTogo\r\nTokelau\r\nTonga\r\nTrinidad y Tobago\r\nTúnez\r\nIslas Turcas y Caicos\r\nTurkmenistán\r\nTurquía\r\nTuvalu\r\nUcrania\r\nUganda\r\nUruguay\r\nUzbekistán\r\nVanuatu\r\nVenezuela\r\nVietnam\r\nIslas Vírgenes Británicas\r\nIslas Vírgenes de los Estados Unidos\r\nWallis y Futuna\r\nYemen\r\nYibuti\r\nZambia\r\nZimbabue");
		cboPais.setBounds(228, 370, 202, 22);
		getContentPane().add(cboPais);
		
		JLabel lblPais = new JLabel("Pais:");
		lblPais.setBounds(56, 374, 46, 14);
		getContentPane().add(lblPais);
		
		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnGrabarJButton(e);
			}
		});
		btnGrabar.setBounds(179, 443, 89, 23);
		getContentPane().add(btnGrabar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(315, 443, 89, 23);
		getContentPane().add(btnLimpiar);
	}
	
	protected void actionPerformedBtnGrabarJButton(ActionEvent e) {
		
	}
}
