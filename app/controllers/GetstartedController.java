package controllers;

import java.util.List;

import com.google.inject.Inject;

import models.Business;
import models.BusinessCategory;
import models.City;
import models.Country;
import models.Location;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class GetstartedController  extends Controller{
	@Inject
	FormFactory formfactory;

	
	public Result getstarted() {
		
		User loggeduser=null;
		Business getBusiness=null;
		
		List <BusinessCategory> bnssc = BusinessCategory.find.all();
		
		List <City> city= City.find.all();
		
		List <Country> country = Country.find.all();
		
		List <Location> location =Location.find.all();
		loggeduser = User.find.query().where().eq("id", session("connected")).findOne();
		
		if(loggeduser!=null) {
			
			getBusiness	= Business.find.query().where()
					.eq("user_id", loggeduser.id)
					.findOne();
		}
		
		return ok(getstarted.render(country,loggeduser,city,location,bnssc,getBusiness));
	}
	
	
	//start business 
	
	public Result startbusiness() {


		Form<Business> businessForm = formfactory.form(Business.class);
		Business user = businessForm.bindFromRequest().get();
		
		User owner = User.find.query().where()
				.eq("id", session("connected"))
				.findOne();
		
		user.user_id = owner.id;
		
		
		user.save();
		
		flash("success", "We Will come to You in a few minute");
		
		return redirect("/");
		
	}
	
	
	
	
	
	
	
	
}
