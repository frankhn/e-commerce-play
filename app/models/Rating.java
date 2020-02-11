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
@Table(name="rating")
public class Rating extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Lob
	@Optional
	@Constraints.Required
	public String Review;
	
	@ManyToOne
	@JoinColumn(name= "user_rating_id", referencedColumnName = "id")
	public Long user_id;
	
	public Long count; //1 for the like 
	
	
	//@ManyToOne(cascade=CascadeType.ALL)
	//public List<Product> product;

	
	public static final Finder<Long, Rating> find = new Finder<>(Rating.class);
}
