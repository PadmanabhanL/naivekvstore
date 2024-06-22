package com.sample.keyvaluestore.controller;

import com.sample.keyvaluestore.bo.KeyValueStore;
import com.sample.keyvaluestore.service.KeyValueStoreService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/keyValues")
public class KeyValueStoreController {

    private final KeyValueStoreService service;

    public KeyValueStoreController(KeyValueStoreService service) {
        this.service = service;
    }

    @GetMapping(path = "/{key}")
    public ResponseEntity<Map<String, Object>> loadByKey(@PathVariable(name = "key") String key) {
        KeyValueStore valueByKey = service.findById(key);
        return ResponseEntity.ok(Map.of("status",
                                        "SUCCESS",
                                        "key",
                                        valueByKey.getKeyString(),
                                        "value",
                                        valueByKey.getValue(),
                                        "expiresAt",
                                        valueByKey.getExpiresAt()));

    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> addKey(@RequestBody Map<String, Object> payload) {
        int ttlMinutes = Integer.parseInt((String) payload.get("ttl"));
        LocalDateTime expiryTime = LocalDateTime.now().plus(ttlMinutes, ChronoUnit.MINUTES);
        KeyValueStore entry = new KeyValueStore((String) payload.get("keyString"),
                                                (String) payload.get("value"), java.sql.Timestamp.valueOf(expiryTime));
        service.save(entry);
        return ResponseEntity.ok(Map.of("status",
                                        "SUCCESS",
                                        "key",
                                        entry.getKeyString(),
                                        "expiresAt",
                                        entry.getExpiresAt()));
    }

    @DeleteMapping(path = "/{key}")
    public ResponseEntity<Map<String, Object>> deleteByKey(@PathVariable(name = "key") String key) {
        service.delete(key);
        return ResponseEntity.ok(Map.of("status", "SUCCESS"));
    }

}
