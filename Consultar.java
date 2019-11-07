import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Consultar
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
}