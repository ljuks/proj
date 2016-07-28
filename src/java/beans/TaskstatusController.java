package beans;

import jpa.Taskstatus;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import controllers.TaskstatusFacade;

import java.io.Serializable;
import java.util.Date;
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

@Named("taskstatusController")
@ViewScoped
public class TaskstatusController implements Serializable {

    @Inject
    private jpa.pro p;
    @EJB
    private controllers.TaskstatusFacade ejbFacade;
    private List<Taskstatus> items = null;
    private Taskstatus selected;

    public TaskstatusController() {
    }

    public Taskstatus getSelected() {
        return selected;
    }

    public void setSelected(Taskstatus selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TaskstatusFacade getFacade() {
        return ejbFacade;
    }

    public Taskstatus prepareCreate() {
        selected = new Taskstatus();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle1").getString("TaskstatusCreated"));

        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle1").getString("TaskstatusUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle1").getString("TaskstatusDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Taskstatus> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle1").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle1").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Taskstatus getTaskstatus(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Taskstatus> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Taskstatus> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Taskstatus.class)
    public static class TaskstatusControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TaskstatusController controller = (TaskstatusController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "taskstatusController");
            return controller.getTaskstatus(getKey(value));
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
            if (object instanceof Taskstatus) {
                Taskstatus o = (Taskstatus) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Taskstatus.class.getName()});
                return null;
            }
        }

    }

}
