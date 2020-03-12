package com.daurada.db;


/**@Configuration
@ComponentScan("com.daurada")
@PropertySource("classpath:database.properties")
public class MySQLConfig {
	
	@Autowired
	private Environment environment;
	
	private static final String DRIVER = "driver";
	private static final String URL = "url";
	private static final String USER = "username";
	private static final String PASSWORD = "password";
	
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource manager = 
				new DriverManagerDataSource();
		
		manager.setDriverClassName(environment.getProperty(DRIVER));
		manager.setUrl(environment.getProperty(URL));
		manager.setUsername(environment.getProperty(USER));
		manager.setPassword(environment.getProperty(PASSWORD));

		return manager;
	}
	
}*/

