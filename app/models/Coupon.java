package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import controllers.Secured;
import play.data.validation.Constraints;
import play.mvc.Security;
import io.ebean.Finder;
import io.ebean.Model;

@Entity
@Table(name="coupon")
@Security.Authenticated(Secured.class)
public class Coupon extends Model{
	
	
	@Id
	@GeneratedValue
	public Long id;
	
	@Constraints.Required
	public String coupon_code;
	
	@Constraints.Required
	public Date start_validation;
	
	@Constraints.Required
	public Date end_validation;
	
	@ManyToOne
	@JoinColumn(name="coupon_owner",referencedColumnName="id")
	public Long coupon_user_id;
	
	
	public static final Finder<Long, Coupon> find = new Finder<>(Coupon.class);
}
