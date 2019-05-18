package com.gabrielamorim.zx.challenge.geladinhaapi.serialize;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Deserializer to parse specific JSON entries 
 * that matches with a multipolygon to @GeoJsonMultiPolygon.
 *
 * @author Gabriel Amorim
 */
public class GeoJsonMultiPolygonDeserializer extends StdDeserializer<GeoJsonMultiPolygon> {

	private static final long serialVersionUID = -4231551316663591762L;

	public GeoJsonMultiPolygonDeserializer() {
		this(null);
	}

	public GeoJsonMultiPolygonDeserializer(Class<?> valueType) {
		super(valueType);
	}

	/**
     * Deserializes multipolygon coordinates JSON arrays 
     * into a new @GeoJsonMultiPolygon object.
     */
	@Override
	public GeoJsonMultiPolygon deserialize(JsonParser parser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		JsonNode node = parser.getCodec().readTree(parser);
		
		JsonNode coordinates = node.get("coordinates");
		
		return createMultiPolygon(coordinates);
	}
	
	/**
     * Creates a new @GeoJsonMultiPolygon object 
     * containing an array of @GeoJsonPolygon objects
     *
     * @param coordinates array containing the coordinates for the @GeoJsonMultiPolygon
     * @return @GeoJsonMultiPolygon object
     */
    private GeoJsonMultiPolygon createMultiPolygon(JsonNode coordinates) {
    	
    	ArrayList<GeoJsonPolygon> geoJsonPolygons = new ArrayList<>();
    	 
    	//MultiPolygon
    	if (coordinates.isArray()) {
    		
    		for (JsonNode polygon : coordinates) {
    			
    			//Polygon    			
    			if (polygon.isArray()) {
    				
    				for (JsonNode points : polygon) {
    					
    					//Points
    					if (points.isArray()) {
    						
    						//Each point
    						ArrayList<Point> pointsList = new ArrayList<Point>();
    						
    	    				for (JsonNode eachPoint : points) {
    	    					double x = eachPoint.get(0).asDouble();
    	    					double y = eachPoint.get(1).asDouble();
    	    		
    	    					pointsList.add(new Point(x, y));
    	    				}
    	        		
    	    				geoJsonPolygons.add(new GeoJsonPolygon(pointsList));	
    					}
    				}
    			}
    		}
    	}

        return new GeoJsonMultiPolygon(geoJsonPolygons);
    }
}
