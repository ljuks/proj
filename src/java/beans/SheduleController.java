package beans;

import jpa.Shedule;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import controllers.SheduleFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import jpa.pro;

@Named("sheduleController")
@ViewScoped
public class SheduleController implements Serializable {

    @Inject
    private jpa.pro p;
//    @Inject
    //  private ProjectsController projectidController;
    @EJB
    private controllers.SheduleFacade ejbFacade;
    private List<Shedule> items = null;
    private Shedule selected;
    private Date da;
    private String pname;

    public SheduleController() {
    }
    
     public boolean filterByProject (Object value, Object filter, Locale locale) {
        // it fails before calling this method
     if (value==null)
         return false;
        
        if (Integer.parseInt(value.toString()) == pro.getFilterproject())
                return true;
        
        return false;
     
    } 
    
    
    
    public void clearda(){
//        this.setDa(null);
da = null;
    }

    public Date getDa() {
        return da;
    }

    public void setDa(Date da) {
        this.da = da;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    
    
    
    public Shedule getSelected() {
        return selected;
    }

    public void setSelected(Shedule selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SheduleFacade getFacade() {
        return ejbFacade;
    }

    public Shedule prepareCreate() {
        selected = new Shedule();
        initializeEmbeddableKey();

        return selected;
    }

    public void create() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SheduleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SheduleUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SheduleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Shedule> getItems() {
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

    public Shedule getShedule(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Shedule> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Shedule> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Shedule.class)
    public static class SheduleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SheduleController controller = (SheduleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sheduleController");
            return controller.getShedule(getKey(value));
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
            if (object instanceof Shedule) {
                Shedule o = (Shedule) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Shedule.class.getName()});
                return null;
            }
        }

    }

    public void fill1000() {
        int i;
        for (i = 0; i < 10000; i++) {
            selected = new Shedule();
            initializeEmbeddableKey();
            selected.setTs(new Date());
            selected.setPid(p.getCurrentuserId());
            selected.setTdate(new Date());
            selected.setTask(Integer.toString(i));

            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SheduleCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }

        }
    }
}
