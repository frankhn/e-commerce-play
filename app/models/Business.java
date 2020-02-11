package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.typesafe.config.Optional;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

@Entity
@Table(name = "business")
public class Business extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Constraints.Required
	public String name;

	@ManyToOne
	@JoinColumn(name="Business_country", referencedColumnName="id")
	public Long country;
	
	@ManyToOne
	@JoinColumn(name="business_city", referencedColumnName="id")
	public Long city;
	
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName="id")
	public Long category;
	
	@Constraints.Required
	public Long user_id;
	
	@ManyToOne
	@JoinColumn(name="Business_city_location_id", referencedColumnName ="id")
	public Long location;
	
	@Lob
	@Optional
	public String location_description;
	
	@Optional
	public Long latitude;
	
	@Optional
	public Long longitude;
	
	
	@Optional
	public Long Phone1;
	
	@Optional 
	public Long phone2;
	
	@Lob 
	@Constraints.MinLength(value = 100)
	public String description;
	
	@Constraints.Required
	public String legal_status;
	
	@Optional
	public String closing_time;
	
	@Optional
	public String working_status;
	
	@Optional
	public String opening_hour;
	
	@Lob
	@Optional
	public String website;
	
	//@OneToMany(cascade = CascadeType.ALL)
	//public List<Product> products;
	
	//@ManyToOne(cascade = CascadeType.ALL)
	//public List<Followers> following;
	
	public static final Finder<Long, Business> find = new Finder<>(Business.class);

}
