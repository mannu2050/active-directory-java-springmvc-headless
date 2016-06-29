package com.headlessauthentication.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.naming.ServiceUnavailableException;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;

@Controller
public class headlessauth {
	private String AUTHORITY = "https://login.microsoftonline.com/common/";
    private String CLIENT_ID = "xxxxxx-xxxx-xxxx-xxxxx";
	private String username = "xxx@xxxxx.onmicrosoft.com";
	private String password = "xxxxxx";
	@RequestMapping("/authenticate")
	public ModelAndView authentication() throws Exception {
 
	            AuthenticationResult result = getAccessTokenFromUserCredentials(
	                    username, password);
	            String message = "<br>"
	    				+ "<h3>Authentication Succeeded</h3>" + "<h4>Access Token - </h4>" + result.getAccessToken()+
	    	            "<br><h4>Refresh Token -</h4> " + result.getRefreshToken()+
	    	            "<br><h4>ID Token - </h4>" + result.getIdToken() +
	    	           "</div><br><br>";

	            return new ModelAndView("authenticate", "message", message);
	}
	 private AuthenticationResult getAccessTokenFromUserCredentials(
	            String username, String password) throws Exception {
	        AuthenticationContext context = null;
	        AuthenticationResult result = null;
	        ExecutorService service = null;
	        try {
	            service = Executors.newFixedThreadPool(1);
	            context = new AuthenticationContext(AUTHORITY, false, service);
	            Future<AuthenticationResult> future = context.acquireToken(
	                    "https://graph.windows.net", CLIENT_ID, username, password,
	                    null);
	            result = future.get();
	        } finally {
	            service.shutdown();
	        }

	        if (result == null) {
	            throw new ServiceUnavailableException(
	                    "authentication result was null");
	        }
	        return result;
	    }
	}



