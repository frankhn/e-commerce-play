package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.ebean.Finder;
import io.ebean.Model;

@Entity
@Table(name="followers")
public class Followers extends Model{
	
	@Id
	@GeneratedValue
	public Long id;
	
	@ManyToOne
	@JoinColumn(name = "following_user_id", referencedColumnName = "id")
	public Long user_id;
	
	@ManyToOne
	@JoinColumn(name="follong_business_id", referencedColumnName ="id")
	public Long business_id;
	
	public static final Finder<Long, Followers> find = new Finder<>(Followers.class);
}
