
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/*
 * Clase utilizada para todas las peticiones que se realizaran (tanto GET como
 * POST).
 *
 * @author Luis Delgado J.
 */
public class Connection {
    
    /*
     * Constructor.
     */
    public Connection() {
        // NADA!
    }

    /*
     * Metodo utilizado para las peticiones POST.
     * Este metodo no utiliza HttpClient sino HttpURLConnection.
     * Los parametros se le pasan en la variable 'param'.
     *
     * @param sUrl Url a la que se envia la peticion POST.
     * @param param Parametros a enviar.
     */
    public void postMethod(String sUrl, String param) {
        try {
            URL url = new URL(sUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(param);
            wr.flush();
            conn.getInputStream();
            conn.disconnect();
        } catch (Exception ex) {
            // EXCEPCION!
        }
    }

}
