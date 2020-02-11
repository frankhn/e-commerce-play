package controllers;

import java.util.List;

import models.Business;
import models.City;
import models.Product;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class SearchController extends Controller{
	
	public Result search(String word, String city) {
		
		if(word.isEmpty()) {
			session("city",city);
			System.out.println("found no search content"+city+"");
			return redirect("/");
		}
		
		System.out.println("cought city and search"+word+ " word " +city+" ");
		
		City scity=City.find.query().where()
				.eq("name", city)
				.findOne();
		
		List<Product> searchProducts=Product.find.query().where()
				.and()
				.or()
				.ilike("name", "%"+word+"%")
				.ilike("product_description", "%"+word+"%")
				.ilike("deal_type", "%"+word+"%")
				.ilike("promotion", "%"+word+"%")
				.ilike("sub_categor2", "%"+word+"%")
				.ilike("sub_categor3", "%"+word+"%")
				.ilike("sub_categor1", "%"+word+"%")
				.ilike("product_absolute_link", "%"+word+"%")
				.ilike("tag", "%"+word+"%")
				.endOr()
				.eq("city", scity.id)
				.endAnd()
				.findList();
		
		List<City> allCity =City.find.query()
				 .orderBy("name")
				 .findList();
		
		List<Business> searchBusiness=Business.find.query().where()
				.or()
				.ilike("name", "%"+word+"%")
				.endOr()
				.findList();
		
		
		
		session("city",city);
		String sessioncity= session("city");
		
		User user=null;
		user= User.find.query().where().eq("id", session("connected")).findOne();
		
		
		
		return ok(searchresult.render(searchProducts, user,sessioncity,allCity));
	}

}
