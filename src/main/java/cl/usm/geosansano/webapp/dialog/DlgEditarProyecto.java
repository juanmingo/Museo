package cl.usm.geosansano.webapp.dialog;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.entity.MuseoProyecto;
import cl.usm.geosansano.entity.MuseoProyectoDetalle;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.entity.Pais;
import cl.usm.geosansano.functions.FuncionFoto;
import cl.usm.geosansano.functions.FuncionNumero;
import cl.usm.geosansano.functions.FuncionSelectItem;
import cl.usm.geosansano.sessions.beans.MuseoProyectoDetalleFacade;
import cl.usm.geosansano.sessions.beans.MuseoProyectoDetalleFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoProyectoFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sessions.beans.PaisFacadeLocal;
import cl.usm.geosansano.sessions.beans.TipoVigenciaFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Juan D. Delgado Robles.
 */
@ManagedBean(name = "dlgEditarProyecto", eager = true)
@SessionScoped
public class DlgEditarProyecto implements Serializable {

//<editor-fold defaultstate="collapsed" desc="Atribtos">
    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;
    @EJB
    private MuseoProyectoDetalleFacadeLocal museoProyectoDetalleFL;
    @EJB
    private MuseoProyectoFacadeLocal museoProyectoFL;
    @EJB
    private PaisFacadeLocal paisFL;
    @EJB
    private TipoVigenciaFacadeLocal tipoVigenciaFL;
    //
    private MuseoUsuario museoUsuario;
    private MuseoProyecto museoProyect;
    //
    private BigInteger mususuId;
    private String musprodetNombre;
    private String musprodetDescripcion;
    //
    private StreamedContent objStreamedContent;
    //
    private ArrayList<SelectItem> listarSelectItemAños = new ArrayList<>();
    private List<Pais> paisList = new ArrayList<>();
    private List<MuseoProyectoDetalle> museoProyectoDetalleList;

//</editor-fold>
    public void cargarProyecto(long musproId) {

        this.museoUsuario = this.museoUsuarioFacade.findByCorreo(Common.obtenerCuentaUsuario());
        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));

        this.listarSelectItemAños = FuncionSelectItem.listarAños();
        this.paisList = this.paisFL.findAll();

        Collections.sort(this.paisList, (Pais p1, Pais p2) -> p1.getNomPais().compareTo(p2.getNomPais()));

        this.museoProyect = this.museoProyectoFL.find(musproId);

        this.museoProyectoDetalleList = museoProyectoDetalleFL.findByDetalleActivo(musproId);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyecto:dlgEditarProyecto");
    }

    public void closeInfoWindow() {
        System.out.println("closeInfoWindow");
        RequestContext context = RequestContext.getCurrentInstance();
        //context.update("PF('infoWindow').close()");
    }

    public void cargarAgregarFoto() {
        this.musprodetNombre = "";
        this.musprodetDescripcion = "";
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyectoFotos:dlgEditarProyectoFotos");
    }

    public void keyUpNombreFoto() {
        System.out.println("musprodetNombre: " + this.musprodetNombre);
    }

    public void cargarEditarFoto(MuseoProyectoDetalle objMPD) {
        System.out.println("musprodetNombre: " + this.musprodetNombre);

        this.objStreamedContent = FuncionFoto.obtenerFoto(objMPD.getMusprodetArchivo(), objMPD.getMuseoProyecto().getMusproNombre(), "./../images/icon_sin_imagen.png");

        System.out.println("objStreamedContent: " + this.objStreamedContent);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarFoto:dlgEditarFoto");
    }

    public void adjuntarArchivo(FileUploadEvent event) {

        System.out.println("event: " + event);

        MuseoProyectoDetalle objMPD = new MuseoProyectoDetalle();

        objMPD.setMusprodetDescripcion(this.musprodetDescripcion);
        objMPD.setMusprodetNombre(this.musprodetNombre);
        objMPD.setMusprodetArchivo(event.getFile().getContents());

        objMPD.setCodVigencia(tipoVigenciaFL.find(0));
        objMPD.setFechaModificacion(new Date());
        objMPD.setMuseoProyecto(this.museoProyect);
        objMPD.setMususuIdUsu(this.mususuId);

        System.out.println("objMPD: " + objMPD.getMusprodetNombre());

        this.museoProyectoDetalleList.add(objMPD);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyecto:dataFotosProyecto");
        context.update("PF('dlgEditarProyectoFotos').hide()");

    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public MuseoProyecto getMuseoProyect() {
        return museoProyect;
    }

    public void setMuseoProyect(MuseoProyecto museoProyect) {
        this.museoProyect = museoProyect;
    }
//</editor-fold>

    public ArrayList<SelectItem> getListarSelectItemAños() {
        return listarSelectItemAños;
    }

    public void setListarSelectItemAños(ArrayList<SelectItem> listarSelectItemAños) {
        this.listarSelectItemAños = listarSelectItemAños;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public List<MuseoProyectoDetalle> getMuseoProyectoDetalleList() {
        return museoProyectoDetalleList;
    }

    public void setMuseoProyectoDetalleList(List<MuseoProyectoDetalle> museoProyectoDetalleList) {
        this.museoProyectoDetalleList = museoProyectoDetalleList;
    }

    public String getMusprodetNombre() {
        return musprodetNombre;
    }

    public void setMusprodetNombre(String musprodetNombre) {
        this.musprodetNombre = musprodetNombre;
    }

    public String getMusprodetDescripcion() {
        return musprodetDescripcion;
    }

    public void setMusprodetDescripcion(String musprodetDescripcion) {
        this.musprodetDescripcion = musprodetDescripcion;
    }

    public StreamedContent getObjStreamedContent() {
        return objStreamedContent;
    }

    public void setObjStreamedContent(StreamedContent objStreamedContent) {
        this.objStreamedContent = objStreamedContent;
    }

}
