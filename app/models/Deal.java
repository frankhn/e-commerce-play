//package models;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.Lob;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.hibernate.validator.constraints.NotEmpty;
//
//import com.typesafe.config.Optional;
//
//import io.ebean.Model;
//import io.ebean.annotation.DbDefault;
//import play.data.validation.Constraints;
//
//@Entity
//@Table(name="deal")
//public class Deal extends Model{
//	
//	
//	@Id
//	@GeneratedValue
//	public Long id;
//	
//	@Constraints.Required
//	@Constraints.MaxLength(value = 15) 
//	public String name;
//	
//	@Constraints.Required
//	@ManyToOne
//	@JoinColumn(name = "BusinessId", referencedColumnName = "id")
//	public Long businessID;
//	
//	@Constraints.Required
//	@ManyToOne
//	@NotEmpty
//	@JoinColumn(name = "CategoryId", referencedColumnName = "id")
//	public Long category_id[];
//	
//	@Constraints.Required
//	public String tags[];
//	
//	@Constraints.Required
//	@Lob
//	@Constraints.MaxLength(value = 100)
//	@Constraints.MinLength(value = 50)
//	public String product_description;
//	
//	@Lob
//	@Optional
//	@Constraints.MaxLength(value = 100)
//	@Constraints.MinLength(value = 50)
//	public String kinyarwanda_description;
//	
//	@ManyToOne
//	@JoinColumn(name="city_id", referencedColumnName="id")
//	public Long city[];
//	
//	@Optional
//	public String sign;//if the product is from E-dealing + image
//	
//	@Optional
//	public String deal_end_status;//days or endless-null or today only
//	
//	@Lob
//	@Constraints.MaxLength(value = 18) 
//	@Optional
//	public String promotion;//narrations for the promotion
//
//	@Lob
//	@Constraints.Required
//	@NotEmpty
//	public String image[];
//	
//	
//	@Optional
//	@DbDefault("current_timestamp")
//	public Timestamp created_on;
//	
//	@Optional
//	public Long rate;
//	
//	@Optional
//	public Date deal_end_time;
//	
//	@Optional
//	@Column(unique=true)
//	public String product_absolute_link;
//
//}
