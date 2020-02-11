package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.typesafe.config.Optional;

import org.hibernate.validator.constraints.NotEmpty;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Pattern;

@Entity
@Table(name = "user")
public class User extends Model {

	@Id
	@GeneratedValue
	public Long id;

	@Constraints.Required
	@Pattern(value = "[a-z0-9\\-]+", message = "Only alphanumerical lower case characters and - are allowed in this field.")
	@NotEmpty
	public String fname;

	@Constraints.Required
	@Pattern(value = "[a-z0-9\\-]+", message = "Only alphanumerical lower case characters and - are allowed in this field.")
	@NotEmpty
	public String lname;
	
	@Lob
	@Optional
	public String username;
	
	@NotEmpty
	@Email
	@Column(unique=true)
	@Constraints.Required
	public String email;
	
	@Column(unique=true)
	public Long phone;

	@Constraints.Required
	@NotEmpty
	@MinLength(6)
	public String password;
	
	@Optional
	public String country;
	
	@Optional
	public String city;
	
	@Optional
	public String location;
	
	@Column(unique=true)
	@OneToOne
	@JoinColumn(name = "user_token", referencedColumnName = "id")
	public String token;

	//@ManyToOne(cascade=CascadeType.ALL)
	//public List<Token> Token;
	
	public static final Finder<Long, User> find = new Finder<>(User.class);
	
}
