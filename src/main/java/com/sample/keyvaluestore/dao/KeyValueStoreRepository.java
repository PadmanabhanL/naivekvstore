package com.sample.keyvaluestore.dao;

import com.sample.keyvaluestore.bo.KeyValueStore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeyValueStoreRepository extends CrudRepository<KeyValueStore, String> {

    @Query(nativeQuery = true, value = "select * from Key_Value_Store kvs where kvs.expires_At < CURRENT_TIMESTAMP LIMIT 1000")
    List<KeyValueStore> findExpiredKeys();
}
