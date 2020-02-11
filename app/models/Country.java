// package models;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.Table;

// import com.typesafe.config.Optional;

// import io.ebean.Finder;
// import io.ebean.Model;
// import play.data.validation.Constraints;

// @Entity
// @Table(name="country")
// public class Country extends Model{
	
// 	@Id
// 	@GeneratedValue
// 	public Long id;
	
// 	@Column(unique=true)
// 	@Constraints.Required
// 	public String name;
	
// 	@Column(unique=true)
// 	@Constraints.Required
// 	public Long code;
	
// 	@Optional
// 	public String flag;
	

// 	public static final Finder<Long, Country> find = new Finder<>(Country.class);
	
// }
