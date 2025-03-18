package io.company.outsera.utils.environment;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigCache;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationManager {
    private static final String AMBIENTE = "hom";
    public static ServerConfig config() {
        String argument = System.getProperty("env");
        String envVar = System.getenv("env");

        System.setProperty("env", Stream.of(argument,envVar)
                .filter(StringUtils::isNotBlank)
                .findAny()
                .orElse(AMBIENTE));

        return ConfigCache.getOrCreate(ServerConfig.class);
    }


}
