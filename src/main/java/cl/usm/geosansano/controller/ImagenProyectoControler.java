package cl.usm.geosansano.controller;

import cl.usm.geosansano.dto.ImagenProyectoDTO;
import cl.usm.geosansano.entity.MuseoProyectoDetalle;
import cl.usm.geosansano.functions.FuncionFotoMuseo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan D. Delgado Robles.
 */
public class ImagenProyectoControler {
    
    public static List<ImagenProyectoDTO> listarImagenProyecto(List<MuseoProyectoDetalle> museoProyectoDetalleList) {
        
        ArrayList<ImagenProyectoDTO> listObjDTO = new ArrayList<>();
        
        FuncionFotoMuseo.guardarFotosProyectoServidor(museoProyectoDetalleList);
        
        for (MuseoProyectoDetalle objMuseoProyectoDetalle : museoProyectoDetalleList) {
            
            ImagenProyectoDTO objDTO = new ImagenProyectoDTO();
            
            objDTO.setMuseoProyectoDetalle(objMuseoProyectoDetalle);
            objDTO.setStreamedContent(FuncionFotoMuseo.obtenerFoto(objMuseoProyectoDetalle.getMusprodetArchivo(), objMuseoProyectoDetalle.getMusprodetNombre(), "./../images/icon_sin_imagen.png"));
            
            String rutaImagen = "/FotosProyectos/" + objMuseoProyectoDetalle.getMuseoProyecto().getMususuId() + "_" + objMuseoProyectoDetalle.getMuseoProyectoDetallePK().getMusprodetId() + ".PNG";
            objDTO.setRutaImagen(rutaImagen);
            objDTO.setNombreArchivo(objMuseoProyectoDetalle.getMuseoProyecto().getMususuId() + "_" + objMuseoProyectoDetalle.getMuseoProyectoDetallePK().getMusprodetId() + ".PNG");
            
            listObjDTO.add(objDTO);
        }
        
        return listObjDTO;
        
    }
    
}
