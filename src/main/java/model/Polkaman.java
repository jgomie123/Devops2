package model;

import javax.persistence.Column;
//we need this import for Entity - note that our annotations are imported frmo the Java
//Persistence API (JPA). Hibernate follows the JPA standard, and is known as a JPA provider
//please understand that the JPA just provides an interface for JPA vendors (like HIbernate)
//if want to build an ORM framework for Java
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*Spring ORM wraps around Hibernate (our ORM framework) in order to abstract SQL from us entirely
 * to make this happen, we need to annotate our models so that Hibernate knows which tables to create and 
 * which columns should exist on those tables, and the framework will also match the datatypes for us
 * */
@Entity //denotes that this class is an entity that should be managed by Hibernate
@Table(name = "polkamans") //this denotes that this represents a table in our DB
public class Polkaman {
	@Id //denotes that a specific column is the primary key for this table
	@Column(name = "id") //dneotes that this field should correspond to a column in a DB
	private int id;
	@Column(name = "polka_name")
	private String polka_name;
	@Column(name = "polka_type")
	private String polka_type;
	@Column(name = "polka_trainer")
	private String polka_trainer;
	
	
	public Polkaman() {
		
	}
	
	public Polkaman(String polka_name, String polka_type, String polka_trainer) {
		super();
		this.polka_name = polka_name;
		this.polka_type = polka_type;
		this.polka_trainer = polka_trainer;
	}
	
	public Polkaman(int id, String polka_name, String polka_type, String polka_trainer) {
		super();
		this.id = id;
		this.polka_name = polka_name;
		this.polka_type = polka_type;
		this.polka_trainer = polka_trainer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolka_name() {
		return polka_name;
	}

	public void setPolka_name(String polka_name) {
		this.polka_name = polka_name;
	}

	public String getPolka_type() {
		return polka_type;
	}

	public void setPolka_type(String polka_type) {
		this.polka_type = polka_type;
	}

	public String getPolka_trainer() {
		return polka_trainer;
	}

	public void setPolka_trainer(String polka_trainer) {
		this.polka_trainer = polka_trainer;
	}

	@Override
	public String toString() {
		return "Polkaman [id=" + id + ", polka_name=" + polka_name + ", polka_type=" + polka_type + ", polka_trainer=" + polka_trainer + "]";
	}
		
}
