package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Email;
import io.ebean.Finder;
import io.ebean.Model;


@Entity
@Table(name= "Pending_activation")
public class PendingActivation  extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	public String link;
	
	@Constraints.Required
	@Email
	public String email;
	
	@Constraints.Required
	public String date;
	
	
	public static final Finder<Long, PendingActivation> find = new Finder<>(PendingActivation.class);
	

}
