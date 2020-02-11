package controllers;

import java.util.List;

import models.City;
import models.Location;
import models.Product;
import models.ProductCategory;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class BrowseController extends Controller {

	public Result browse(String city, String category) {

		session("city", city);
		String sessioncity = session("city");

		City pcity = City.find.query().where().eq("name", city).findOne();

		ProductCategory pcategory = ProductCategory.find.query().where().eq("name", category).findOne();
		if (pcategory == null) {
			flash("failed", "category you're trying to access does not exit or no product yet");
			return redirect("/");
		}
		String categoryclass = pcategory.classification;

		List<ProductCategory> categories = ProductCategory.find.query().where()
				.eq("classification", pcategory.classification).findList();

		List<Location> locations = Location.find.query().where().eq("city", pcity.id).findList();

		List<City> allCity = City.find.query().orderBy("name").findList();

		List<Product> products = Product.find.query().where().and().eq("city", pcity.id).endAnd().and()
				.eq("category_id", pcategory.id).endAnd().orderBy("name").findList();
		User user = null;
		user = User.find.query().where().eq("id", session("connected")).findOne();

		return ok(browse.render(products, user, categories, locations, sessioncity, categoryclass, category, allCity));
	}

	public Result browselocation(String city, String category, String location) {

		return TODO;
	}

	public Result gateways(String destination) {
		String sessioncity;
		if (session("city") == null) {
			sessioncity = "kigali";
			session("city", sessioncity);
		} else {
			sessioncity = session("city");
		}

		City pcity = City.find.query().where().eq("name", sessioncity).findOne();

		List<Product> products = Product.find
				.nativeSql("select *from product where city" + "='" + pcity.id + "'order by RAND() Limit 20")
				.setFirstRow(0).setMaxRows(20).findPagedList().getList();
		List<City> allCity = City.find.query().orderBy("name").findList();
		User user = null;
		user = User.find.query().where().eq("id", session("connected")).findOne();

		return ok(gateways.render(sessioncity, products, user, allCity));
	}

	public Result city(String city, String department) {

		session("city", city);
		String sessioncity = session("city");

		City pcity = City.find.query().where().eq("name", city).findOne();

		User user = null;
		user = User.find.query().where().eq("id", session("connected")).findOne();

		// department may be best sellers
		// it not yet implemented

		List<Location> locations = Location.find.query().where().eq("city", pcity.id).findList();

		List<City> allCity = City.find.query().orderBy("name").findList();

		List<ProductCategory> localcategories = ProductCategory.find.query().where().eq("classification", "local")
				.findList();

		List<ProductCategory> goodscategories = ProductCategory.find.query().where().eq("classification", "goods")
				.findList();

		List<Product> products = Product.find.query().where().eq("city", pcity.id).orderBy("name").findList();

		return ok(citydeals.render(products, user, sessioncity, locations, allCity, localcategories, goodscategories));
	}

	public Result learning() {

		User loggeduser = null;
		String sessioncity;

		if (session("city") == null) {
			sessioncity = "kigali";
			session("city", sessioncity);
		} else {
			sessioncity = session("city");
		}
		loggeduser = User.find.query().where().eq("id", session("connected")).findOne();

		City city_id = City.find.query().where().eq("name", sessioncity).findOne();

		List<City> allCity = City.find.query().orderBy("name").findList();
		List<Product> products = Product.find.query().where().eq("city", city_id.id).orderBy("name").findList();

		return ok(learning.render(products, loggeduser));
	}

	public Result sporting() {

		User loggeduser = null;
		String sessioncity;
		String sessionlocation=null;
		String sessionsportcategory=null;

		if (session("city") == null) {
			sessioncity = "kigali";
			session("city", sessioncity);
		} else {
			sessioncity = session("city");
		}
		
		if (session("sportlocation") != null) {
			sessionlocation = session("sportlocation");
		}
		if (session("sportcategory") != null) {
			sessionsportcategory = session("sportcategory");
		}else {
			sessionsportcategory = "soccer";
		}
		
		
		String userLocation = null;
		loggeduser = User.find.query().where().eq("id", session("connected")).findOne();
		if (loggeduser != null) {
			userLocation = loggeduser.location;
			session("userLocation", userLocation);
		} // for accessing clubs in users location not yet implemented

		String sporting = "sport";
		List<ProductCategory> allcategory = ProductCategory.find
				.nativeSql("select *from product_category where classification" + "='" + sporting + "'").findList();

		City city_id = City.find.query().where().eq("name", sessioncity).findOne();

		List<City> allCity = City.find.query().orderBy("name").findList();

		List<Location> alllocation = Location.find.query().where().eq("city", city_id.id).orderBy("name").findList();

		List<Product> products = Product.find.nativeSql("select *from product where city='"+city_id.id+"' order by RAND()").findList();
		return ok(sport.render(products, allcategory, loggeduser, sessioncity, allCity, alllocation,sessionsportcategory,sessionlocation));
	}

	public Result salon() {
		return TODO;
	}

	public Result supplier() {

		return TODO;
	}

	public Result foodsmarket() {
		return TODO;
	}

	public Result music() {
		return TODO;
	}

	public Result cinema() {
		return TODO;
	}
}
