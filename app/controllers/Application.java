package controllers;

import java.util.List;

import org.mongodb.morphia.query.Query;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.util.JSON;

import models.Person;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.*;
import views.html.helper.form;

public class Application extends Controller {

    public  Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public  Result allPersons() {
		JsonNode json = null;
		Person person = new Person();
		json = Json.toJson(person.all());
		return ok(json).as("application/json");
	}

    
    public Result getPhone(String phone) {
    	
    	Query<Person> q = MorphiaObject.datastore.createQuery(Person.class).filter("_id", phone);
    	List<Person> list = q.asList();
    	
    	return ok(Json.toJson(list));
    }
    
   // @BodyParser.Of(BodyParser.Json.class)
	public Result addPerson() {
		//JsonNode json = request().body().asJson();
		
		Person person = new Person();
	    person = Form.form(Person.class).bindFromRequest().get();
		person.save();
		return ok();
		
		
		
		/*
		String firstName = json.findPath("firstName").textValue();
		String lastName = json.findPath("lastName").textValue();
		String location = json.findPath("location").textValue();
		String phone = json.findPath("phone").textValue();
		
		
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setLocation(location);
		person.setPhone(phone);
	*/
		
		/*Person person = new Person();
		person.setFirstName("firstName");
		person.setLastName("lastName");
		person.setLocation("location");
		person.setPhone("phone");
		person.create(person);
		JsonNode result = Json.toJson(person.all());
		return ok(result).as("application/json");
		
		/*
		 * User newUser = Json.fromJson(request().body().asJson(), User.class);
    User inserted = Database.addUser(newUser);
    return created(Json.toJson(inserted));
		 */
		
		//return ok(result);

	}


    
}
