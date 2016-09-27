package beans;

import jpa.Projectpersons;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import controllers.ProjectpersonsFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import jpa.Projects;
import jpa.pro;
import org.primefaces.event.SelectEvent;

@Named("projectpersonsController")
@ViewScoped
public class ProjectpersonsController implements Serializable {

    @EJB
    private controllers.ProjectpersonsFacade ejbFacade;
    private List<Projectpersons> items = null;
    private Projectpersons selected;
    private int flag;
    private int projectsid;

    public ProjectpersonsController() {
       
    }


    public void projselect(SelectEvent event) {

        Projects item = (Projects) event.getObject();
        this.setProjectsid(item.getId());
        this.setFlag(0);
      pro.setFilterproject(11);
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getProjectsid() {
        return projectsid;
    }

    public void setProjectsid(int projectsid) {
        this.projectsid = projectsid;
    }

    public Projectpersons getSelected() {
        return selected;
    }

    public void setSelected(Projectpersons selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProjectpersonsFacade getFacade() {
        return ejbFacade;
    }

    public Projectpersons prepareCreate() {
        selected = new Projectpersons();
        initializeEmbeddableKey();
        selected.setProjectid(this.getProjectsid());
        return selected;
    }
    
    public void onChangeMenu(){
   
      
        this.selected.setKpi(this.selected.getUsersptypeid().getPercent());       
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle777").getString("ProjectpersonsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle777").getString("ProjectpersonsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle777").getString("ProjectpersonsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Projectpersons> getItems() {
        if ((this.getFlag() == 0) || (items == null)) {
            this.setFlag(1);
//            items = getFacade().findAll();
            EntityManager em = Persistence.createEntityManagerFactory("projPU").createEntityManager();

            TypedQuery<Projectpersons> query = em.createNamedQuery("Projectpersons.findByProjectid", Projectpersons.class);
            query.setParameter("projectid", this.getProjectsid());

            items = query.getResultList();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle777").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle777").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Projectpersons getProjectpersons(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Projectpersons> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Projectpersons> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Projectpersons.class)
    public static class ProjectpersonsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProjectpersonsController controller = (ProjectpersonsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "projectpersonsController");
            return controller.getProjectpersons(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Projectpersons) {
                Projectpersons o = (Projectpersons) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Projectpersons.class.getName()});
                return null;
            }
        }

    }

}
