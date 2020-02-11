package controllers;

import play.mvc.Security;
import play.mvc.Http.Context;
import play.mvc.Result;
/**
 * Implement authorization for this system.
 * getUserName() and onUnauthorized override superclass methods to restrict
 * access to the profile() page to logged in users.  
 */
public class Secured extends Security.Authenticator {
	
	
	//chech if user is logged in?
	  @Override
	  public String getUsername(Context ctx) {
		  //then find his username
	    return ctx.session().get("connected");
	  }
	  
	  //on unUnauthorized access redirect to home page
	  @Override
	  public Result onUnauthorized(Context context) {
	    return redirect("/"); 
	  }
	 
	  public static String getUser(Context ctx) {
	    return ctx.session().get("connected");
	  }
	  
	  public static boolean isLoggedIn(Context ctx) {
		    return (getUser(ctx) != null);
		  }
}
