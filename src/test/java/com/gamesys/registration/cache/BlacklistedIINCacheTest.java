package com.gamesys.registration.cache;


import com.gamesys.registration.service.BlacklistedIINService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import java.util.List;
import java.util.stream.Collectors;
import static com.gamesys.registration.RegistrationServiceConstants.BLACKLISTED_IIN_CACHE;
import static com.gamesys.registration.RegistrationServiceConstants.BLACKLISTED_IIN_CACHE_KEY;
import static java.util.stream.IntStream.range;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@DisplayName("Blacklisted IIN Cache Test")
public class BlacklistedIINCacheTest {


    @Mock
    private CacheManager cacheManager;

    @Mock
    private BlacklistedIINService blacklistedIINService;

    @Mock
    private Cache cache;

    @InjectMocks
    private BlacklistedIINCache serviceUnderTest;

    @Test
    @DisplayName("Test caching of  blacklisted payment iin")
    public void test_Load_Cache() {
        List<Integer> expectedList = range(100000, 100010).boxed().collect(Collectors.toList());
        when(cacheManager.getCache(eq(BLACKLISTED_IIN_CACHE))).thenReturn(cache);
        when(blacklistedIINService.getBlackListedIIN()).thenReturn(expectedList);
        serviceUnderTest.loadIINData();
        verify(cacheManager, times(1)).getCache(eq(BLACKLISTED_IIN_CACHE));
        verify(cache, times(1)).put(eq(BLACKLISTED_IIN_CACHE_KEY), eq(expectedList));

    }


}
