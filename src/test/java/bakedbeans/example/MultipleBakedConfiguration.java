package bakedbeans.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import bakedbeans.core.ConfigFile;
import bakedbeans.core.Configuration;

@ConfigFile(location = "yaml/multi.yaml")
public class MultipleBakedConfiguration implements Configuration {

    @JsonProperty
    public PersonDetails nick = new PersonDetails();

    @JsonProperty
    public BirthdayDetails robert = new BirthdayDetails();

    @JsonProperty
    public PersonDetails edward = new PersonDetails();

    public PersonDetails getNick() {
	return nick;
    }

    public BirthdayDetails getRobert() {
	return robert;
    }

    public PersonDetails getEdward() {
	return edward;
    }
}
