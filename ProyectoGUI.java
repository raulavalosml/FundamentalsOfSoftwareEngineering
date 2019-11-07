import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.*;
//import javax.swing.Panel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProyectoGUI extends JFrame implements ActionListener
{
	private JPanel panel;
//	private Panel panel2;
	private JPanel panelUsuario;
	private JTextField tfUsuario, tfContra;
	private JButton bEntrar, bSalir;
	
	private GerenteGUI gerente = new GerenteGUI();
	private ChofisGUI chofis = new ChofisGUI();
	private ProveedorGUI prove = new ProveedorGUI();
	
	public ProyectoGUI() throws Exception
	{
		super("PAN PEPE");
		
		//1. Crear Objetos del atributo

		panel = new JPanel();
		panelUsuario = new JPanel();
		
		tfUsuario = new JTextField(10);
		tfContra = new JTextField(10);
		
		bEntrar = new JButton("Ingresar");
		bSalir = new JButton("Salir");
		
		bEntrar.addActionListener(this);
		bSalir.addActionListener(this);
		
		panelUsuario.setLayout(new GridLayout(3,2));
		panelUsuario.add(new JLabel("Usuario:"));
		panelUsuario.add(tfUsuario);
		panelUsuario.add(new JLabel("Contraseña:"));
		panelUsuario.add(tfContra);
		panelUsuario.add(bEntrar);
		panelUsuario.add(bSalir);
		
		panel.add(panelUsuario);
		
		
		//1.2 Colocar el metodo
			setSize(600,350);
			setVisible(true);
			add(panel);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void desplegarG()
	{
		panel.setVisible(false);
		panel = gerente.getPanel2();
		panel.setVisible(true);
		add(panel);
		setVisible(true);
	}
	
	public void desplegarC()
	{
		panel.setVisible(false);
		panel = chofis.getPanel2();
		panel.setVisible(true);
		add(panel);
		setVisible(true);
	}
	
	public void desplegarP()
	{
		panel.setVisible(false);
		panel = prove.getPanel2();
		panel.setVisible(true);
		add(panel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == bSalir)
		{
			System.exit(0);
		}
		
		if(e.getSource() == bEntrar)
		{
			String strUsuario="";
			
			strUsuario = tfUsuario.getText();
			
			if(strUsuario.equals("GERENTE"))
			{
				desplegarG();
			}
			
			if(strUsuario.equals("CHOFIS"))
			{
				desplegarC();
			}
			
			if(strUsuario.equals("PROVEEDOR"))
			{
				desplegarP();
			}
		}
		
		
	}
	
	static public void main (String args[]) throws Exception
	{
		new ProyectoGUI();
	}
}