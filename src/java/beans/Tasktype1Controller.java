package beans;

import jpa.Tasktype1;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import controllers.Tasktype1Facade;

import java.io.Serializable;
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

@Named("tasktype1Controller")
@SessionScoped
public class Tasktype1Controller implements Serializable {

    @EJB
    private controllers.Tasktype1Facade ejbFacade;
    private List<Tasktype1> items = null;
    private Tasktype1 selected;

    public Tasktype1Controller() {
    }

    public Tasktype1 getSelected() {
        return selected;
    }

    public void setSelected(Tasktype1 selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private Tasktype1Facade getFacade() {
        return ejbFacade;
    }

    public Tasktype1 prepareCreate() {
        selected = new Tasktype1();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle11").getString("Tasktype1Created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle11").getString("Tasktype1Updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle11").getString("Tasktype1Deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tasktype1> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle11").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle11").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Tasktype1 getTasktype1(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Tasktype1> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tasktype1> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tasktype1.class)
    public static class Tasktype1ControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Tasktype1Controller controller = (Tasktype1Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tasktype1Controller");
            return controller.getTasktype1(getKey(value));
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
            if (object instanceof Tasktype1) {
                Tasktype1 o = (Tasktype1) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tasktype1.class.getName()});
                return null;
            }
        }

    }

}
