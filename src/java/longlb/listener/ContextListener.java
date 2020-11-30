/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import java.util.List;

/**
 * Web application lifecycle listener.
 *
 * @author Long Le
 */
public class ContextListener implements ServletContextListener {

    private final Logger log = Logger.getLogger(ContextListener.class.getName());
    private final String FUNCTIONS_MAPPING_FILE = "/WEB-INF/functions.txt";
    private final String ADMIN_FUNCTIONS_FILE = "/WEB-INF/admin-functions.txt";
    private final String MEMBER_FUNCTIONS_FILE = "/WEB-INF/member-functions.txt";
    private final String GUEST_FUNCTIONS_FILE = "/WEB-INF/guest-functions.txt";
    private final String FRONTEND_RESOURCES_FILE = "/WEB-INF/frontend-resource.txt";

    public Map<String, String> loadFunctionsMapping(String fileName) {
	Map<String, String> functionsMap = null;
	FileReader fr = null;
	BufferedReader br = null;

	try {
	    fr = new FileReader(fileName);
	    br = new BufferedReader(fr);

	    while (br.ready()) {
		String row = br.readLine();
		String[] splitedStrings = row.split("=");

		String key = splitedStrings[0];
		String value = splitedStrings[1];

		if (functionsMap == null) {
		    functionsMap = new HashMap<>();
		}

		functionsMap.put(key, value);
	    }
	} catch (FileNotFoundException ex) {
	    log.error("FileNotFoundException: " + ex.getMessage());
	} catch (IOException ex) {
	    log.error("IOException: " + ex.getMessage());
	} finally {
	    try {
		if (br != null) {
		    br.close();
		}

		if (fr != null) {
		    fr.close();
		}
	    } catch (IOException ex) {
		log.error("Close BufferedReader-FileReader IOException: " + ex.getMessage());
	    }
	}
	return functionsMap;
    }
    
    public List<String> loadFunctions(String fileName) {
	List<String> functionsList = null;
	FileReader fr = null;
	BufferedReader br = null;

	try {
	    fr = new FileReader(fileName);
	    br = new BufferedReader(fr);

	    while (br.ready()) {
		String function = br.readLine();

		if (functionsList == null) {
		    functionsList = new ArrayList<>();
		}

		functionsList.add(function);
	    }
	} catch (FileNotFoundException ex) {
	    log.error("FileNotFoundException: " + ex.getMessage());
	} catch (IOException ex) {
	    log.error("IOException: " + ex.getMessage());
	} finally {
	    try {
		if (br != null) {
		    br.close();
		}

		if (fr != null) {
		    fr.close();
		}
	    } catch (IOException ex) {
		log.error("Close BufferedReader-FileReader IOException: " + ex.getMessage());
	    }
	}
	return functionsList;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
	ServletContext context = sce.getServletContext();
	String realPath = context.getRealPath("/");
	
	Map<String, String> functionsMap = loadFunctionsMapping(realPath + FUNCTIONS_MAPPING_FILE);
	if (functionsMap != null){
	    context.setAttribute("FUNCTIONS_MAP", functionsMap);
	}	
	
	List<String> adminFuntions = loadFunctions(realPath + ADMIN_FUNCTIONS_FILE);
	if (adminFuntions != null){
	    context.setAttribute("ADMIN_FUNCTIONS", adminFuntions);
	}
	
	List<String> memberFunctions = loadFunctions(realPath + MEMBER_FUNCTIONS_FILE);
	if (memberFunctions != null){
	    context.setAttribute("MEMBER_FUNCTIONS", memberFunctions);
	}
	
	List<String> guestFunctions = loadFunctions(realPath + GUEST_FUNCTIONS_FILE);
	if (guestFunctions != null){
	    context.setAttribute("GUEST_FUNCTIONS", guestFunctions);
	}
	
	List<String> frontendResources = loadFunctions(realPath + FRONTEND_RESOURCES_FILE);
	if (frontendResources != null){
	    context.setAttribute("FRONTEND_RESOURCES", frontendResources);
	}
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
