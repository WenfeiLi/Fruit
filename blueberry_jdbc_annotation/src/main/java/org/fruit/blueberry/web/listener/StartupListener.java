package org.fruit.blueberry.web.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fruit.blueberry.dto.constants.ProjectConstants;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Stephen on 10/10/2014.
 */
public class StartupListener extends ContextLoaderListener implements ServletContextListener {
    private static final Log log = LogFactory.getLog(StartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        if (log.isDebugEnabled()) {
            log.debug("Start to initialize context...");
        }

        super.contextInitialized(event);
        ServletContext context = event.getServletContext();
        ProjectConstants.CONTEXT_PATH_VALUE = context.getContextPath();
        context.setAttribute(ProjectConstants.CONTEXT_PATH_KEY, ProjectConstants.CONTEXT_PATH_VALUE);

        ProjectConstants.APP_PATH_VAL = context.getRealPath("");
        context.setAttribute(ProjectConstants.APP_PATH_KEY, ProjectConstants.APP_PATH_VAL);
    }
}
