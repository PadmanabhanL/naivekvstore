package com.sample.keyvaluestore.scheduler;

import com.sample.keyvaluestore.service.KeyValueStoreService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class KVStoreCleanupScheduler {

    private final KeyValueStoreService kvStoreService;

    public KVStoreCleanupScheduler(KeyValueStoreService kvStoreService) {
        this.kvStoreService = kvStoreService;
    }

    @Scheduled(fixedRate = 15, timeUnit = TimeUnit.MINUTES)
    public void cleanupExpiredEntries() {
        kvStoreService.cleanupEntries();
    }
}
