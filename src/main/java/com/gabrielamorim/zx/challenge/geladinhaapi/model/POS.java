package com.gabrielamorim.zx.challenge.geladinhaapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gabrielamorim.zx.challenge.geladinhaapi.serialize.GeoJsonMultiPolygonDeserializer;
import com.gabrielamorim.zx.challenge.geladinhaapi.serialize.GeoJsonPointDeserializer;

/**
 * POS (Point of Sale) entity.
 *
 * @author Gabriel Amorim
 */
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "tradingName", "ownerName", "document", "coverageArea", "address" })
public class POS {

	@Id
	private Integer id;
	private String tradingName;
	private String ownerName;
	
	@Indexed(unique = true)
	private String document;
	
	@GeoSpatialIndexed(name = "pos_coverageArea_index", type = GeoSpatialIndexType.GEO_2DSPHERE) //index to coverage area queries
	@JsonDeserialize(using = GeoJsonMultiPolygonDeserializer.class) //deserializer to convert the JSON coverageArea entry to GeoJsonMultiPolygon
	private GeoJsonMultiPolygon coverageArea;
	
	@GeoSpatialIndexed(name = "pos_address_index", type = GeoSpatialIndexType.GEO_2DSPHERE) //index to location near queries
	@JsonDeserialize(using = GeoJsonPointDeserializer.class) //deserializer to convert the JSON address entry to GeoJsonPoint
	private GeoJsonPoint address = null;

	public GeoJsonPoint getAddress() {
		return address;
	}

	public void setAddress(GeoJsonPoint address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public GeoJsonMultiPolygon getCoverageArea() {
		return coverageArea;
	}

	public void setCoverageArea(GeoJsonMultiPolygon coverageArea) {
		this.coverageArea = coverageArea;
	}
}