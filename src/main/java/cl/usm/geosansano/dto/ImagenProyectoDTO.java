package cl.usm.geosansano.dto;

import cl.usm.geosansano.entity.MuseoProyectoDetalle;
import java.io.Serializable;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Juan D. Delgado Robles
 */
public class ImagenProyectoDTO implements Serializable {

    private MuseoProyectoDetalle museoProyectoDetalle;
    private StreamedContent streamedContent;
    private String rutaImagen;
    private String nombreArchivo;

    public MuseoProyectoDetalle getMuseoProyectoDetalle() {
        return museoProyectoDetalle;
    }

    public void setMuseoProyectoDetalle(MuseoProyectoDetalle museoProyectoDetalle) {
        this.museoProyectoDetalle = museoProyectoDetalle;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

}
