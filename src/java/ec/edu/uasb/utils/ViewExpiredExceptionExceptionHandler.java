/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.utils;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 *
 * @author victor.barba
 */
public class ViewExpiredExceptionExceptionHandler extends ExceptionHandlerWrapper {

    private final ExceptionHandler wrapped;

    public ViewExpiredExceptionExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
            ExceptionQueuedEvent event = i.next();
//            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            ExceptionQueuedEventContext context = event.getContext();
            Throwable t = context.getException();
            if (t instanceof ViewExpiredException) {
//                ViewExpiredException vee = (ViewExpiredException) t;
                FacesContext facesContext = FacesContext.getCurrentInstance();
//                Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
                NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
                try {
                    // Push some useful stuff to the request scope for use in the page
//                    requestMap.put("currentViewId", vee.getViewId());                    
                    navigationHandler.handleNavigation(facesContext, null, "sessionExpired");
                    facesContext.renderResponse();
                } finally {
                    i.remove();
                }
            }
        }

        // At this point, the queue will not contain any ViewExpiredEvents. Therefore, let the parent handle them.
        getWrapped().handle();
    }
}
