package com.gabrielamorim.zx.challenge.geladinhaapi.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gabrielamorim.zx.challenge.geladinhaapi.model.POS;
import com.gabrielamorim.zx.challenge.geladinhaapi.repository.POSRepository;

/**
 * CreateEndpoint to expose create 
 * operations on geladinhaapi data.
 *
 * @author Gabriel Amorim
 */
@Component
@Path("/create")
public class CreateEndpoint {

	@Autowired
	private POSRepository pdvRepository;
		
	/**
	 * Expose this method as a POST request
	 * to create a @POS entity.
	 * 
	 * @param @POS
	 * @return @POS object saved on database
	 */
	@POST
	@Path("/pos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public POS createPos(POS pos) {
		return pdvRepository.save(pos);
	}
}