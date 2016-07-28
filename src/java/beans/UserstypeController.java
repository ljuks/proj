package beans;

import jpa.Userstype;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import controllers.UserstypeFacade;

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


@Named("userstypeController")
@ViewScoped
public class UserstypeController implements Serializable {
@Inject jpa.pro p;
    @EJB
    private controllers.UserstypeFacade ejbFacade;
    private List<Userstype> items = null;
    private Userstype selected;

    public UserstypeController() {
    }

    public Userstype getSelected() {
        return selected;
    }

    public void setSelected(Userstype selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserstypeFacade getFacade() {
        return ejbFacade;
    }

    public Userstype prepareCreate() {
        selected = new Userstype();
  
        initializeEmbeddableKey();
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserstypeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserstypeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserstypeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Userstype> getItems() {
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

    public Userstype getUserstype(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Userstype> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Userstype> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Userstype.class)
    public static class UserstypeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserstypeController controller = (UserstypeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userstypeController");
            return controller.getUserstype(getKey(value));
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
            if (object instanceof Userstype) {
                Userstype o = (Userstype) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Userstype.class.getName()});
                return null;
            }
        }

    }

}
