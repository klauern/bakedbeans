package bakedbeans.builder.test;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import bakedbeans.example.SimpleBakedConfiguration;
import bakedbeans.example.PersonDetails;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.io.Resources;

public class SimpleBakedConfigurationTest {

    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private static final String EXAMPLE_YAML = "yaml/nick.yaml";
    private static final String EXTRA_EXAMPLE_YAML = "yaml/extra-nick.yaml";

    @Test
    public void shouldMapPersonDetails() throws JsonParseException,
	    JsonMappingException, IOException {
	SimpleBakedConfiguration config = mapper.readValue(
		Resources.getResource(EXAMPLE_YAML),
		SimpleBakedConfiguration.class);
	PersonDetails person = config.getNick();
	assertThat(person.getFirst()).isEqualTo("Nick");
	assertThat(person.getDescription()).isEqualTo("An Amazing Guy");
	assertThat(person.getLast()).isEqualTo("Klauer");
    }

    @Ignore
    @Test
    public void shouldIgnoreExtraFields() throws JsonParseException,
	    JsonMappingException, IOException {
	SimpleBakedConfiguration config = mapper.readValue(
		Resources.getResource(EXTRA_EXAMPLE_YAML),
		SimpleBakedConfiguration.class);
	PersonDetails person = config.getNick();
    }

}