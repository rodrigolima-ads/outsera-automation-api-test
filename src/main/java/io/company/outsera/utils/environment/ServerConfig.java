package io.company.outsera.utils.environment;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/resources/${env}.properties"})
public interface ServerConfig extends Config {
    @Key("services.petstore.uri")
    String baseuripetstore();
    @Key("base.path")
    String basepath();
    @Key("user.cadastrado")
    String userDefault();


}
