package com.irengine.checkpoint.dao.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.irengine.checkpoint.DataAccessBaseConfig;

@Configuration
@ImportResource("classpath:META-INF/checkpoint-da-utility.xml")
public class JdbcConfig extends DataAccessBaseConfig {
	// JDBC Template
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
