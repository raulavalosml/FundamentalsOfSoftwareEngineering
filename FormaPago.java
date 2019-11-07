import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

public class FormaPago extends JFrame implements ActionListener
{
	private JButton  bPagar, bSalir, bNext, bAtras;
	
 	private JComboBox comboPago;
    private String opcionesPago[] = {"TARJETA CREDITO","TARJETA DEBITO","TRANSFERENCIA"};
 	
    private JPanel panel1, panel2, panelTarjeta, panelTrans;
    private JTextField tfnOrden;
    
    private JTextField tfNumTarjeta, tfTitular, tfCodigo, tfVencimiento, tfEmail;
    
    private PrintWriter    archivoOut;
    private BufferedReader archivoIn;
    
    private	int nOrden=0;
    
    public FormaPago() 
    {
    	super("Pan Pepe's");
    	
    	// 1. Crear los objetos de los atributos
    	panel1 = new JPanel();
    	panel2 = new JPanel();
    	panelTarjeta = new JPanel();
    	panelTrans = new JPanel();
    	
    	comboPago = new JComboBox(opcionesPago);
    	
    	bPagar = new JButton("Realizar Pago");
    	bNext = new JButton("Siguiente");
    	bSalir = new JButton("Exit");
    	bAtras = new JButton("Atras");
    	
    	tfnOrden = new JTextField();
    	
    	tfNumTarjeta = new JTextField();
    	tfTitular = new JTextField();
    	tfCodigo = new JTextField();
    	tfVencimiento = new JTextField("dd/mm/aaaa");
    	tfEmail = new JTextField();
    	
    	// Adicionar addActionListener a lo JButtons
    	bSalir.addActionListener(this);
    	bNext.addActionListener(this);
    	bPagar.addActionListener(this);
    	bAtras.addActionListener(this);		
    	
    	// 2. Definir los Layouts de los JPanels
    	panel1.setLayout(new GridLayout(3,2));
    	panelTarjeta.setLayout(new GridLayout(5,2));
    	panelTrans.setLayout(new GridLayout(2,2));
    	panel2.setLayout(new FlowLayout());
    	
    	 // 3. Colocar los objetos de los atributos en los JPanels correspondientes
    	panel1.add(new JLabel("Forma de pago"));
        panel1.add(comboPago);
        panel1.add(new JLabel("Numero de orden:"));
        panel1.add(tfnOrden);
        panel1.add(bNext);
        
        panelTarjeta.add(new JLabel("Numero de Tarjeta:"));
        panelTarjeta.add(tfNumTarjeta);
        panelTarjeta.add(new JLabel("Nombre del Titular:"));
        panelTarjeta.add(tfTitular);
        panelTarjeta.add(new JLabel("Codigo de seguridad (4 digitos):"));
        panelTarjeta.add(tfCodigo);
        panelTarjeta.add(new JLabel("Fecha de vencimiento:"));
        panelTarjeta.add(tfVencimiento);
        panelTarjeta.add(new JLabel("Email:"));
        panelTarjeta.add(tfEmail);
        
        panelTrans.add(new JLabel("Transferencia"));
        
        panel2.add(panel1);
        
         // 4. Adicionar el panel2 al JFrame y hacerlo visible
        setSize(600,300);
        add(panel2);
        
        //setVisible(true);
    }
    
    public JPanel getPanel2()
	{
		return panel2;
	}
    
