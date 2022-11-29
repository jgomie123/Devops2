package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import repository.PolkamanRepository;
import repository.PolkamanRepositoryImpl;
import service.PolkamanService;

/*This is the class we'll be using to configure our AnnotationContext, menaing we'll use this class
 * to add SPring Beans to our IOC container. This would be a replacement for an XML file
 * 
 * If you wan tot use a class to configure your context (to create Spring beans and add them to the 
 * IOC Container), we need to use an annotation: @Configuration
 * */

//@Configuration is the annotation that we will use to create and manage our beans
@Configuration
public class AppConfig {
	
	//<Bean> : this would be for an XML file
	@Bean(name = "polkamanService")
	public PolkamanService getPolkamanService() {
		/*Typically you don't add these kinds of beans to our IOC container in this way
		 * TYpically you would only add beans in this way that would need a lot of custom configuration
		 * */
		return new PolkamanService();
	}
	
	@Bean(name = "polkamanRepository")
	public PolkamanRepository getPolkamanRepository() {
		return new PolkamanRepositoryImpl();
	}
	
	//I told you before that normally we use more custom type beans
	//note you can have multiple classes that are @configuration,
	//that time has come
	//you actually don't want to mix your spring ORM and spring web
	//beans defined in the same place, just for you it will be 
	//difficult to manage them, but I will keep them together for now
	//recall that w can create methods annotated with @Bean in a config class
	 //to add beans to our IOC container. Also remember that we said it is commong
	//to use this approach when beans require heavey customization before they are added
	//to the computer
	//what kind of bean will i need to manage my creds
	//we'll start with a bean to manage our DB creds. Most things in SPring
	//end up becoming beans
	
	//it's a representation of your data source (DB)
	//the datasource comes frmo java.sql, and Spring provides an implementation of it
	//since DataSource is just an interface
	@Bean
	public DataSource dataSource() {
		//DataSource comes from java.sql
		//the drivermanagerdatasource is a Spring-specific implementatino of the datasource
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//here we will need to do custom configuration of our bean
		//need to specify the url, username, password as a bean
		dataSource.setDriverClassName("org.postgresql.Driver"); //this is our driver class name if using postgres
		dataSource.setUrl(System.getenv("db_url"));
		dataSource.setUsername(System.getenv("db_username"));
		dataSource.setPassword(System.getenv("db_password"));
		//and this is our dataSource bean
		//I don't have to worry about making my own connection utility - just make the bean
		//and that's all we really need to set - this takes care of spring connecting to our DB
		//this is our bean
		return dataSource;
	}
	
	//let's make a second bean in order to remove our boiler plate jdbc frmo our repo
	//so we will define a JDBCTemplate bean. It's a type. JDBCTemplate abstracts away the processof
	//connecting to the DB, creating Statement objects and resultset objects, and closing our connection
	//as it wraps around our DataSource bean and handles those tasks for us
	//this is technically 
	
//	@Bean
//	public JdbcTemplate jdbcTemplate() {
//		return new JdbcTemplate(dataSource());
//	}
	
	/*Right now we're using SPring JDBC which provides decent abstraction over our standard boiler plate JDBC code
	 * but there are some things it hasn't done for us that can be done, and there are still some problems that spring jdbc can't
	 * address. one of the big ones it that, as devs, 
	 * */
	
	//we need an entitymanager - aptly named, as an entity managed by hibernate, so we will have an entitymamanger bean 
	//that will wrap around hibernate and manage hibernate - so this bean will be a representation of Hibernate
	/*In order tot ake advantatge of spring ORM's Hibernate integration, we will create an entityManagerFactoryBean that is 
	 * actually just an abstraction over hibernate
	 * */
	
	//gives youa ccess tot he underlying dataSource
	@Bean
	public LocalContainerEntityManagerFactoryBean  entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		//so what am I configuring - need to pass the datasource to Hibernate
		//no longer using JDBC anymore will be using ORM framework
		em.setDataSource(dataSource()); //just calling the method that returns the dataSource bean
		/*the entity manager needs to know which packages to scan for entityes (anything annotated with @entity)
		 * */
		
		em.setPackagesToScan("model");
		//now I need to tell spring orm what my jpa provider is - my jpa provider is Hibernate 
		//there are others but we chose Hibernate
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		//set some Hibernate properties
		Properties hibernateProps = new Properties();

		hibernateProps.put("hibernate.show_sql", "true"); //shows the sql queries that hibernate writes for you
		//^not needed but nice to have
		hibernateProps.put("hibernate.hbm2ddl.auto", "validate");
		//^this is best practice - trying to match java classes up with what exists in your DB
		
		//next we need to set the jpa properties by passing in the hibernate properties
		em.setJpaProperties(hibernateProps);
		
		return em;
	}
	
	//one more bean we need to create
	
	
	
}
