package bakedbeans.builder.test;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import bakedbeans.core.BeanRegistry;
import bakedbeans.core.ConfigFile;
import bakedbeans.core.Configuration;
import bakedbeans.example.SimpleBakedConfiguration;

public class ConfigFileAnnotationTest {

    @Test
    public void shouldFindAllClassesWithConfigFileAnnotation()
	    throws IOException, ClassNotFoundException {
	Reflections reflections = new Reflections("");
	Set<Class<?>> config_classes = reflections
		.getTypesAnnotatedWith(ConfigFile.class);

	assertThat(config_classes.size()).isGreaterThan(0);
	assertThat(config_classes).contains(SimpleBakedConfiguration.class);
    }

    @Test
    public void shouldCreateMapOfConfigurations() throws JsonParseException,
	    JsonMappingException, IOException {
	BeanRegistry registry = new BeanRegistry();
	Map<Class, Configuration> configs = registry.mapBakedBeans();
	assertThat(configs).containsKey(SimpleBakedConfiguration.class);
	SimpleBakedConfiguration baked = (SimpleBakedConfiguration) configs
		.get(SimpleBakedConfiguration.class);
	assertThat(baked.getNick().getFirst()).isEqualTo("Nick");
	assertThat(baked.getNick().getLast()).isEqualTo("Klauer");
	assertThat(baked.getNick().getDescription())
		.isEqualTo("An Amazing Guy");

    }

}
