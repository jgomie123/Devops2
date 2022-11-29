package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import model.Polkaman;

public class PolkamanRepositoryImpl implements PolkamanRepository {
	
	/*we can use our jdbc templeate bean here by autowiring it in:
	 * I am commenting this out as we are no longer using Spring jdbc, we're using Spring ORM (abstraction
	 * on top of abstraction)
	 * */
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	/*For using Spring ORM you need to include an EntityManager. 
	 * our entitymanager factory will return an entity manager to us
	 * a persistence context is a set of entity instances - we ahve one entity instance in this
	 * project, which is Polkaman 
	 * */
	
	//the persistence context was defined in our factory bean method
	//the persistence context is wiring in a dependency, but it's specific to spring ORM
	@PersistenceContext(type = PersistenceContextType.EXTENDED) //persist doesn't have immediately - will block other requests
	private EntityManager entityManager;

	public List<Polkaman> findAll() {
		// TODO Auto-generated method stub
		
		//rowMapper will take all the rows and map  it to object type of choice
		//return this.jdbcTemplate.query("select * from polkamans", new DataClassRowMapper<Polkaman>(Polkaman.class));
		
		//what you pass in is not SQL - Hibernate comes with it's own query language called
		//HQL hibernate query lanague: it's specific to Hibernate and allows you to focus
		//your queries on the Java classes rather than on the tables in the DB
		//so you're operating in a very object oriented fashion
		return this.entityManager.createQuery("FROM Polkaman", Polkaman.class).getResultList();
		//returns a list of type Polkaman.class
	}

//	public void save(Polkaman polkaman) {
//		// TODO Auto-generated method stub
//		//this is all that's needed
//		//same as we did with JDBC but less typing - no handling exceptions, 
//		//no writing our own whetever
//		//DON"T SHOW THIS ONE ONLY DO FINDALL
////		this.jdbcTemplate.update("insert into polkamans values(default, ?, ?, ?)", 
////				polkaman.getPolka_name(), polkaman.getPolka_type(), polkaman.getPolka_trainer());
//		System.out.println("I am here");
//		this.entityManager.persist(polkaman);
//	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
