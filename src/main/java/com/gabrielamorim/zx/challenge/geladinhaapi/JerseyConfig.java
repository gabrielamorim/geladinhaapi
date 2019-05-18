package com.gabrielamorim.zx.challenge.geladinhaapi;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.gabrielamorim.zx.challenge.geladinhaapi.endpoint.CreateEndpoint;
import com.gabrielamorim.zx.challenge.geladinhaapi.endpoint.SearchEndpoint;

/**
 * Jersey configuration class to configure
 * all REST aspects in this application. 
 * 
 * Here you can register endpoints, filters, 
 * listeners and also change Jersey's default behavior 
 * with custom config.
 * 
 * @author Gabriel Amorim
 */
@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		registerEndpoints();
	}

	private void registerEndpoints() {
		register(CreateEndpoint.class);
		register(SearchEndpoint.class);
	}
}
