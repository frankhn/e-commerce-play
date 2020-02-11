package models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.typesafe.config.Optional;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

@Entity
@Table(name="product")
public class Product extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Constraints.Required
	@Constraints.MaxLength(value = 15) 
	public String name;
	
	@Constraints.Required
	@ManyToOne
	@JoinColumn(name = "BusinessId", referencedColumnName = "id")
	public Long businessID;
	
	@Constraints.Required
	@ManyToOne
	@NotEmpty
	@JoinColumn(name = "CategoryId", referencedColumnName = "id")
	public Long category_id;
	
	@Optional
	public String tag;//
	
	@Constraints.Required
	@Lob
	@Constraints.MinLength(value = 40) 
	public String product_description;
	
	@Lob
	@Optional
	public String kinyarwanda_description;
	
	
	@Optional
	public Long sellingPrice;
	
	@Optional
	public Long buyingPrice;
	
	@ManyToOne
	@JoinColumn(name="city_id", referencedColumnName="id")
	public Long city;
	
	@Optional
	public Long longitude;
	
	@Optional
	public Long latitude;
	
	@Optional
	public String deal_type;//like hostel,hotel restaurant,rent
	
	@Optional
	public Date checkin;//start date to checkin if deal is hotel or renting type
	
	@Optional
	public Date checkout;
	
	@Optional
	public String sign;//if the product is from E-dealing + image
	
	@Lob
	@Optional
	public String deal_end_status;//days or endless-null or today only
	
	@Optional
	public Long today_price;
	
	@Lob
	@Constraints.MaxLength(value = 18) 
	@Optional
	public String promotion;//like 56% off on given purchases
	
	@Optional
	public Long sells;
	
	@Optional
	public Long instock;
	
	@Lob
	@Constraints.Required
	@NotEmpty
	public String image;
	
	@ManyToOne
	@JoinColumn(name="product_city_location_id", referencedColumnName ="id")
	public Location location;
	
	@Constraints.Required
	@NotEmpty
	public String sub_categor1;
	
	@Constraints.Required
	@NotEmpty
	public String sub_categor2;
	
	@Optional
	public String sub_categor3;
	
	@Optional
	public Timestamp last_review;
	
	@Optional
	public Long rating;
	
	//current time stamp generated
	@Optional
	public Date deal_start_time;
	
	@Optional
	public Date deal_end_time;
	
	//add boolean : whether same prices for all product option or no
//	
//	@OneToOne
//	@Column(unique=true)
//	@JoinColumn(name="sum_of_ratings_id", referencedColumnName="rating_id")
//	public Long sum_rating;
//	
	@Optional
	@Column(unique=true)
	public String product_absolute_link;
	
	
	public static final Finder<Long, Product> find = new Finder<>(Product.class);
}


