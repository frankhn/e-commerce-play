package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints.Required;

@Entity
@Table(name="product_sub_category")
public class ProductSubCategory extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public String name;
	
	@ManyToOne
	@JoinColumn(name="super_category_id", referencedColumnName="id")
	public Long categoryId;

	public static final Finder<Long, ProductSubCategory> find = new Finder<>(ProductSubCategory.class);
}
