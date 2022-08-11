package site.nomoreparties.stellarburgers;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:env.properties")
public interface EnvConfig extends Config {

    @Key("host")
    String getHost();

    @Key("browser")
    String getBrowser();

    @Key("api_base_path")
    String getBasePath();
}
