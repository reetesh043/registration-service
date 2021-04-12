package com.gamesys.registration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DisplayName("Blacklisted IIN Service Test")
public class BlacklistedIINServiceTest {

    private BlacklistedIINService serviceUnderTest;

    @BeforeEach
    private void setUp(){
        serviceUnderTest = new BlacklistedIINService();
    }

    @Test
    @DisplayName("Test the blacklisted identification number")
    public void test_blacklist_IIN(){
        List<Integer> expectedList = range(100000, 100010).boxed().collect(Collectors.toList());
        List<Integer> actualList = serviceUnderTest.getBlackListedIIN();
        assertTrue(actualList.equals(expectedList));
    }
}
