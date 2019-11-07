import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChofisGUI extends JFrame implements ActionListener
{

    
    private JButton bRegistrarVentas, bRegistrarPedido, bSalir, bGuardar, bGuardarV;
    
    private JTextArea taDatos = new JTextArea(10,23);
    private JPanel panel1, panel2, panelPedido, panelVentas;
    private JTextField tfDonas, tfConchas, tfBolillos, tfDonasV, tfConchasV, tfBolillosV, tfPedido, tfPedidoV;
    private JTextField tfCuerno, tfMuffin, tfOreja, tfPolvoron;
    private JTextField tfCuernoV, tfMuffinV, tfOrejaV, tfPolvoronV;
    
    private Ventas ad = new Ventas();
 
    public ChofisGUI()
    {
        super("Repartidor");
        
        taDatos = new JTextArea(10,23);
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        panelPedido = new JPanel();
        panelVentas = new JPanel();
        
        tfDonas = new JTextField(5);
        tfConchas = new JTextField(5);
        tfBolillos = new JTextField(5);
        tfPedido = new JTextField(5);
        
        tfDonasV = new JTextField(5);
        tfConchasV = new JTextField(5);
        tfBolillosV = new JTextField(5);
        tfPedidoV = new JTextField(5);
        tfCuerno = new JTextField(5);
        tfMuffin = new JTextField(5);
        tfOreja = new JTextField(5);
        tfPolvoron = new JTextField(5);
        tfCuernoV = new JTextField(5);
        tfMuffinV = new JTextField(5);
        tfOrejaV = new JTextField(5);
        tfPolvoronV = new JTextField(5);
        
        bGuardar = new JButton("Verificar");
        bGuardarV = new JButton("Guardar Ventas");
        bSalir = new JButton("Salir");
        bRegistrarPedido = new JButton("Verificar pedido completo");
        bRegistrarVentas = new JButton("Registrar Ventas");
        
        bSalir.addActionListener(this);
        bRegistrarPedido.addActionListener(this);
        bRegistrarVentas.addActionListener(this);
        bGuardar.addActionListener(this);
        bGuardarV.addActionListener(this);
        
        panel1.setLayout(new GridLayout(3,1));
        panelPedido.setLayout(new GridLayout(10,2));
        panelVentas.setLayout(new GridLayout(10,2));
        panel2.setLayout(new FlowLayout());
        
        panel1.add(bRegistrarPedido);
        panel1.add(bRegistrarVentas);
        panel1.add(bSalir);
        
        panelPedido.add(new JLabel("Registrar el pedido completo"));
        panelPedido.add(new JLabel(""));
        panelPedido.add(new JLabel("Pedido a Verificar: "));
        panelPedido.add(tfPedido);
        panelPedido.add(new JLabel("Donas: "));
        panelPedido.add(tfDonas);
        panelPedido.add(new JLabel("Conchas: "));
        panelPedido.add(tfConchas);
        panelPedido.add(new JLabel("Bolillo: "));
        panelPedido.add(tfBolillos);
        panelPedido.add(new JLabel("Cuerno: "));
        panelPedido.add(tfCuerno);
        panelPedido.add(new JLabel("Muffin: "));
        panelPedido.add(tfMuffin);
        panelPedido.add(new JLabel("Orejas: "));
        panelPedido.add(tfOreja);
        panelPedido.add(new JLabel("Polvorones: "));
        panelPedido.add(tfPolvoron);
        panelPedido.add(new JLabel(""));
        panelPedido.add(bGuardar);
        
        panelVentas.add(new JLabel("Registrar las ventas del día"));
        panelVentas.add(new JLabel(""));
        panelVentas.add(new JLabel("Pedido a Registrar V: "));
        panelVentas.add(tfPedidoV);
        panelVentas.add(new JLabel("Donas: "));
        panelVentas.add(tfDonasV);
        panelVentas.add(new JLabel("Conchas: "));
        panelVentas.add(tfConchasV);
        panelVentas.add(new JLabel("Bolillo: "));
        panelVentas.add(tfBolillosV);
        panelVentas.add(new JLabel("Cuerno: "));
        panelVentas.add(tfCuernoV);
        panelVentas.add(new JLabel("Muffin: "));
        panelVentas.add(tfMuffinV);
        panelVentas.add(new JLabel("Orejas: "));
        panelVentas.add(tfOrejaV);
        panelVentas.add(new JLabel("Polvorones: "));
        panelVentas.add(tfPolvoronV);
        panelVentas.add(new JLabel(""));
        panelVentas.add(bGuardarV);
        
        
        panel2.add(panel1);
        
        add(panel2);
        setSize(400,400);
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
     	   
        if(e.getSource() == bRegistrarVentas)
        {        	 
        	panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panel1);
        	panel2.add(panelVentas);
			panel2.setVisible(true); 
        	 
        }
        
        
        if(e.getSource() == bRegistrarPedido)
        {
            panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panel1);
        	panel2.add(panelPedido);
			panel2.setVisible(true);
        }
        
        if(e.getSource()== bGuardar)
        {
        	String donas="", conchas="", bolillos="", pedido="";
        	String muffin="", polvoron="", oreja="", cuerno="";

			pedido = tfPedido.getText();        	
        	donas=tfDonas.getText();
        	conchas=tfConchas.getText();
        	bolillos=tfBolillos.getText();
        	cuerno=tfCuerno.getText();
			muffin=tfMuffin.getText();
			oreja=tfOreja.getText();
        	polvoron=tfPolvoron.getText();
        	
        	respuesta=ad.verificarPedido(pedido,donas,conchas,bolillos,cuerno,muffin,oreja,polvoron);
        	
        	JOptionPane.showMessageDialog(null,respuesta);
        	System.out.println(respuesta);
        	
        	//panel2.setVisible(false);
			//panel2.removeAll();
        	//panel2.add(panel1);
			//panel2.setVisible(true);
        }
        
        if(e.getSource()== bGuardarV)
        {
        	String donas="", conchas="", bolillos="", pedido="";
        	String muffin="", polvoron="", oreja="", cuerno="";

			pedido = tfPedidoV.getText();
			//verificar si esta pagado el pedido        	
        	donas=tfDonasV.getText();
        	conchas=tfConchasV.getText();
        	bolillos=tfBolillosV.getText();
			cuerno=tfCuernoV.getText();
			muffin=tfMuffinV.getText();
			oreja=tfOrejaV.getText();
        	polvoron=tfPolvoronV.getText();
        	
        	respuesta=ad.registrarVentas(pedido,donas,conchas,bolillos,cuerno,muffin,oreja,polvoron);
        	
        	JOptionPane.showMessageDialog(null,respuesta);
        	System.out.println(respuesta);
        	
        	//panel2.setVisible(false);
			//panel2.removeAll();
        	//panel2.add(panel1);
			//panel2.setVisible(true);
        }
        /*
        if(e.getSource() == bPagar)
        {
            panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panel1);
        	panel2.add(panelPagar);
			panel2.setVisible(true);
        }
        */
        
        if(e.getSource() == bSalir)
            System.exit(0);
    }
    
    public static void main(String args[])
    {
        new ChofisGUI();
    }
}
