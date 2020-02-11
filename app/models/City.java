// package models;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;

// import io.ebean.Finder;
// import io.ebean.Model;
// import play.data.validation.Constraints;

// @Entity
// @Table(name="city")
// public class City extends Model{
	
// 	@Id
// 	@GeneratedValue
// 	public Long id;
	
// 	@Constraints.Required
// 	public String name;
	
// 	@ManyToOne
// 	@JoinColumn(name = "country_id", referencedColumnName = "id")
// 	public Long country;
	
// 	public static final Finder<Long, City> find = new Finder<>(City.class);
// }
