import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

public class Ventas
{
	private PrintWriter  archivoOut;
    private BufferedReader archivoIn;
	private Date fecha;
	
	public void pedidoRecogido(String n)
	{
		int encontrado=0;
		String datos = "";
		String pedidoBuscado = "";
		StringTokenizer st=null;
		
		try
        {
            archivoIn = new BufferedReader (new FileReader("Pedidos.txt"));
            while(archivoIn.ready()&& encontrado==0)
            {
                datos = archivoIn.readLine()+"\n";
                st = new StringTokenizer(datos,"_");
                pedidoBuscado = st.nextToken();
				/*
                donasB = st.nextToken();
                conchasB = st.nextToken();
                bolillosB = st.nextToken();
                */
                if(n.equals(pedidoBuscado))
                {
                	encontrado = 1;
                }
                //else
                //{
                	//respuesta = "No se ha pagado el pedido";	
                //} 
                            	   
            }
            archivoIn.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Error "+ioe);
        }
		
		try
        {
            archivoOut = new PrintWriter(new FileWriter("Recogidos.txt",true));
           
            archivoOut.println(datos);
       
            archivoOut.close();

            System.out.println("Datos almacenados en archivo recogidos");
        }
        catch(IOException ioe)
        {
            System.out.println("Error "+ioe);
        }
	}
	
	public String registrarVentas(String pedido,String donas,String conchas,String bolillos,String cuernos, String muffins, String orejas, String polvorones)
	{
		String respuesta="", datos="",str;
		StringTokenizer st=null;
		String pedidoBuscado="";
		int encontrado = 0;
		int cD=0, cC=0, cB=0, cCu=0, cM=0, cO=0, cP=0, tot=0;

		//primero buscar si ese pedido existe en pedidos.txt
		try
        {
            archivoIn = new BufferedReader (new FileReader("Recogidos.txt"));
            while(archivoIn.ready()&& encontrado==0)
            {
                datos = archivoIn.readLine()+"\n";
                st = new StringTokenizer(datos,"_");
                pedidoBuscado = st.nextToken();
               
                if(pedidoBuscado.equals(pedido))
                {
                	encontrado = 1;
                }             	   
            }
            archivoIn.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Error "+ioe);
        }
        
        //si existe poder registrar el pedido
        if(encontrado==1)
        {
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
        	
        	String datosGuardar=pedidoBuscado+"_Donas:"+donas+"_"+cD+"_"+"Conchas:"+conchas+"_"+cC+"_"+"Bolillos:"+bolillos+"_"+cB+"_"+"Cuernos:"+cuernos+"_"+cCu+"_"+"Muffins:"+muffins+"_"+cM+"_"+"Orejas:"+orejas+"_"+cO+"_"+"Polvorones:"+polvorones+"_"+cP+"_"+"Total:"+"_"+tot;
		
			try
        	{
                archivoOut = new PrintWriter(new FileWriter("Ventas.txt",true));
               
                archivoOut.println(datosGuardar);
           
                archivoOut.close();

                System.out.println("Datos almacenados de venta");
                
                respuesta = "Datos almacenados de venta";
            }
            catch(IOException ioe)
            {
                System.out.println("Error "+ioe);
                respuesta = "Error no se almacenaron los datos...";
            }	
        }
		else
		{
			respuesta = "Ese pedido no ha sido recogido...";
		}
		return respuesta;
	}
	
	//verifica que este pagado y completo
	public String verificarPedido(String pedido,String donas,String conchas,String bolillos,String cuerno, String muffin, String oreja, String polvoron)
	{
		String respuesta="", datos="",str="";
		StringTokenizer st=null;
		String pedidoBuscado="";
		int encontrado = 0;
		String donasB="",conchasB="",bolillosB="",cuernoB="",muffinB="",orejaB="",polvoronB="";
		polvoron=polvoron+"\n";
		try
        {
            archivoIn = new BufferedReader (new FileReader("Pagos.txt"));
            while(archivoIn.ready()&& encontrado==0)
            {
                datos = archivoIn.readLine()+"\n";
                st = new StringTokenizer(datos,"_");
                pedidoBuscado = st.nextToken();
				/*
                donasB = st.nextToken();
                conchasB = st.nextToken();
                bolillosB = st.nextToken();
                */
                if(pedido.equals(pedidoBuscado))
                {
                	encontrado = 1;
                }
                //else
                //{
                	//respuesta = "No se ha pagado el pedido";	
                //} 
                            	   
            }
            archivoIn.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Error "+ioe);
        }
        
               
                
        if(encontrado==1)
        {
        	
        	//abrir txt para el token de items
        	String items="";
        	StringTokenizer stItems=null;
        	encontrado=0;
        	try
        	{
            	archivoIn = new BufferedReader (new FileReader("Items.txt"));
            	while(archivoIn.ready()&& encontrado==0)
            	{
                	items = archivoIn.readLine()+"\n";
                	stItems = new StringTokenizer(items,"_");
                	pedidoBuscado = stItems.nextToken();
				
					if(pedido.equals(pedidoBuscado))
               		{
                		encontrado = 1;
                	}
                            	   
            	}
            archivoIn.close();
            System.out.println(items);
	        }
	        catch(IOException ioe)
	        {
	            System.out.println("Error "+ioe);
	        }
        	
        	donasB = stItems.nextToken();
        	conchasB = stItems.nextToken();
        	bolillosB = stItems.nextToken();
        	cuernoB = stItems.nextToken();
        	muffinB = stItems.nextToken();
        	orejaB = stItems.nextToken();
        	polvoronB = stItems.nextToken();
        	
        	int c = 0;
        	
        	if((donas.equals(donasB))&&(conchas.equals(conchasB))&&(bolillos.equals(bolillosB))&&(cuerno.equals(cuernoB))&&(muffin.equals(muffinB))&&(oreja.equals(orejaB))&&(polvoron.equals(polvoronB)))
        	{
        		respuesta = "Pedido correcto...";
        		pedidoRecogido(pedidoBuscado);
        	}
        	else
        	{
        		respuesta = "Pedido incompleto...";
        	} 
        	
        	//respuesta = verificacion(donas,conchas,bolillos);
        	
        	//respuesta = respuesta + "\nVerificar: \n" + error1 + error2 + error3;	
        }
        else
        {
        	respuesta = "Ese pedido no se ha pagado...";
        }
               
        return respuesta;
	}
}