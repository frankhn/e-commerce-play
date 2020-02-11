package models;

import javax.persistence.CascadeType;
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
@Table(name="product_option")
public class ProductOption extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Constraints.Required
	public String name;
	
	@Lob
	@Constraints.Required
	public String content;
	
	@Optional
	public Long sellingPrice;
	
	@Optional
	public Long buyingPrice;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "product_option_id", referencedColumnName = "id")
	public Long productid;

	public static final Finder<Long, ProductOption> find = new Finder<>(ProductOption.class);

}
