import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class Pedido extends JFrame implements ActionListener
{
	private JPanel panel1, panel2;
	private JTextField tfDonas, tfConchas, tfBolillos, tfCuernos, tfMuffins, tfOrejas, tfPolvorones;
	private JButton bGuardar;
	
	private PrintWriter  archivoOut;
    private BufferedReader archivoIn;
	
    public Pedido() 
    {
    	panel1 = new JPanel();
        panel2 = new JPanel();
    	
    	tfDonas = new JTextField(5);
        tfConchas = new JTextField(5);
        tfBolillos = new JTextField(5);
        tfCuernos = new JTextField(5);
        tfMuffins = new JTextField(5);
        tfOrejas = new JTextField(5);
        tfPolvorones = new JTextField(5);
        
        bGuardar = new JButton("Guardar");
        
        bGuardar.addActionListener(this);
        
        panel1.setLayout(new GridLayout(10,2));
        panel2.setLayout(new FlowLayout());
        
        panel1.add(new JLabel("Donas: "));
        panel1.add(tfDonas);
        panel1.add(new JLabel("Conchas: "));
        panel1.add(tfConchas);
        panel1.add(new JLabel("Bolillo: "));
        panel1.add(tfBolillos);
        panel1.add(new JLabel("Cuerno: "));
        panel1.add(tfCuernos);
        panel1.add(new JLabel("Muffin: "));
        panel1.add(tfMuffins);
        panel1.add(new JLabel("Orejas: "));
        panel1.add(tfOrejas);
        panel1.add(new JLabel("Polvorones: "));
        panel1.add(tfPolvorones);
        panel1.add(new JLabel(""));
        panel1.add(bGuardar);
        
        panel2.add(panel1);
        
        add(panel2);
        setSize(600,300);
        //setVisible(true);
    }
    
    public JPanel getPanel2()
	{
		return panel2;
	}
        
    private String obtenerOrden()
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

    	return nPedido;
    }   
    	 
    private void guardarPedido(String datos, String total)
    {
    	String nOrden="";
    	int nPedido=0;
    	
    	nOrden=obtenerOrden();
    	nPedido=Integer.parseInt(nOrden);
    	nPedido=nPedido+1;
    	
    	Date myDate = new Date();
		String fecha = new SimpleDateFormat("dd-MM-yyyy").format(myDate);
		
		try
        {
            archivoOut = new PrintWriter(new FileWriter("Pedidos.txt",true));
           
            archivoOut.println(nPedido+"_"+"PENDIENTE"+"_"+fecha);
       
            archivoOut.close();

            System.out.println("Datos almacenados en archivo pedido");
        }
        catch(IOException ioe)
        {
            System.out.println("Error "+ioe);
        }
    	
		try
        {
            archivoOut = new PrintWriter(new FileWriter("Pedidos-Items.txt",true));
           
            archivoOut.println(nPedido+"_"+datos+"\n");
       
            archivoOut.close();

            System.out.println("Datos almacenados en el archivo pedido-items");
        }
        catch(IOException ioe)
        {
            System.out.println("Error "+ioe);
        }
    	
    	try
        {
            archivoOut = new PrintWriter(new FileWriter("Items.txt",true));
           
            archivoOut.println(nPedido+total+"\n");
       
            archivoOut.close();

            System.out.println("Datos almacenados en el archivo items");
        }
        catch(IOException ioe)
        {
            System.out.println("Error "+ioe);
        }
    }
    
    
    public void actionPerformed(ActionEvent e)
    {
        String respuesta="";
        String datos ="";
        int cD=0, cC=0, cB=0, cCu=0, cM=0, cO=0, cP=0, tot=0;
     	 
        if(e.getSource()==bGuardar)
        {
        	String donas="", conchas="", bolillos="", cuernos="", muffins="", orejas="", polvorones="";
        	
        	donas=tfDonas.getText();
        	conchas=tfConchas.getText();
        	bolillos=tfBolillos.getText();
        	cuernos=tfCuernos.getText();
        	muffins=tfMuffins.getText();
        	orejas=tfOrejas.getText();
        	polvorones=tfPolvorones.getText();
        	
        	cD=Integer.parseInt(donas);
        	cC=Integer.parseInt(conchas);
        	cB=Integer.parseInt(bolillos);
        	cCu=Integer.parseInt(cuernos);
        	cM=Integer.parseInt(muffins);
        	cO=Integer.parseInt(orejas);
        	cP=Integer.parseInt(polvorones);
        	
        	String total = "_"+donas+"_"+conchas+"_"+bolillos+"_"+cuernos+"_"+muffins+"_"+orejas+"_" + polvorones;
        	
        	cD=cD*10;
        	cC=cC*12;
        	cB=cB*3;
        	cCu=cCu*10;
        	cM=cM*7;
        	cO=cO*12;
        	cP=cP*7;
        	
        	tot=cD+cC+cB+cCu+cM+cO+cP;
        	
        	datos="Donas:"+donas+"_"+cD+"_"+"Conchas:"+conchas+"_"+cC+"_"+"Bolillos:"+bolillos+"_"+cB+"_"+"Cuernos:"+cuernos+"_"+cCu+"_"+"Muffins:"+muffins+"_"+cM+"_"+"Orejas:"+orejas+"_"+cO+"_"+"Polvorones:"+polvorones+"_"+cP+"_"+"Total:"+"_"+tot;
        	
        	guardarPedido(datos,total);
        }
    }
    
    public static void main(String args[])
    {
        new Pedido();
    }
    
}