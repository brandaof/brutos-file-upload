package br.brandao.examples.fileupload.web;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationContextListener 
	implements ServletContextListener{

	private static ServletContext servletContext;
	
    public void contextInitialized(ServletContextEvent sce) {
    	servletContext = sce.getServletContext();
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
	
    @Produces
    @Named("fileRepositoryPath")
    @Singleton
    public String getFilesPath(){
    	return servletContext.getRealPath("/WEB-INF/files");
    }
    
}
