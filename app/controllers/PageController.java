package controllers;

import java.util.List;

import models.City;
import play.mvc.Controller;
import views.html.*;
import play.mvc.Result;

public class PageController  extends Controller{
	
	
	public Result about(String cnt) {
		String sessioncity;
		
		if(session("city")==null) {
			sessioncity="kigali";
			session("city",sessioncity);
		}else {
			sessioncity=session("city");
		}
		List<City> allCity =City.find.query()
				 .orderBy("name")
				 .findList();
		City city_id=City.find.query().where().eq("name", sessioncity).findOne();
		 
		return ok(about.render(cnt,sessioncity,allCity));
	}
	
	public Result newsroom() {
String sessioncity;
		
		if(session("city")==null) {
			sessioncity="kigali";
			session("city",sessioncity);
		}else {
			sessioncity=session("city");
		}
		List<City> allCity =City.find.query()
				 .orderBy("name")
				 .findList();
		City city_id=City.find.query().where().eq("name", sessioncity).findOne();
		return ok(newsroom.render(sessioncity,allCity));
	}

	public Result customerService() {
String sessioncity;
		
		if(session("city")==null) {
			sessioncity="kigali";
			session("city",sessioncity);
		}else {
			sessioncity=session("city");
		}
		List<City> allCity =City.find.query()
				 .orderBy("name")
				 .findList();
		City city_id=City.find.query().where().eq("name", sessioncity).findOne();
		return ok(customer.render(sessioncity,allCity));
	}
	
	public Result community() {
String sessioncity;
		
		if(session("city")==null) {
			sessioncity="kigali";
			session("city",sessioncity);
		}else {
			sessioncity=session("city");
		}
		List<City> allCity =City.find.query()
				 .orderBy("name")
				 .findList();
		City city_id=City.find.query().where().eq("name", sessioncity).findOne();
		return ok(community.render(sessioncity,allCity));
	}
	
public Result policies() {
	String sessioncity;
	
	if(session("city")==null) {
		sessioncity="kigali";
		session("city",sessioncity);
	}else {
		sessioncity=session("city");
	}
	List<City> allCity =City.find.query()
			 .orderBy("name")
			 .findList();
	City city_id=City.find.query().where().eq("name", sessioncity).findOne();
		return ok(policy.render(sessioncity,allCity));
	}
}
