package cl.usm.geosansano.webapp.dialog;

//<editor-fold defaultstate="collapsed" desc="Imports"> 
import cl.usm.geosansano.controller.ImagenProyectoControler;
import cl.usm.geosansano.dto.ImagenProyectoDTO;
import cl.usm.geosansano.entity.MuseoProyecto;
import cl.usm.geosansano.entity.MuseoProyectoDetalle;
import cl.usm.geosansano.entity.MuseoProyectoDetallePK;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.entity.Pais;
import cl.usm.geosansano.functions.FuncionFotoMuseo;
import cl.usm.geosansano.functions.FuncionNumero;
import cl.usm.geosansano.functions.FuncionSelectItem;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;
//</editor-fold>

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
    private MuseoProyectoDetalle museoProyectoDetalle;

    //
    private BigInteger mususuId;
    private String musprodetNombre;
    private String musprodetDescripcion;
    private boolean isDisableAdjuntarFoto;
    private boolean isVisibleFotosAgregadas;
    //
    private StreamedContent objStreamedContent;
    //
    private ArrayList<SelectItem> listarSelectItemAños = new ArrayList<>();
    private List<ImagenProyectoDTO> listarImagenProyecto;
    private List<Pais> paisList = new ArrayList<>();
    private List<MuseoProyectoDetalle> museoProyectoDetalleList;
    private List<MuseoProyectoDetalle> museoProyectoDetalleListEliminar;

