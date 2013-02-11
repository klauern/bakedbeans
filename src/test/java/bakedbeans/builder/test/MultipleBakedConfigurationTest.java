package bakedbeans.builder.test;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import bakedbeans.core.BeanRegistry;
import bakedbeans.core.Configuration;
import bakedbeans.example.MultipleBakedConfiguration;
import bakedbeans.example.PersonDetails;
import bakedbeans.example.SimpleBakedConfiguration;

public class MultipleBakedConfigurationTest {

    private BeanRegistry registry;

    @Before
    public void setUp() {
	registry = new BeanRegistry("bakedbeans.example");
    }

    @Test
    public void shouldMapMultipleElementsInAYAML() throws JsonParseException,
	    JsonMappingException, IOException {
	Map<Class, Configuration> configs = registry.mapBakedBeans();
	assertThat(configs).containsKey(SimpleBakedConfiguration.class);
	assertThat(configs).containsKey(MultipleBakedConfiguration.class);
    }

    @Test
    public void shouldCreateEdwardAndNick() throws JsonParseException,
	    JsonMappingException, IOException {
	Map<Class, Configuration> configs = registry.mapBakedBeans();
	MultipleBakedConfiguration ed_and_nick = (MultipleBakedConfiguration) configs
		.get(MultipleBakedConfiguration.class);

	PersonDetails ed = ed_and_nick.getEdward();
	PersonDetails nick = ed_and_nick.getNick();

	assertThat(ed.getFirst()).isEqualTo("Edward");
	assertThat(ed.getLast()).isEqualTo("Cullen");

	assertThat(nick.getFirst()).isEqualTo("Nick");
	assertThat(nick.getLast()).isEqualTo("Klauer");
    }

}
