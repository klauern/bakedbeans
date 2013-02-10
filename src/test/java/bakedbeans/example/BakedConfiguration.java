package bakedbeans.example;

import bakedbeans.core.ConfigFile;

import com.fasterxml.jackson.annotation.JsonProperty;

@ConfigFile(location = "yaml/example.yaml")
public class BakedConfiguration {
	
	
	@JsonProperty
	PersonDetails nick = new PersonDetails();

	
	public PersonDetails getNick() {
		return nick;
	}
}
