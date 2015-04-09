package com.texit.clinicManager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.texti.clinicManager.core.Repository;

@Configuration
@ComponentScan(basePackageClasses = Repository.class)
public class RepositoryConfig {
	
}
