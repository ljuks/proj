package beans;

import jpa.Projectstatus;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import controllers.ProjectstatusFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named("projectstatusController")
@ViewScoped
public class ProjectstatusController implements Serializable {

    @Inject
    private jpa.pro p;
    @EJB
    private controllers.ProjectstatusFacade ejbFacade;
    private List<Projectstatus> items = null;
    private Projectstatus selected;

    public ProjectstatusController() {
    }

    public Projectstatus getSelected() {
        return selected;
    }

    public void setSelected(Projectstatus selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProjectstatusFacade getFacade() {
        return ejbFacade;
    }

    public Projectstatus prepareCreate() {
        selected = new Projectstatus();
        initializeEmbeddableKey();

        return selected;
    }

    public void create() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProjectstatusCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProjectstatusUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProjectstatusDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Projectstatus> getItems() {
        if (items == null) {
            items = getFacade().findAll();
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Projectstatus getProjectstatus(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Projectstatus> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Projectstatus> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Projectstatus.class)
    public static class ProjectstatusControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProjectstatusController controller = (ProjectstatusController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "projectstatusController");
            return controller.getProjectstatus(getKey(value));
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
            if (object instanceof Projectstatus) {
                Projectstatus o = (Projectstatus) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Projectstatus.class.getName()});
                return null;
            }
        }

    }

}
