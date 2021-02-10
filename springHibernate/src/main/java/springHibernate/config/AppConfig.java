package springHibernate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement 
// enables Spring’s annotation-driven transaction management capability
public class AppConfig {

	@Autowired
	Environment environment;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"springHibernate.entity"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;

	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;

	}

	private Properties hibernateProperties() {

		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}

	/*
	 * Binds a Hibernate Session from the specified factory to the thread . so when
	 * we call SessionFactory.getCurrentSession() which will return the session that
	 * is bound to the currently running thread.
	 */
	@Bean
	public HibernateTransactionManager hibernateTransactionManager() {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;

	}

	/*
	 * openSession() will return a new session object on every call, which is
	 * actually a instance of org.hibernate.impl.SessionImpl.We can use this method
	 * when we decided to manage the Session our self. It does not try to store or
	 * pull the session from the current context. If we use this method, we need to
	 * flush() and close() the session. It does not flush and close() automatically.
	 * 
	 * getCurrentSession():
	 * 
	 * Session getCurrentSession() throws HibernateException
	 * 
	 * Obtains the current session. The "current session" refers to a Hibernate
	 * Session bound by Hibernate behind the scenes, to the transaction scope. A
	 * session is opened whenever getCurrentSession() is called for the first time
	 * and closed when the transaction ends. This creates a brand new session if one
	 * does not exist or uses an existing one if one already exists. It
	 * automatically configured with both auto-flush and auto-close attributes as
	 * true means Session will be automatically flushed and closed.
	 * 
	 */

}
