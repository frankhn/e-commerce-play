package controllers;

import com.google.inject.Inject;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

public class SportController  extends Controller{
	@Inject
	FormFactory formfactory;
	
	public Result setCity() {
		
		DynamicForm requestData = formfactory.form().bindFromRequest();

		String city = requestData.get("city");
		String sportlocation = requestData.get("location");
		System.out.println("session changed!!!'"+sportlocation+"'");
		session("city",city);
		session("sportlocation",sportlocation);
		return redirect("/sporting");
	}
	
	public Result setSportCategory() {
		DynamicForm requestData = formfactory.form().bindFromRequest();

		String category = requestData.get("category");
		System.out.println("session changed!!!'"+category+"'");
		session("sportcategory",category);
		
		return redirect("/sporting");
	}

}
