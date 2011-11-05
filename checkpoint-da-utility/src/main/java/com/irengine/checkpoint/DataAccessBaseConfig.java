package com.irengine.checkpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataAccessBaseConfig {
	@Value("${jdbc.url}")
	String jdbcUrl;

	@Value("${jdbc.username}")
	String username;

	@Value("${jdbc.password}")
	String password;

	@Value("#{h2DataSource}")
	DataSource embeddedDataSource;

	@Bean
	public DataSource dataSource() {
		return embeddedDataSource;
	}

	// Remote Data Source ( just for illustrating remote connection )
	@Bean
	public DataSource remoteDataSource() {
		return new DriverManagerDataSource(jdbcUrl, username, password);
	}
}