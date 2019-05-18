package com.gabrielamorim.zx.challenge.geladinhaapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.gabrielamorim.zx.challenge.geladinhaapi.model.POS;

/**
 * POSRepository to CRUD operations on @POS collection. 
 * Mongo specific {@link org.springframework.data.repository.Repository} interface.
 *
 * @author Gabriel Amorim
 */
public interface POSRepository extends MongoRepository<POS, Long> {		
}
