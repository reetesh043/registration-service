package com.gamesys.registration.cache;

import com.gamesys.registration.service.BlacklistedIINService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import static com.gamesys.registration.RegistrationServiceConstants.BLACKLISTED_IIN_CACHE;
import static com.gamesys.registration.RegistrationServiceConstants.BLACKLISTED_IIN_CACHE_KEY;



/**
 * Cache to hold the blacklisted payment issuer identification numbers at application start up.
 */

@Component
@EnableAsync
@Order(1)
public class BlacklistedIINCache {

    private static final Logger logger = LoggerFactory.getLogger(BlacklistedIINCache.class);

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private BlacklistedIINService blacklistedIINService;

    /**
     * Loads blacklisted iin  data to cache.
     * This method gets executed when application bootstraps and also on a scheduled basis.
     * Scheduler can be customized based on requirement.
     */

    @Scheduled(cron = "${iin.cache.refresh}")
    @EventListener(ApplicationReadyEvent.class)
    public void loadIINData() {
        logger.info("Populating  blacklisted payment issuer identification numbers to cache on application start up");

        Cache cache = cacheManager.getCache(BLACKLISTED_IIN_CACHE);
        cache.put(BLACKLISTED_IIN_CACHE_KEY, blacklistedIINService.getBlackListedIIN());

        logger.info("Blacklisted payment issuer identification numbers added to cache successfully");
    }
}
