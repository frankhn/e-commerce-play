package controllers;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import com.learning.api.controllers.utils.CurrentDateTime;
import com.learning.api.controllers.utils.RandomNumber;
import com.learning.api.controllers.utils.TokenGenerator;

import akka.dispatch.Foreach;
import io.ebean.Query;
import models.Business;
import models.City;
import models.Product;
import models.Token;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

public class HomeController extends Controller {
	@Inject
	FormFactory formfactory;

	public Result index() {
		User loggeduser=null;
		String sessioncity;
		
		if(session("city")==null) {
			sessioncity="kigali";
			session("city",sessioncity);
		}else {
			sessioncity=session("city");
		}
		
		 loggeduser = User.find.query().where().eq("id", session("connected")).findOne();
		
		 City city_id=City.find.query().where().eq("name", sessioncity).findOne();
		 
		 List<City> allCity =City.find.query()
				 .orderBy("name")
				 .findList();
		 
		List<Product> products = Product.find.nativeSql("select *from product where city"
				+ "='"+city_id.id+"'order by RAND() Limit 20")
				.setFirstRow(0)
		        .setMaxRows(20)
		        .findPagedList()
				.getList();
		// calling the Top product
		Product mprodu = null;
		
		 mprodu = Product.find.nativeSql("select *from product where city"
				+ "= '"+city_id.id+"' order by RAND() Limit 1")
				.findOne();
		 if(mprodu==null) {
			 mprodu= Product.find.nativeSql("select *from product where city"
						+ "= 1 order by RAND() Limit 1")
						.findOne();
		 }
		  
		 List<Product> topprod = Product.find.nativeSql("select *from product where city"
				+ "='"+city_id.id+"'order by RAND() Limit 2")
				.findList();
		 if(topprod==null) {
			 topprod =Product.find.nativeSql("select *from product where city"
						+ "= 1 order by RAND() Limit 2")
						.findList();
		 }
		 
		 Product downad = null;
			
		 downad = Product.find.nativeSql("select *from product where city"
				+ "= '"+city_id.id+"' order by RAND() Limit 1")
				.findOne();
		 if(downad==null) {
			 downad= Product.find.nativeSql("select *from product where city"
						+ "= 1 order by RAND() Limit 1")
						.findOne();
		 }
		
		return ok(main.render(products, loggeduser,sessioncity,allCity,mprodu,topprod,downad));
		
	}

	public Result feed() {
		
		return redirect("/register");
	}

	public Result create() {

		return TODO;
	}
	
	
	public Result show(String name, Long id) {
		
		Product product = Product.find.query().where().eq("id", id).findOne();
			String city=session("city");
		User user=null;
		user= User.find.query().where().eq("id", session("connected")).findOne();
		return ok(show.render(product, user,city));
		
		}	
	public Result login() {

		DynamicForm requestData = formfactory.form().bindFromRequest();

		String email = requestData.get("email");
		String password = requestData.get("password");

		//System.out.println("checking if values are caught:" + email + "....password:.." + password);

		User loginUser = User.find.query().where().eq("email", email).findOne();

		if (loginUser != null) {
			if (password.equals(loginUser.password)) {
					session().clear();
				session("connected", "" + loginUser.id + "");
				if(loginUser.city!=null) {
				session("city", ""+loginUser.city);
				}
				//User user= User.find.query().where().eq("token", session("connected")).findOne();
				//List<Product> products = Product.find.all();

				//return ok(main.render(products, user));
				return redirect("/");

			}

			// return redirect("/feed");
			// }

			// String message=flash("failed");
			// message="invalid credentials";

		}
		// Email or passwords is wrong
		flash("failed", "Wrong Creadentials");
		return redirect("/");
	}

	public Result registration() {

		return ok(startregistration.render());

	}

	public Result signup() {

		Form<User> userForm = formfactory.form(User.class);
		User user = userForm.bindFromRequest().get();
		
		String fname=user.fname;
		String lname=user.lname;
		
		String signupemail = userForm.rawData().get("email");
		System.out.println(""+signupemail);
		User emailExist= User.find.query().where().eq("email", signupemail).findOne();
		
		if(emailExist==null) {

			Integer d =RandomNumber.randomNumber();
			
			String username=fname+"-"+lname+"_"+d;
			
			String token = TokenGenerator.generateToken();
			user.token = token;
			user.username=username;
			user.save();

			Token newToken = new Token();

			String tokeStamp = CurrentDateTime.currentDate();
			String status = "inactive";

			newToken.token = token;
			newToken.timestamp = tokeStamp;
			newToken.status = status;
			
			
			newToken.save();

			return redirect("/");


		}else {
			
			flash("email exist", "Email already Exist!!!");
			
			return redirect("/register");
		}
			}
	
	@Security.Authenticated(Secured.class)
	public Result account(String username) {
		String city=session("city");
		User user= User.find.query().where().eq("id", session("connected")).findOne();
		
		Business userBusiness=null;
		userBusiness=Business.find.query().where()
			.eq("user_id", user.id)
			.findOne();
		
		List<City> allCity =City.find.query()
				 .orderBy("name")
				 .findList();
		
		List<Product> products=null;
		
		if(userBusiness!=null) {
			
			products=Product.find.query().where()
					.eq("Business_id", userBusiness.id)
					.orderBy("name")
					.findList();
		}
		
		return ok(profile.render(user,city,userBusiness,products,allCity));
	}
	
	
	@Security.Authenticated(Secured.class)
	public Result logout() {
		session().clear();
		return redirect("/");
	}

}
/* selecting sponsored product id's  
List<Sponsored> sponsored = Sponsored.find.nativeSql("select *from sponsored where city1="
			+ "'"+city_id.id+"' or city2='"+city_id.id+"' "
			+ "or city3='"+city_id.id+"' or city4='"+city_id.id+"' order by RAND()")
			.findList();
		JsonNode spJson = Json.toJson(sponsored);
		
		String name = spJson.findPath("product_id").textValue();
		
		System.out.println(""+name+"");
		*/