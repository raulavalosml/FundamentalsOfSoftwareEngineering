import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GerenteGUI extends JFrame implements ActionListener
{    
    private JButton bPedido, bPagar, bConsultar, bSalir, bGuardar, bAtras;
    
    private JTextArea taDatos = new JTextArea(10,23);
    private JPanel panel1, panel2, panelPedido, panelPagar, panelConsultar;
   
    private Consultar ad = new Consultar();
    private FormaPago pago = new FormaPago();
    private Pedido pedido = new Pedido();
    
    public GerenteGUI()
    {
        super("Gerente");
        
        taDatos = new JTextArea(10,50);
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        panelPedido = new JPanel();
        panelPagar = new JPanel();
        panelConsultar = new JPanel();
        
        bGuardar = new JButton("Guardar pedido");
        bSalir = new JButton("Salir");
        bPedido = new JButton("Realizar Pedido");
        bPagar = new JButton("Pagar");
        bConsultar = new JButton("Consultar");
        bAtras = new JButton("Regresar");
        
        bSalir.addActionListener(this);
        bPedido.addActionListener(this);
        bPagar.addActionListener(this);
        bConsultar.addActionListener(this);
        
        bAtras.addActionListener(this);
        
        panel1.setLayout(new GridLayout(4,1));
        panelPedido.setLayout(new GridLayout(4,2));
        panel2.setLayout(new GridLayout(4,1));
        panel2.setLayout(new FlowLayout());
        
        //panelConsultar.setLayout(new FlowLayout());

        
        panel1.add(bPedido);
        panel1.add(bPagar);
        panel1.add(bConsultar);
        panel1.add(bSalir);
       
        panelConsultar.add(new JScrollPane(taDatos));
        
        panelPagar=pago.getPanel2();
        
        panel2.add(panel1);
        
        add(panel2);
        setSize(600,300);
        //setVisible(true);
    }
    
    public JPanel getPanel2()
	{
		return panel2;
	}
        
    
    public void actionPerformed(ActionEvent e)
    {
        String respuesta="";
        String datos ="";
     	   
        if(e.getSource() == bPedido)
        {
        	panelPedido=pedido.getPanel2();
        	
        	panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panelPedido);
        	panel2.add(bAtras);
			panel2.setVisible(true); 
        	 
        }
        
        
        if(e.getSource() == bConsultar)
        {
        	respuesta=ad.consultar();
        	taDatos.setText(respuesta);
        	
            panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panelConsultar);
        	panel2.add(bAtras);
			panel2.setVisible(true);
        }
        
        if(e.getSource() == bPagar)
        {
            panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panelPagar);
        	panel2.add(bAtras);
			panel2.setVisible(true);
        }
        
        if(e.getSource()==bGuardar)
        {
        	panelPedido=pedido.getPanel2();
        	
        	JOptionPane.showMessageDialog(null,"Pedido realizado");
        	
        	panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panelPedido);
        	panel2.add(bAtras);
			panel2.setVisible(true);	
        }
        
        if(e.getSource()==bAtras)
        {
        	panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panel1);
			panel2.setVisible(true);
        }
        
        if(e.getSource() == bSalir)
            System.exit(0);
    }
    
    public static void main(String args[])
    {
        new GerenteGUI();
    }
}
