package guis;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import arreglos.ArregloAlumno;
import clases.Alumno;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Alumnos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtDni;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtEdad;
	private JTextField txtCelular;
	private JButton btnAdicionar;
	private JButton btnConsultar;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JButton btnEliminar;
	private JComboBox <String>cboEstado;
	private JScrollPane scrollPane;
	private DefaultTableModel modelo;

	public static void main(String[] args) {
		try {
			Alumnos dialog = new Alumnos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	//
	
	ArregloAlumno aa=new ArregloAlumno();
	private JButton btnBuscar;
	private JTable tblAlumnos;

	
	public Alumnos() {
		setBounds(100, 100, 881, 567);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo : ");
		
		JLabel lblNewLabel_1 = new JLabel("DNI : ");
		
		JLabel lblNewLabel_2 = new JLabel("Nombres : ");
		
		JLabel lblNewLabel_3 = new JLabel("Apellidos : ");
		
		JLabel lblNewLabel_4 = new JLabel("Edad : ");
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		
		txtNombres = new JTextField();
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Celular : ");
		
		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int codigo = leerNumeroAlumno();
					Alumno a = aa.buscar(codigo);

					if(btnModificar.isEnabled() == false){
						if (a != null) {
							txtNombres.setText(a.getNombre());
							txtApellidos.setText(a.getApellido());
							txtDni.setText(""+a.getDni());
							txtEdad.setText(String.valueOf(a.getEdad()));
							txtCelular.setText(String.valueOf(a.getCelular()));
							cboEstado.setSelectedIndex(a.getEstado());
							habilitarEntradas(true);
							txtDni.setEditable(false);
							habilitarBotones(true);
							btnModificar.setEnabled(false);
							habilitarBusqueda(false);
						}
						else
							error("El c�digo " + codigo + " no existe", txtCodigo);
					}
					else{
						if (a != null) {
							txtNombres.setText(a.getNombre());
							txtApellidos.setText(a.getApellido());
							txtDni.setText(""+a.getDni());
							txtEdad.setText(String.valueOf(a.getEdad()));
							txtCelular.setText(String.valueOf(a.getCelular()));
							cboEstado.setSelectedIndex(a.getEstado());
							habilitarEntradas(false);
							habilitarBotones(true);
							habilitarBusqueda(false);
						}
						else
							error("El c�digo " + codigo + " no existe", txtCodigo);
						
					}
				}
				catch (Exception el) {
					error("Ingrese un C�DIGO", txtCodigo);
				}
				
				
				
				
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("Estado : ");
		
		JLabel lblNewLabel_7 = new JLabel("");
		
		JComboBox cboEstado = new JComboBox();
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"Registrado", "Maticulado", "Retirado"}));
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int codAlumno = leerNumeroAlumno();
				String nombres = leerNombres();
				if(nombres.length() > 0){
					String apellidos = leerApellidos();
					if(apellidos.length() > 0){
						try{
						String dni = leerDNI();
							try{
								int edad = leerEdad();
								try{
									int celular = leerCelular();
									int estado = leerPosEstado();
									if (btnAdicionar.isEnabled() == false) {
										Alumno a = aa.buscarDNI(dni);
										if(a == null){
											Alumno nuevo = new Alumno(codAlumno, nombres, apellidos, dni, edad, celular, estado);
											aa.adicionar(nuevo);
											btnAdicionar.setEnabled(true);
										}
										else
											error("El DNI " + dni + " ya existe", txtDni);
											
									}
									if (btnModificar.isEnabled() == false) {
										Alumno a = aa.buscar(codAlumno);
										a.setNombre(nombres);
										a.setApellido(apellidos);
										a.setDni(dni);
										a.setEdad(edad);
										a.setCelular(celular);
										a.setEstado(estado);
										aa.actualizarArchivo();
										btnModificar.setEnabled(true);
									}
									listar();
									btnAceptar.setEnabled(true);
								}
								catch(Exception el){
									error("Ingrese CELULAR del Alumno", txtCelular);
								}
							}
							catch(Exception el){
								error("Ingrese EDAD del Alumno", txtEdad);
							}
						}
						catch(Exception el){
							error("Ingrese DNI del Alumno", txtDni);
						}
					}
					else
						error("Ingrese APELLIDOS del Alumno", txtApellidos);
				}
				else
					error("Ingrese NOMBRES del Alumno", txtNombres);
				
				
			}
		});
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdicionar.setEnabled(false);
				btnModificar.setEnabled(true);
				btnAceptar.setEnabled(true);
				txtCodigo.setText("" + aa.numeroCorrelativo());
				txtDni.setEditable(true);
				limpieza();
				habilitarEntradas(true);
				txtDni.requestFocus();
				
			}
		});
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarEntradas(false);
				habilitarBotones(true);
				habilitarBusqueda(true);
				btnConsultar.setEnabled(false);
				limpieza();
				txtCodigo.setText("");
				txtCodigo.requestFocus();
				
				
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				habilitarBotones(true);
				btnModificar.setEnabled(false);
				try{
					if (aa.tama�o() == 0) {
						btnAceptar.setEnabled(false);
						habilitarEntradas(false);
						mensaje("No existen alumnos");	
					}
					else{
						habilitarBotones(true);
						habilitarEntradas(false);
						habilitarBusqueda(true);
						btnModificar.setEnabled(false);
						txtCodigo.setEditable(false);
						editarFila();
						btnAceptar.setEnabled(true);
					}
				}
				catch(Exception el){
					txtCodigo.requestFocus();
				}
				
				
				
				
				
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				btnAdicionar.setEnabled(true);
				btnModificar.setEnabled(true);
				btnAceptar.setEnabled(false);
				if (aa.tama�o() == 0)
					mensaje("No existen Alumnos");
				else {
					editarFila();
					habilitarEntradas(false);
					int numeroAlumno = leerNumeroAlumno();
					Alumno x = aa.buscar(numeroAlumno);
					if (x.getEstado() != 1) {
						int ok = confirmar("� Desea eliminar el registro ?");
						if (ok == 0) {
							aa.eliminar(aa.buscar(leerNumeroAlumno()));
							listar();
							editarFila();
						}
					}
					else
						mensaje("No se puede eliminar Alumno " + numeroAlumno + " porque est� Matriculado");
				}
				
				
				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(lblNewLabel_1))
									.addGap(26)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(lblNewLabel_4)
									.addGap(27)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblNewLabel_3)
										.addGap(18)
										.addComponent(txtApellidos))
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblNewLabel_2)
										.addGap(18)
										.addComponent(txtNombres, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_6)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel_7)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_5)))
							.addGap(16)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cboEstado, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAceptar, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
								.addComponent(txtCelular, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(135)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnEliminar, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
								.addComponent(btnModificar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnConsultar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAdicionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4)
								.addComponent(txtEdad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7)
								.addComponent(btnBuscar)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_6)
								.addComponent(cboEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAdicionar))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5)
								.addComponent(btnConsultar))))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(13)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(18)
									.addComponent(lblNewLabel_3))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtNombres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnAceptar))
									.addGap(18)
									.addComponent(txtApellidos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(11)
							.addComponent(btnModificar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEliminar)))
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblAlumnos = new JTable();
		scrollPane_1.setViewportView(tblAlumnos);
		contentPanel.setLayout(gl_contentPanel);
		
		
		
		
		
		modelo=new DefaultTableModel();
		modelo.addColumn("C�DIGO");
		modelo.addColumn("NOMBRES");
		modelo.addColumn("APELLIDOS");
		modelo.addColumn("DNI");
		modelo.addColumn("EDAD");
		modelo.addColumn("CELULAR");
		modelo.addColumn("ESTADO");
		
		
		
		
		
		
		
		
		
	}
	
	void habilitarBotones(boolean sino) {
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
		btnConsultar.setEnabled(sino);
	}
		
	

	
	
	// metods sin paametos
	void ajustarAnchoColumnas(){
		TableColumnModel tcm = tblAlumnos.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(15));  
		tcm.getColumn(1).setPreferredWidth(anchoColumna(25)); 
		tcm.getColumn(2).setPreferredWidth(anchoColumna(25)); 
		tcm.getColumn(3).setPreferredWidth(anchoColumna(20));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(10)); 
		tcm.getColumn(5).setPreferredWidth(anchoColumna(18)); 
		tcm.getColumn(6).setPreferredWidth(anchoColumna(20)); 
	}
	
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblAlumnos.getSelectedRow();
		if (modelo.getRowCount() == aa.tama�o() - 1)
			posFila = aa.tama�o() - 1;
		if (posFila == aa.tama�o())
			posFila --;
		modelo.setRowCount(0);
		Alumno x;
		for (int i=0; i<aa.tama�o(); i++) {
			x = aa.obtener(i);
			Object[] fila = { x.getCodAlumno(),
					x.getNombre(),
					x.getApellido(),
					x.getDni(),
					x.getEdad(),
					x.getCelular(),
					enTextoEstado(x.getEstado())};
			modelo.addRow(fila);
		}
		if (aa.tama�o() > 0)
			tblAlumnos.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	
	
	void editarFila() {
		if (aa.tama�o() == 0)
			limpieza();
		else {
			Alumno x = aa.obtener(tblAlumnos.getSelectedRow());
			txtCodigo.setText("" + x.getCodAlumno());
			txtNombres.setText("" + x.getNombre());
			txtApellidos.setText("" + x.getApellido());
			txtDni.setText("" + x.getDni());
			txtDni.setEditable(false);
			txtEdad.setText("" + x.getEdad());
			txtCelular.setText("" + x.getCelular());
			cboEstado.setSelectedIndex(x.getEstado());
		}
	}
	
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Informaci�n", 0);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	
	void limpieza() {
		txtCodigo.setText("" + aa.numeroCorrelativo());
		cboEstado.setSelectedIndex(0);
		txtNombres.setText("");
		txtApellidos.setText("");
		txtDni.setText("");
		txtEdad.setText("");
		txtCelular.setText("");
	}
	
	void habilitarBusqueda(boolean sino){
		btnBuscar.setEnabled(sino);
		txtCodigo.setEditable(sino);
	}
	
	void habilitarEntradas(boolean sino) {
		btnAceptar.setEnabled(sino);
	}
	
	int leerNumeroAlumno() {
		return Integer.parseInt(txtCodigo.getText().trim());
	}
	
	String leerNombres() {
		return txtNombres.getText().trim();
	}
	String leerApellidos() {
		return txtApellidos.getText().trim();
	}
	
	String leerDNI() {
		return txtDni.getText().trim();
	}
	
	int leerEdad() {
		return Integer.parseInt(txtEdad.getText().trim());
	}
	
	int leerCelular() {
		return Integer.parseInt(txtCelular.getText().trim());
	}
	
	int leerPosEstado() {
		return cboEstado.getSelectedIndex();
	}
	
	
	//M�tds que retrnan valor con parametro
	
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	
	int anchoColumna(int porcentaje){
		return porcentaje* scrollPane.getWidth()/100;
	}
	String enTextoEstado(int i) {
		return cboEstado.getItemAt(i);
	}
	
	
	
	
	
	
	
	
	
}
