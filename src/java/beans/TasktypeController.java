package beans;

import jpa.Tasktype;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import controllers.TasktypeFacade;

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

@Named("tasktypeController")
@ViewScoped
public class TasktypeController implements Serializable {

    @Inject
    private jpa.pro p;

    @EJB
    private controllers.TasktypeFacade ejbFacade;
    private List<Tasktype> items = null;
    private List<Tasktype> mselected = null;
    private Tasktype selected;

    public TasktypeController() {
    }

    public List<Tasktype> getMselected() {
        return mselected;
    }

    public void setMselected(List<Tasktype> mselected) {
        this.mselected = mselected;
    }

    
    
    
    
    public Tasktype getSelected() {
        return selected;
    }

    public void setSelected(Tasktype selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TasktypeFacade getFacade() {
        return ejbFacade;
    }

    public Tasktype prepareCreate() {
        selected = new Tasktype();
        initializeEmbeddableKey();
        selected.setPid(p.getCurrentuserId());
        selected.setTs(new Date());
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TasktypeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TasktypeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TasktypeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Tasktype> getItems() {
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

    public Tasktype getTasktype(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Tasktype> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Tasktype> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Tasktype.class)
    public static class TasktypeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TasktypeController controller = (TasktypeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tasktypeController");
            return controller.getTasktype(getKey(value));
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
            if (object instanceof Tasktype) {
                Tasktype o = (Tasktype) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Tasktype.class.getName()});
                return null;
            }
        }

    }

}
