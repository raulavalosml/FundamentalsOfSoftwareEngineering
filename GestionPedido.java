import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

public class GestionPedido
{
	private PrintWriter  archivoOut;
    private BufferedReader archivoIn;
	private Date fecha;
	
	
	public String consultar()
	{
		String respuesta="";
		
		try
        {
            archivoIn = new BufferedReader (new FileReader("Ventas.txt"));
            while(archivoIn.ready())
            {
                respuesta=respuesta+archivoIn.readLine()+"\n";
                
            }
            archivoIn.close();
        }
        catch(IOException ioe)
        {
            System.out.println("Error "+ioe);
        }
        
        return respuesta;
	}
	
	public String verificarPedido(String pedido)
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
}