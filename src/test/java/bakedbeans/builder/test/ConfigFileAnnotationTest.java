package bakedbeans.builder.test;


import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;

import bakedbeans.core.ConfigFile;
import bakedbeans.example.BakedConfiguration;

public class ConfigFileAnnotationTest {
	
	@Test
	public void shouldFindAllClassesWithConfigFileAnnotation() throws IOException, ClassNotFoundException {
		Reflections reflections = new Reflections("");
		Set<Class<?>> config_classes = reflections.getTypesAnnotatedWith(ConfigFile.class);
		
		assertThat(config_classes.size()).isGreaterThan(0);
		assertThat(config_classes).contains(BakedConfiguration.class);
	}

}
