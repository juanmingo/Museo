package cl.usm.geosansano.functions;

import cl.usm.geosansano.entity.MuseoProyectoDetalle;
import cl.usm.geosansano.sistema.Common;
import cl.usm.geosansano.sistema.Pagina;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private static ServletContext SC = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

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

    public static void guardarFotosProyectoServidor(List<MuseoProyectoDetalle> museoProyectoDetalleList) {
        for (MuseoProyectoDetalle objMuseoDet : museoProyectoDetalleList) {
            System.out.println("" + SC.getRealPath(Pagina.CARPETA_IMAGENES_PROYECTO));
            try {
                File files = new File(Common.obtenerAbsolutePath() + Pagina.CARPETA_IMAGENES_PROYECTO);
                if (!files.exists()) {
                    if (files.mkdirs()) {
                        //System.out.println("Multiple directories are created!: " + Common.obtenerAbsolutePath() + Pagina.CARPETA_IMAGENES_PROYECTO);
                    } else {
                        //System.out.println("Failed to create multiple directories!: " + Common.obtenerAbsolutePath() + Pagina.CARPETA_IMAGENES_PROYECTO);
                    }
                }
                //File file = new File(Common.obtenerAbsolutePath() + Pagina.CARPETA_IMAGENES_PROYECTO + objMuseoDet.getMuseoProyecto().getMususuId() + "_" + objMuseoDet.getMuseoProyectoDetallePK().getMusprodetId() + ".PNG");
                File file = new File(SC.getRealPath(Pagina.CARPETA_IMAGENES_PROYECTO) + "/" + objMuseoDet.getMuseoProyecto().getMususuId() + "_" + objMuseoDet.getMuseoProyectoDetallePK().getMusprodetId() + ".PNG");
                //System.out.println("-> " + SC.getRealPath(Pagina.CARPETA_IMAGENES_PROYECTO) + "/" + objMuseoDet.getMuseoProyecto().getMususuId() + "_" + objMuseoDet.getMuseoProyectoDetallePK().getMusprodetId() + ".PNG");
                FileOutputStream fop = new FileOutputStream(file);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fop.write(objMuseoDet.getMusprodetArchivo());
                fop.flush();
                fop.close();
                //System.out.println("Done");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}
