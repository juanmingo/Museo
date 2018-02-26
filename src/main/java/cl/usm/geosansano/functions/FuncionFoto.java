package cl.usm.geosansano.functions;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Juan D. Delgado Robles.
 */
public abstract class FuncionFoto {

    public static StreamedContent obtenerFoto(byte[] musprodetArchivo, String nombreArchivo, String rutaImagenPorDefecto) {
        StreamedContent objStrCon;
        if (musprodetArchivo != null) {
            objStrCon = new DefaultStreamedContent(new ByteArrayInputStream(musprodetArchivo), "PNG", nombreArchivo + ".PNG");
        } else {
            ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaImagen = context.getRealPath(rutaImagenPorDefecto);
            InputStream fotoAlumnoSinFoto = null;
            try {
                fotoAlumnoSinFoto = new BufferedInputStream(new FileInputStream(rutaImagen));
            } catch (FileNotFoundException e) {
                //LOGGER.log(Level.INFO, "[FuncionFotoIndividuo][fotoIndividuo] {0}", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
            objStrCon = new DefaultStreamedContent(fotoAlumnoSinFoto, "PNG", "FOTO_ALUMNO.PNG");
        }
        return objStrCon;
    }
}
