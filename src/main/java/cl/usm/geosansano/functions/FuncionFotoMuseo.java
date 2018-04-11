package cl.usm.geosansano.functions;

import cl.usm.geosansano.entity.MuseoProyectoDetalle;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
public abstract class FuncionFotoMuseo {

    private static Logger LOGGER = Logger.getLogger("[FuncionFotoIndividuo]");

    public static StreamedContent obtenerFotoDetalle(MuseoProyectoDetalle objMuseoProyectoDetalle, String rutaImagenPorDefecto) {
        StreamedContent objStrCon;
        if (objMuseoProyectoDetalle != null) {
            ByteArrayInputStream fotoInputStream = new ByteArrayInputStream(objMuseoProyectoDetalle.getMusprodetArchivo());
            objStrCon = new DefaultStreamedContent(fotoInputStream, "PNG", objMuseoProyectoDetalle.getMuseoProyecto().getMususuId() + "_" + objMuseoProyectoDetalle.getMuseoProyectoDetallePK().getMusprodetId() + ".PNG");
        } else {
            ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaImagen = context.getRealPath(rutaImagenPorDefecto);
            InputStream SinFoto = null;
            try {
                SinFoto = new BufferedInputStream(new FileInputStream(rutaImagen));
            } catch (FileNotFoundException e) {
                LOGGER.log(Level.INFO, "[FuncionFotoIndividuo][fotoIndividuo] {0}", e.getMessage());
            }
            objStrCon = new DefaultStreamedContent(SinFoto, "PNG", "0.PNG");
        }
        return objStrCon;
    }

    public static List<StreamedContent> obtenerFotoDetalleList(List<MuseoProyectoDetalle> museoProyectoDetalleList) {
        List<StreamedContent> streamedContentList = new ArrayList<>();
        for (MuseoProyectoDetalle objMuseoDet : museoProyectoDetalleList) {
            streamedContentList.add(FuncionFotoMuseo.obtenerFotoDetalle(objMuseoDet, "./images/icon_sin_imagen.png"));
        }
        return streamedContentList;
    }
}
