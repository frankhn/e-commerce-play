package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

@Entity
@Table(name="product_image")
public class ProductImage extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Constraints.Required
	@Lob
	public String name;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name= "image_product_id", referencedColumnName = "id")
	public Long image;
	

	public static final Finder<Long, ProductImage> find = new Finder<>(ProductImage.class);
}
