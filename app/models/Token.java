package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.ebean.Model;

@Entity
@Table(name = "token")
public class Token extends Model {

	@Id
	@GeneratedValue
	public Long id;
	
	@Column(unique=true)
	public String token;

	public String timestamp;

	public String status;
}