//</editor-fold>
    //
    public void cargarProyecto(long musproId) {

        this.museoProyectoDetalleListEliminar = new ArrayList<>();

        this.museoUsuario = this.museoUsuarioFacade.findByCorreo(Common.obtenerCuentaUsuario());
        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));

        this.listarSelectItemAños = FuncionSelectItem.listarAños();
        this.paisList = this.paisFL.findAll();

        Collections.sort(this.paisList, (Pais p1, Pais p2) -> p1.getNomPais().compareTo(p2.getNomPais()));

        this.museoProyect = this.museoProyectoFL.find(musproId);

        this.museoProyectoDetalleList = museoProyectoDetalleFL.findByDetalleActivo(this.museoProyect.getMusproId());

        this.isVisibleFotosAgregadas = this.museoProyectoDetalleList.size() > 0;

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyecto:dlgEditarProyecto");
    }

    public void cargarProyectoEliminar(long musproId) {

        this.museoUsuario = this.museoUsuarioFacade.findByCorreo(Common.obtenerCuentaUsuario());
        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));

        this.museoProyect = this.museoProyectoFL.find(musproId);

        this.museoProyectoDetalleList = museoProyectoDetalleFL.findByDetalleActivo(this.museoProyect.getMusproId());
        this.listarImagenProyecto = ImagenProyectoControler.listarImagenProyecto(this.museoProyectoDetalleList);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEliminarProyecto:dlgEliminarProyecto");
    }

    public void guardarCambiosProyecto() {

        if (this.museoProyect.getMusproId() == 0 || this.museoProyect.getMusproId() == 1 || this.museoProyect.getMusproId() == 2 || this.museoProyect.getMusproId() == 3 || this.museoProyect.getMusproId() == 4 || this.museoProyect.getMusproId() == 5) {
            this.museoProyect.setMusproDescripcion(this.museoProyect.getMusproDescripcion().toLowerCase());
        } else {
            this.museoProyect.setMusproDescripcion(this.museoProyect.getMusproDescripcion().toUpperCase());
        }

        this.museoProyect.setMusproNombre(this.museoProyect.getMusproNombre().toUpperCase());
        this.museoProyect.setMusproCiudad(this.museoProyect.getMusproCiudad().toUpperCase());

        this.museoProyect.setFechaModificacion(new Date());
        this.museoProyect.setMususuIdUsu(this.mususuId);

        this.museoProyectoFL.edit(this.museoProyect);

        //AGREGA LOS ELEMENTOS A ELIMINAR
        for (MuseoProyectoDetalle objMPD : this.museoProyectoDetalleListEliminar) {
            this.museoProyectoDetalleList.add(objMPD);
        }

        //MuseoProyectoDetalle
        //long maxMusprodetId = this.museoProyectoDetalleFL.newMusprodetId(this.museoProyect.getMusproId());
        for (MuseoProyectoDetalle objMPD : this.museoProyectoDetalleList) {

            if (objMPD.getMuseoProyectoDetallePK().getMusproId() != 0 && objMPD.getMuseoProyectoDetallePK().getMusprodetId() != 0) {

                objMPD.setMusprodetNombre(objMPD.getMusprodetNombre().toUpperCase());
                objMPD.setMusprodetDescripcion(objMPD.getMusprodetDescripcion().toUpperCase());

                objMPD.setFechaModificacion(new Date());
                objMPD.setMususuIdUsu(this.mususuId);

                this.museoProyectoDetalleFL.edit(objMPD);
            } else {

                long maxMusprodetId = this.museoProyectoDetalleFL.newMusprodetId(this.museoProyect.getMusproId());

                objMPD.getMuseoProyectoDetallePK().setMusproId(this.museoProyect.getMusproId());
                objMPD.getMuseoProyectoDetallePK().setMusprodetId(maxMusprodetId);

                objMPD.setMusprodetNombre(objMPD.getMusprodetNombre().toUpperCase());
                objMPD.setMusprodetDescripcion(objMPD.getMusprodetDescripcion().toUpperCase());

                objMPD.setFechaModificacion(new Date());
                objMPD.setMususuIdUsu(this.mususuId);

                this.museoProyectoDetalleFL.create(objMPD);
            }
            //maxMusprodetId++;
        }

        this.museoProyectoDetalleList = museoProyectoDetalleFL.findByDetalleActivo(this.museoProyect.getMusproId());

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "EDICIÓN: " + this.museoProyect.getMusproNombre(), "LATITUD: " + this.museoProyect.getMusproLatitud() + " - LONGITUD" + this.museoProyect.getMusproLongitud()));

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyecto:dataFotosProyecto");
        context.update("formSansano:msjProyectos");
    }

    public void cargarAgregarFoto() {
        this.musprodetNombre = "";
        this.musprodetDescripcion = "";

        this.isDisableAdjuntarFoto = true;

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyectoFotos:dlgEditarProyectoFotos");
    }

    public void keyUpNombreFoto() {
        this.isDisableAdjuntarFoto = !(this.musprodetNombre != null && !"".equals(this.musprodetNombre) && this.musprodetDescripcion != null && !"".equals(this.musprodetDescripcion));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyectoFotos:fudFoto");
    }

    public void cargarEditarFoto(MuseoProyectoDetalle objMPD) {
        this.museoProyectoDetalle = objMPD;
        this.objStreamedContent = FuncionFotoMuseo.obtenerFoto(this.museoProyectoDetalle.getMusprodetArchivo(), this.museoProyectoDetalle.getMuseoProyecto().getMusproNombre(), "./../images/icon_sin_imagen.png");
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarFoto:dlgEditarFoto");
    }

    public void cargarEditarFotoEliminar(MuseoProyectoDetalle objMPD) {
        this.museoProyectoDetalle = objMPD;
        this.objStreamedContent = FuncionFotoMuseo.obtenerFoto(this.museoProyectoDetalle.getMusprodetArchivo(), this.museoProyectoDetalle.getMuseoProyecto().getMusproNombre(), "./../images/icon_sin_imagen.png");
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarFotoEliminar:dlgEditarFotoEliminar");
    }

    public void adjuntarArchivo(FileUploadEvent event) {

        MuseoProyectoDetalle objMPD = new MuseoProyectoDetalle();
        MuseoProyectoDetallePK objMPDId = new MuseoProyectoDetallePK(0, 0);

        objMPD.setMuseoProyectoDetallePK(objMPDId);

        objMPD.setMusprodetDescripcion(this.musprodetDescripcion);
        objMPD.setMusprodetNombre(this.musprodetNombre);
        objMPD.setMusprodetArchivo(event.getFile().getContents());

        objMPD.setCodVigencia(this.tipoVigenciaFL.find(2));
        objMPD.setFechaModificacion(new Date());
        objMPD.setMuseoProyecto(this.museoProyect);
        objMPD.setMususuIdUsu(this.mususuId);

        this.museoProyectoDetalleList.add(objMPD);

        this.isVisibleFotosAgregadas = this.museoProyectoDetalleList.size() > 0;

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyecto:dataFotosProyecto");
        context.update("formEditarProyecto:pnFotosProyecto");
        context.update("formEditarFoto:dlgEditarFoto");
        context.update("PF('dlgEditarProyectoFotos').hide()");

    }

    public void eliminarFotoProyecto() {

        this.museoProyectoDetalle.setCodVigencia(this.tipoVigenciaFL.find(4));
        this.museoProyectoDetalle.setFechaModificacion(new Date());
        this.museoProyectoDetalle.setMususuIdUsu(this.mususuId);

        this.museoProyectoDetalleList.remove(this.museoProyectoDetalle);

        this.museoProyectoDetalleListEliminar.add(this.museoProyectoDetalle);

        this.isVisibleFotosAgregadas = this.museoProyectoDetalleList.size() > 0;

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyecto:dataFotosProyecto");
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editar", ((MuseoProyectoDetalle) event.getObject()).getMusprodetNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formSansano:msjProyectos");
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editar Cancelado", ((MuseoProyectoDetalle) event.getObject()).getMusprodetNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formSansano:msjProyectos");
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public MuseoProyecto getMuseoProyect() {
        return museoProyect;
    }

    public void setMuseoProyect(MuseoProyecto museoProyect) {
        this.museoProyect = museoProyect;
    }

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

    public boolean isIsDisableAdjuntarFoto() {
        return isDisableAdjuntarFoto;
    }

    public void setIsDisableAdjuntarFoto(boolean isDisableAdjuntarFoto) {
        this.isDisableAdjuntarFoto = isDisableAdjuntarFoto;
    }

    public boolean isIsVisibleFotosAgregadas() {
        return isVisibleFotosAgregadas;
    }

    public void setIsVisibleFotosAgregadas(boolean isVisibleFotosAgregadas) {
        this.isVisibleFotosAgregadas = isVisibleFotosAgregadas;
    }

    public MuseoProyectoDetalle getMuseoProyectoDetalle() {
        return museoProyectoDetalle;
    }

    public void setMuseoProyectoDetalle(MuseoProyectoDetalle museoProyectoDetalle) {
        this.museoProyectoDetalle = museoProyectoDetalle;
    }

    public List<ImagenProyectoDTO> getListarImagenProyecto() {
        return listarImagenProyecto;
    }

    public void setListarImagenProyecto(List<ImagenProyectoDTO> listarImagenProyecto) {
        this.listarImagenProyecto = listarImagenProyecto;
    }
//</editor-fold>
}
