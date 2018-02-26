package cl.usm.geosansano.webapp.dialog;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cl.usm.geosansano.entity.MuseoProyecto;
import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.entity.Pais;
import cl.usm.geosansano.functions.FuncionNumero;
import cl.usm.geosansano.functions.FuncionSelectItem;
import cl.usm.geosansano.sessions.beans.MuseoProyectoFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sessions.beans.PaisFacadeLocal;
import cl.usm.geosansano.sistema.Common;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

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
    private MuseoProyectoFacadeLocal museoProyectoFL;
    @EJB
    private PaisFacadeLocal paisFL;
    //
    private MuseoUsuario museoUsuario;
    private MuseoProyecto museoProyect;
    //
    private BigInteger mususuId;
    //
    private ArrayList<SelectItem> listarSelectItemAños = new ArrayList<>();
    private List<Pais> paisList = new ArrayList<>();

//</editor-fold>
    public void cargarProyecto(long musproId) {

        this.museoUsuario = this.museoUsuarioFacade.findByCorreo(Common.obtenerCuentaUsuario());
        this.mususuId = FuncionNumero.nvlBigInteger(String.valueOf(Common.obtenerMususuId()));

        this.listarSelectItemAños = FuncionSelectItem.listarAños();
        this.paisList = this.paisFL.findAll();

        Collections.sort(this.paisList, (Pais p1, Pais p2) -> p1.getNomPais().compareTo(p2.getNomPais()));

        this.museoProyect = this.museoProyectoFL.find(musproId);

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarProyecto:dlgEditarProyecto");
    }

    public void closeInfoWindow() {
        System.out.println("closeInfoWindow");
        RequestContext context = RequestContext.getCurrentInstance();
        //context.update("PF('infoWindow').close()");
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

}
