package beans;

import jpa.Projects;
import beans.util.JsfUtil;
import beans.util.JsfUtil.PersistAction;
import controllers.ProjectsFacade;

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

@Named("projectsController")
@ViewScoped
public class ProjectsController implements Serializable {

    @Inject
    private jpa.pro p;

    @EJB
    private controllers.ProjectsFacade ejbFacade;
    private List<Projects> items = null;
    private Projects selected;

    public ProjectsController() {
    }

    public Projects getSelected() {
//            p.setProjectsid(this.selected.getId());
        return selected;
    }

    public void setSelected(Projects selected) {
        this.selected = selected;
 //       p.setProjectsid(selected.getId());
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProjectsFacade getFacade() {
        return ejbFacade;
    }

    public Projects prepareCreate() {
        selected = new Projects();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle77").getString("ProjectsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setTs(new Date());
        selected.setPid(p.getCurrentuserId());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle77").getString("ProjectsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle77").getString("ProjectsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Projects> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle77").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle77").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Projects getProjects(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Projects> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Projects> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Projects.class)
    public static class ProjectsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProjectsController controller = (ProjectsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "projectsController");
            return controller.getProjects(getKey(value));
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
            if (object instanceof Projects) {
                Projects o = (Projects) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Projects.class.getName()});
                return null;
            }
        }

    }

}
