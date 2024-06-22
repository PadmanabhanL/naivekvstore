package com.sample.keyvaluestore.service;

import com.sample.keyvaluestore.bo.KeyValueStore;
import com.sample.keyvaluestore.dao.KeyValueStoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KeyValueStoreService implements CrudService<KeyValueStore, String> {

    private final KeyValueStoreRepository keyValueStoreDao;

    public KeyValueStoreService(KeyValueStoreRepository keyValueStoreDao) {
        this.keyValueStoreDao = keyValueStoreDao;
    }

    @Override
    public List<KeyValueStore> findAll() {
        List<KeyValueStore> entries = new ArrayList<>();
        keyValueStoreDao.findAll().forEach(entries::add);
        return entries;
    }

    @Override
    public KeyValueStore findById(String key) {
        Optional<KeyValueStore> optionalEntry = keyValueStoreDao.findById(key);
        if (optionalEntry.isEmpty()) {
            throw new RuntimeException("Item Not Found");
        }
        return optionalEntry.get();
    }

    @Override
    public void save(KeyValueStore keyValueStore) {
        keyValueStoreDao.save(keyValueStore);
    }

    @Override
    public void delete(String key) {
        Optional<KeyValueStore> entry = keyValueStoreDao.findById(key);
        if (entry.isEmpty()) {
            return;
        }

        entry.get().setExpiresAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        keyValueStoreDao.save(entry.get());
    }

    @Override
    public void cleanupEntries() {
        List<KeyValueStore> itemsToBeDeleted = keyValueStoreDao.findExpiredKeys();
        keyValueStoreDao.deleteAll(itemsToBeDeleted);
    }
}
