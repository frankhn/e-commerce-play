package controllers;

import com.google.inject.Inject;
import com.learning.api.controllers.utils.CurrentDateTime;
import com.learning.api.controllers.utils.MailerService;
import com.learning.api.controllers.utils.ProcessDates;
import com.learning.api.controllers.utils.RandomNumber;
import com.learning.api.controllers.utils.RandomWord;
import com.learning.api.controllers.utils.TokenGenerator;

import models.PendingActivation;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.*;

public class UserController extends Controller{
	@Inject
	FormFactory formfactory;
	
	public Result activationlink() {
		
		DynamicForm requestData = formfactory.form().bindFromRequest();
	    String email = requestData.get("email");
	   // System.out.println(""+email);
	   
	    String token = TokenGenerator.generateToken();
	    int randomnumber = RandomNumber.randomNumber();
	    String randomword = RandomWord.randomAlphaNumeric(10);
	    
	    String code=token+randomnumber+randomword;
	    
	    String confUrl = "http://localhost:9000/activateaccount/user/me/"+code+"";
	    
	    MailerService.sendMail(email, confUrl);
	    System.out.println("activation sent successfully"); 
	    flash("success", "activation Link sent successfully");
	    PendingActivation client = new PendingActivation();
	    
	    client.email= email;
	    client.link= code;
	    client.date = CurrentDateTime.currentDateFullFormat();
	    
	    client.save();
		return redirect("/");
	}
	
	public Result confirmactvation(String code) {
		
		PendingActivation p =PendingActivation.find.query().where()
				.eq("link", code).findOne();
		if(p==null) {
			flash("failed","Invalid Activation Link");
			return redirect("/");
		}
		int d = ProcessDates.isAcivationLinkExpired(p.date);
		User user =User.find.query().where()
				.eq("email", p.email).findOne();
		if(d==0 && user==null){
			String email=p.email;
			return ok(register.render(email));
			
		}else if(d==1){
			flash("success"," Activation Link has"
					+ " expired");
			return redirect("/");
		
		}else {
			flash("success"," You've already registered Proceed and Login"
					+ " For Better Experience");
			return redirect("/");
		
		}
	}	
	
	
	public Result businessregistration(Long id) {
		return TODO;
	}
}
