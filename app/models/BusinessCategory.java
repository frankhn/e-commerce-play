package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.typesafe.config.Optional;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

@Entity
@Table(name="business_category")
public class BusinessCategory extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Constraints.Required
	public String name;
	
	@Lob
	@Optional
	public String content;
	
	

	public static final Finder<Long, BusinessCategory> find = new Finder<>(BusinessCategory.class);
}
