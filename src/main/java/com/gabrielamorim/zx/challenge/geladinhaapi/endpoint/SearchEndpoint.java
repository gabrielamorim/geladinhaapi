package com.gabrielamorim.zx.challenge.geladinhaapi.endpoint;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.gabrielamorim.zx.challenge.geladinhaapi.model.POS;
import com.gabrielamorim.zx.challenge.geladinhaapi.repository.POSRepository;

/**
 * SearchEndpoint to expose search 
 * operations on geladinhaapi data.
 *
 * @author Gabriel Amorim
 */
@Component
@Path("/search")
public class SearchEndpoint {
	
	@Autowired
	private POSRepository pdvRepository;
	
	@Autowired 
	private MongoTemplate mongoTemplate;
	
	/**
	 * Expose this method as a GET request
	 * to return the @POS entity of a given id.
	 * 
	 * If the @POS entity does not exist
	 * return a 404 Not Found.
	 * 
	 * @param id
	 * @return @POS object that matches the id
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public POS findById(@PathParam("id") Long id) {
		return pdvRepository.findById(id).get();
	}
	
	/**
	 * Expose this method as a GET request
	 * to return the @POS entity closest to a given point
	 * and within the coverage area.
	 * 
	 * If the @POS does not match the distance 
	 * or coverage area returns an empty list.
	 * 
	 * @param @Point
	 * @return @POS object that matches the distance and coverage area criteria
	 */
	@GET
	@Path("/location")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<POS> findBylocation(@QueryParam("x") Double x, @QueryParam("y") Double y) {
		
		//max distance in meters, here 10km
		double maxDistance = 10000;
		
		List<POS> pdvs =
				mongoTemplate.find(
						new Query(
								Criteria.where("address").nearSphere(new GeoJsonPoint(x, y)).maxDistance(maxDistance)
								).addCriteria(
										Criteria.where("coverageArea").intersects(new GeoJsonPoint(x, y))
										).limit(1), POS.class);
		
		return pdvs;
	}
}
