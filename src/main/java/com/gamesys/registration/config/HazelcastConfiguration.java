package com.gamesys.registration.config;


import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.Config;
import com.hazelcast.config.CacheSimpleConfig;
import com.hazelcast.config.MaxSizePolicy;
import com.hazelcast.config.MulticastConfig;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.config.NetworkConfig;
import static com.gamesys.registration.RegistrationServiceConstants.BLACKLISTED_IIN_CACHE;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Hazelcast caching configuration.
 */

@Configuration
public class HazelcastConfiguration {

    @Value("${cacheTimeToLiveInSeconds}")
    private String cacheTimeToLiveInSeconds;

    @Value("${maxEntriesInCache}")
    private String maxEntriesInCache;

    @Bean
    public Config hazelCastConfig() {

        return new Config()
                .setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig()
                                .setName(BLACKLISTED_IIN_CACHE)
                                .setEvictionConfig(new EvictionConfig().setEvictionPolicy(EvictionPolicy.LRU).setSize((Integer.valueOf(maxEntriesInCache)))
                                        .setMaxSizePolicy(MaxSizePolicy.PER_NODE))
                                .setTimeToLiveSeconds(Integer.valueOf(cacheTimeToLiveInSeconds)))
                .addCacheConfig(new CacheSimpleConfig()
                        .setName(BLACKLISTED_IIN_CACHE)
                        .setStatisticsEnabled(true)
                        .setManagementEnabled(true))
                .setNetworkConfig(new NetworkConfig()
                        .setJoin(new JoinConfig()
                                .setTcpIpConfig(
                                        new TcpIpConfig()
                                                .setEnabled(false))
                                .setMulticastConfig(
                                        new MulticastConfig()
                                                .setEnabled(false))));
    }
}

