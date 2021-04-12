package com.gamesys.registration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import static com.gamesys.registration.RegistrationServiceConstants.BLACKLISTED_IIN_CACHE;
import static java.util.stream.IntStream.range;

/*
 * Service class to populate blacklisted payment issuer identification numbers.
 */

@Service
public class BlacklistedIINService {

    private static final Logger logger = LoggerFactory.getLogger(BlacklistedIINService.class);

    public static final int BLACKLISTED_IIN_RANGE_START = 100000;
    public static final int BLACKLISTED_IIN_RANGE_END = 100010;

    @Cacheable(value = BLACKLISTED_IIN_CACHE)
    public List<Integer> getBlackListedIIN() {
        logger.info("populate  blacklisted payment issuer identification numbers");

        //TODO: Populate from DB or from actual service which provides the issuer identification numbers
        // Currently generated random 6 digit numbers to test the functionality

        return range(BLACKLISTED_IIN_RANGE_START, BLACKLISTED_IIN_RANGE_END)
                .boxed()
                .collect(Collectors.toList());
    }
}
