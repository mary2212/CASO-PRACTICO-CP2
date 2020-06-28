package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidad.Doctor;
import model.DoctorModel;
import util.Validaciones;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmCrudDoctor extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtFechaNac;
	private JTextField txtSueldo;
	private JTextField txtEmail;
	private JTable table;

	int idSeleccionado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCrudDoctor frame = new FrmCrudDoctor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCrudDoctor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DATOS DE DOCTOR");
		lblNewLabel.setBounds(211, 29, 103, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(47, 71, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(47, 106, 46, 14);
		contentPane.add(lblDni);
		
		JLabel lblFechaDeNacimiennto = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiennto.setBounds(47, 142, 114, 14);
		contentPane.add(lblFechaDeNacimiennto);
		
		JLabel lblNewLabel_1 = new JLabel("Sueldo:");
		lblNewLabel_1.setBounds(47, 181, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(47, 219, 46, 14);
		contentPane.add(lblEmail);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(176, 68, 164, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(176, 103, 103, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setBounds(176, 139, 103, 20);
		contentPane.add(txtFechaNac);
		txtFechaNac.setColumns(10);
		
		txtSueldo = new JTextField();
		txtSueldo.setBounds(176, 178, 86, 20);
		contentPane.add(txtSueldo);
		txtSueldo.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(176, 216, 164, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 272, 484, 194);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				int fila = table.getSelectedRow();

				idSeleccionado = Integer.parseInt(table.getValueAt(fila, 0).toString());
				String nom= table.getValueAt(fila, 1).toString();
				String dni= table.getValueAt(fila, 2).toString();
				String fec= table.getValueAt(fila, 3).toString();
				String sue= table.getValueAt(fila, 4).toString();
				String ema= table.getValueAt(fila, 5).toString();

				txtNombre.setText(nom);
				txtDni.setText(dni);
				txtFechaNac.setText(fec);
				txtSueldo.setText(sue);
				txtEmail.setText(ema);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID DOCTOR", "NOMBRE", "DNI", "FECHA DE NACIMIENTO", "SUELDO", "EMAIL"
			}
		));
		scrollPane.setViewportView(table);
		
		listaDoctor();
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String nom=txtNombre.getText().trim();
				String dni=txtDni.getText().trim();
				String fec=txtFechaNac.getText().trim();
				String sue=txtSueldo.getText().trim();
				String ema=txtEmail.getText().trim();		
				
				if(nom.matches(Validaciones.NOMBRE)== false) 
				{
					mensaje("El nombre debe ser entre 3 y 30 cacateres");
				}
				else if(dni.matches(Validaciones.DNI)== false) 
				{
					mensaje("El DNI debe ser de 8 digitos");
				}
				else if(fec.matches(Validaciones.FECHA)== false) 
				{
					mensaje("El año es yyyy/MM/dd");
				}
				else if(sue.matches(Validaciones.SUELDO)== false)
				{
					mensaje("El sueldo debe llevar decimal 0.0");
				}
				
				else if(ema.matches(Validaciones.EMAIL)== false) 
				{
					mensaje("El correo debe ser xxx.xxx.xxx@xxx.xx");
				}
				else 
				{
					Doctor obj=new Doctor();
					obj.setIdoctor(idSeleccionado);
					obj.setNombre(nom);
					obj.setDni(dni);
					obj.setFechaNacimiento(fec);
					obj.setSueldo(sue);
					obj.setEmail(ema);
					
					DoctorModel m=new DoctorModel();
					int salida=m.insertaDoctor(obj);
					
					if(salida > 0) 
					{
						mensaje("Se registro correctamente");
						listaDoctor();
						limpiarCajasTexto();
					}
					else 
					{
						mensaje("Error en el registro");
					}
				}
			}
		});
		btnRegistrar.setBounds(400, 81, 114, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(idSeleccionado==-1) 
				{
					mensaje("Se debe seleccionar una fila");
				}
				else 
				{
					String nom=txtNombre.getText().trim();
					String dni=txtDni.getText().trim();
					String fec=txtFechaNac.getText().trim();
					String sue=txtSueldo.getText().trim();
					String ema=txtEmail.getText().trim();		
					
					if(nom.matches(Validaciones.NOMBRE)== false) 
					{
						mensaje("El nombre debe ser entre 3 y 30 cacateres");
					}
					else if(dni.matches(Validaciones.DNI)== false) 
					{
						mensaje("El DNI debe ser de 8 digitos");
					}
					else if(fec.matches(Validaciones.FECHA)== false) 
					{
						mensaje("El año es yyyy/MM/dd");
					}
					else if(sue.matches(Validaciones.SUELDO)== false)
					{
						mensaje("El sueldo debe llevar decimal 0.0");
					}
					
					else if(ema.matches(Validaciones.EMAIL)== false) 
					{
						mensaje("El correo debe ser xxx.xxx.xxx@xxx.xx ");
					}
					else 
					{
						Doctor obj=new Doctor();
						obj.setIdoctor(idSeleccionado);
						obj.setNombre(nom);
						obj.setDni(dni);
						obj.setFechaNacimiento(fec);
						obj.setSueldo(sue);
						obj.setEmail(ema);
						
						DoctorModel m=new DoctorModel();
						int salida=m.actualizaDoctor(obj);
						
						if(salida > 0) 
						{
							mensaje("Se actualizo correctamente");
							listaDoctor();
							limpiarCajasTexto();
							idSeleccionado=-1;
						}
						else 
						{
							mensaje("Error al actualizar");
						}
					}
				}

			}
		});
		btnActualizar.setBounds(400, 138, 114, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(idSeleccionado== -1) 
				{
					mensaje("Se debe seleccionar una fila");
				}
				else 
				{
					DoctorModel m=new DoctorModel();
					int s=m.eliminaDoctor(idSeleccionado);
					
					if (s > 0) 
					{
						mensaje("Se elimino correctamente");
						listaDoctor();
						limpiarCajasTexto();
						idSeleccionado= -1;
					} else 
					{
						mensaje("Error al eliminar");
					}
				}
			}
		});
		btnEliminar.setBounds(400, 193, 114, 23);
		contentPane.add(btnEliminar);
		

	}
	
	
	
	void listaDoctor()
	{
		DoctorModel m=new DoctorModel();
		List<Doctor> data=m.listaDoctor();
		
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		for(Doctor aux: data) 
		{
			Object[]fila= 
				{
						aux.getIdoctor(),
						aux.getNombre(),
						aux.getDni(),
						aux.fechaNacimiento,
						aux.getSueldo(),
						aux.getEmail(),
				};
			dtm.addRow(fila);
		}
	}

	void mensaje(String m)
	{
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() 
	{
		txtNombre.setText("");
		txtDni.setText("");
		txtFechaNac.setText("");
		txtSueldo.setText("");
		txtEmail.setText("");
		txtNombre.requestFocus();
	}
}
