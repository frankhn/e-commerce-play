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

@Entity
@Table(name="user_preference")
public class UserPreference extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Constraints.Required
	public String name;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	public Long user_id;
	
	//@ManyToOne(cascade=CascadeType.ALL)
	//public List<User> user;

	public static final Finder<Long, User> find = new Finder<>(User.class);
}