    private void desplegarPanel()
    {
    	String tipo  = (String)comboPago.getSelectedItem();
    	
    	if(tipo.equals("TARJETA CREDITO"))
    	{
    		panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panelTarjeta);
        	panel2.add(bAtras);
        	panel2.add(bPagar);
			panel2.setVisible(true);
    	}
    	if(tipo.equals("TARJETA DEBITO"))
    	{
    		panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panelTarjeta);
        	panel2.add(bAtras);
        	panel2.add(bPagar);
			panel2.setVisible(true); 
			
    	}
    	if(tipo.equals("TRANSFERENCIA"))
    	{
    		panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panelTrans);
        	panel2.add(bAtras);
			panel2.setVisible(true); 
				
			//int numero = Math.floor(Math.random()*(9-1+1)+1);
    	}
    	
    }
    
    private String obtenerDatos()
    {
        String datos;
        
        String nTarjeta		 = tfNumTarjeta.getText();
        String titular 		 = tfTitular.getText();
        String codigo  		 = tfCodigo.getText();
        String vencimiento   = tfVencimiento.getText();
        String email  	 	 = tfEmail.getText();
        
        if(nTarjeta.equals("") || titular.isEmpty() || codigo.equals("") || vencimiento.isEmpty() || email.isEmpty())
            datos = "VACIO";
        else
        {
            try
            {
                int n = Integer.parseInt(nTarjeta);
                int e = Integer.parseInt(codigo);
                datos = nTarjeta+"_"+titular+"_"+codigo+"_"+vencimiento+"_"+email;
            }
            catch(NumberFormatException nfe)
            {
                datos = "NO_NUMERICO";
            }
        }
        
        return datos;
    }
    
    private void guardarPago(String datos, int n)
    {
    	String str, nPedido="", estado="", fecha="",respuesta="";
		StringTokenizer st;
		
		nPedido = Integer.toString(n);
		
    	try
        {
            archivoOut = new PrintWriter(new FileWriter("Pagos.txt",true));
            
            System.out.println(datos);
            
            archivoOut.println(n+"_"+datos);
              
            archivoOut.close();
            
            System.out.println("Datos almacenados en el archivo Pagos...");
        }
        catch(IOException ioe)
        {
            System.out.println("Error: "+ioe);
        }

    }
    
    private String checarOrden(int n)
    {
    	String str, nPedido="", estado="", fecha="",respuesta="";
		StringTokenizer st;
		int numPedido=0;
		
		try
		{
			//Abrir el archivo
			archivoIn = new BufferedReader(new FileReader("Pedidos.txt"));

			while(archivoIn.ready())
			{
				str = archivoIn.readLine();
				st = new StringTokenizer(str,"_");
				nPedido = st.nextToken();
				estado = st.nextToken();
				fecha = st.nextToken();
				
				System.out.println(nPedido);
				
				numPedido=Integer.parseInt(nPedido);
				
				if(numPedido==n)
				{
					respuesta = "FOUND";
				}
				else
				{
					respuesta = "NOT-FOUND";
				}
			}
			//Cerra el archivo
			archivoIn.close();
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error: "+ e);
		}
		catch(IOException io)
		{
			System.out.println("Error:"+io);
		}
		
		return respuesta;
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	String datos="",respuesta="";
    	
    	if(e.getSource()==bNext)
    	{
    		nOrden=Integer.parseInt(tfnOrden.getText());
    		
    		datos=checarOrden(nOrden);
    		
    		if(datos.equals("NOT-FOUND"))
    		{
    			respuesta="El pedido no ha sido encontrado";
    			JOptionPane.showMessageDialog(null, respuesta);
    		}	
    		else
    		{
    			desplegarPanel();
    		}
    	}
    	
    	if(e.getSource()==bAtras)
    	{
    		panel2.setVisible(false);
			panel2.removeAll();
        	panel2.add(panel1);
			panel2.setVisible(true); 	
    	}
    	
    	if(e.getSource()==bPagar)
    	{
    		datos=obtenerDatos();
    		// 2. Checar datos: datos no vacios y saldo numerico, y realizar la captura de datos
            if(datos.equals("VACIO"))
            {
                respuesta = "Algun campo esta vacio...";
                JOptionPane.showMessageDialog(null, respuesta);
            }
            else
                if(datos.equals("NO_NUMERICO"))
                {
                	respuesta = "Saldo, numero de Tarjeta, Codigo de seguridad deben ser numericos...";
                    JOptionPane.showMessageDialog(null, respuesta);
                } 
                else
                {
                	JOptionPane.showMessageDialog(null, "Pago realizado exitosamente, se le enviara un correo con los datos del mismo");
                	guardarPago(datos,nOrden);
                }
    	}
    	
        if(e.getSource() == bSalir)
        {
        	System.exit(0);
        }
    }
    
    public static void main(String args[])
    {
        new FormaPago();
    }
}