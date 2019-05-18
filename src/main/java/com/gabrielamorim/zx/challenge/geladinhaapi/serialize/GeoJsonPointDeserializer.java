package com.gabrielamorim.zx.challenge.geladinhaapi.serialize;

import java.io.IOException;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Deserializer to parse specific JSON entries 
 * that matches with a point to @GeoJsonPoint.
 *
 * @author Gabriel Amorim
 */
public class GeoJsonPointDeserializer extends StdDeserializer<GeoJsonPoint> {

	private static final long serialVersionUID = -1731812515370919126L;
	
	public GeoJsonPointDeserializer() {
		this(null);
	}

	public GeoJsonPointDeserializer(Class<?> valueType) {
		super(valueType);
	}

	/**
     * Deserializes coordinates JSON array 
     * into a new @GeoJsonPoint object.
     */
	@Override
	public GeoJsonPoint deserialize(JsonParser parser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		JsonNode node = parser.getCodec().readTree(parser);
		
		JsonNode coordinates = node.get("coordinates");
		
		GeoJsonPoint geoJsonPoint = new GeoJsonPoint(coordinates.get(0).asDouble(), coordinates.get(1).asDouble());
		
		return geoJsonPoint;
	}

}
