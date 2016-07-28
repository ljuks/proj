package beans;

import jpa.Projecttypes;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import controllers.ProjecttypesFacade;

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

@Named("projecttypesController")
@ViewScoped
public class ProjecttypesController implements Serializable {

    @Inject
    private jpa.pro p;

    @EJB
    private controllers.ProjecttypesFacade ejbFacade;
    private List<Projecttypes> items = null;
    private Projecttypes selected;

    public ProjecttypesController() {
    }

    public Projecttypes getSelected() {
        return selected;
    }

    public void setSelected(Projecttypes selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProjecttypesFacade getFacade() {
        return ejbFacade;
    }

    public Projecttypes prepareCreate() {
        selected = new Projecttypes();
        initializeEmbeddableKey();
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProjecttypesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProjecttypesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProjecttypesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Projecttypes> getItems() {
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

    public Projecttypes getProjecttypes(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Projecttypes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Projecttypes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Projecttypes.class)
    public static class ProjecttypesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProjecttypesController controller = (ProjecttypesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "projecttypesController");
            return controller.getProjecttypes(getKey(value));
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
            if (object instanceof Projecttypes) {
                Projecttypes o = (Projecttypes) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Projecttypes.class.getName()});
                return null;
            }
        }

    }

}
