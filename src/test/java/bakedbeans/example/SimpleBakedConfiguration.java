package bakedbeans.example;

import bakedbeans.core.ConfigFile;
import bakedbeans.core.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

@ConfigFile(location = "yaml/nick.yaml")
public class SimpleBakedConfiguration implements Configuration {

    @JsonProperty
    PersonDetails nick = new PersonDetails();

    public PersonDetails getNick() {
	return nick;
    }
}
