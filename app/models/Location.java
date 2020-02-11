package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

//this class defines locations like kimironko that are not cities but either part 
//of the city or near the city

@Entity
@Table(name="location")
public class Location extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Constraints.Required
	public String name;
	
	@ManyToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	//defines the nearest cities to this location
	public Long city;
	

	public static final Finder<Long, Location> find = new Finder<>(Location.class);
}
