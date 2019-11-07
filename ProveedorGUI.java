import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

public class ProveedorGUI extends JFrame implements ActionListener
{

    
    //private JButton bRegistrarVentas, bRegistrarPedido, bSalir, bGuardar, bGuardarV;
    private JButton bRegistrarPedido, bSalir, bBuscar;
    
    private JTextArea taDatos = new JTextArea(10,23);
    private JPanel panel1, panel2, panelPedido;
    private JTextField tfPedido;
    private JTextField tfDonas, tfConchas, tfBolillos;
    private JTextField tfCuerno, tfMuffin, tfOreja, tfPolvoron;
    
    private BufferedReader archivoIn;
    
    //private ConsultaPP ad = new ConsultaPP();
    
    //private ChofisAD ad = new ChofisAD();
    private GestionPedido ad = new GestionPedido();
 
    public ProveedorGUI()
    {
        super("PROVEEDOR");
        
        taDatos = new JTextArea(10,23);
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        panelPedido = new JPanel();
        
        tfPedido=new JTextField(5);
        tfDonas = new JTextField(5);
        tfConchas = new JTextField(5);
        tfBolillos = new JTextField(5);
        tfCuerno = new JTextField(5);
        tfMuffin = new JTextField(5);
        tfOreja = new JTextField(5);
        tfPolvoron = new JTextField(5);
        
        bSalir = new JButton("Salir");
        bRegistrarPedido = new JButton("Verificar pedido completo");
        bBuscar= new JButton("Buscar");
        
        bSalir.addActionListener(this);
        bRegistrarPedido.addActionListener(this);
        bBuscar.addActionListener(this);
        
        panel1.setLayout(new GridLayout(3,1));
        panelPedido.setLayout(new GridLayout(12,2));
        panel2.setLayout(new FlowLayout());
        
        panel1.add(bRegistrarPedido);
        panel1.add(bSalir);
        
       //panelPedido.add(new JLabel("Registrar el pedido completo"));
       	panelPedido.add(new JLabel("Pedido a verificar"));
        panelPedido.add(tfPedido);
        panelPedido.add(new JLabel(""));
        panelPedido.add(bBuscar);
        panelPedido.add(new JLabel(""));
        panelPedido.add(new JLabel(""));
        
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
       
       
        panel2.add(panel1);
        
        add(panel2);
        setSize(400,400);
        //setVisible(true);
    }
    
    public JPanel getPanel2()
	{
		return panel2;
	}
	
	
	private void desplegar(String localizado, String pedido)
	{
		
			String donasB="",conchasB="",bolillosB="",cuernoB="",muffinB="",orejaB="",polvoronB="", basura="";
			String pedidoBuscado="";
			String respuesta="";
			
		if(localizado.equals("Pedido localizado"))
		{
			//abrir txt para el token de items
        	String items="";
        	StringTokenizer stItems=null;
        	int encontradoB=0;
        	try
        	{
            	archivoIn = new BufferedReader (new FileReader("Items.txt"));
            	while(archivoIn.ready()&& encontradoB==0)
            	{
                	items = archivoIn.readLine()+"\n";
                	stItems = new StringTokenizer(items,"_");
                	pedidoBuscado = stItems.nextToken();
				
					if(pedido.equals(pedidoBuscado))
               		{
                		encontradoB = 1;
                	}
                            	   
            	}
            archivoIn.close();
            System.out.println(items);
	        }
	        catch(IOException ioe)
	        {
	            System.out.println("Error "+ioe);
	        }
        	
        	
        	//basura= stItems.nextToken();
        	donasB = stItems.nextToken();
        	//basura= stItems.nextToken();
        	conchasB = stItems.nextToken();
        	//basura= stItems.nextToken();
        	bolillosB = stItems.nextToken();
        	//basura= stItems.nextToken();
        	cuernoB = stItems.nextToken();
        	//basura= stItems.nextToken();
        	muffinB = stItems.nextToken();
        	//basura= stItems.nextToken();
        	orejaB = stItems.nextToken();
        	//basura= stItems.nextToken();
        	polvoronB = stItems.nextToken();
        	
        	tfDonas.setText(donasB);
        	tfConchas.setText(conchasB);
        	tfBolillos.setText(bolillosB);
        	tfCuerno.setText(cuernoB);
			tfMuffin.setText(muffinB);
			tfOreja.setText(orejaB);
        	tfPolvoron.setText(polvoronB);
        	
        	//respuesta=donasB+"_"+conchasB+"_"+bolillosB+"_"+cuernoB+"_"+muffinB+"_"+orejaB+"_"+polvoronB;
		}
		else
		{
			tfDonas.setText("");
        	tfConchas.setText("");
        	tfBolillos.setText("");
        	tfCuerno.setText("");
			tfMuffin.setText("");
			tfOreja.setText("");
        	tfPolvoron.setText("");
			
		}
	
	}
        
    	private String verificarPedido(String pedido)
	{
		String respuesta="", datos="",str="";
		StringTokenizer st=null;
		String pedidoBuscado="";
		int encontrado = 0;
		String donasB="",conchasB="",bolillosB="",cuernoB="",muffinB="",orejaB="",polvoronB="";
	
		try
        {
            archivoIn = new BufferedReader (new FileReader("Pagos.txt"));
            while(archivoIn.ready()&& encontrado==0)
            {
                datos = archivoIn.readLine()+"\n";
                st = new StringTokenizer(datos,"_");
                pedidoBuscado = st.nextToken();
			
                if(pedido.equals(pedidoBuscado))
                {
                	encontrado = 1;
                	
                }              	   
            }
            archivoIn.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Error PPPPPPPPPPPP "+ioe);
        }
             
        if(encontrado==1)
        {  	
        	respuesta="Pedido localizado";
        }
        else
        {
        	respuesta = "Ese pedido no se ha pagado...";
        } 
        return respuesta;
	}
	
    public void actionPerformed(ActionEvent e)
    {
        String respuesta="";
        String datos ="";
     	   
                
        if(e.getSource() == bRegistrarPedido)
        {
            panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panel1);
        	panel2.add(panelPedido);
			panel2.setVisible(true);
        }
        
        if(e.getSource()== bBuscar)
        {
        	
        	String pedido="";
        	
			pedido = tfPedido.getText();        	
        	
        	respuesta= ad.verificarPedido(pedido);
        	
        	JOptionPane.showMessageDialog(null,respuesta);
        	
        	desplegar(respuesta,pedido);
        }
           
        if(e.getSource() == bSalir)
            System.exit(0);
    }
    
    public static void main(String args[])
    {
        new ProveedorGUI();
    }
}
