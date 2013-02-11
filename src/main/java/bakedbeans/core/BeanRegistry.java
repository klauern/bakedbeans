package bakedbeans.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

public class BeanRegistry {

	private Reflections reflections;
	private Set<Class<?>> configured_classes;
	private final ObjectMapper yaml_mapper = new ObjectMapper(new YAMLFactory());
	
	public BeanRegistry() {
		reflections = new Reflections("");
		yaml_mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public BeanRegistry(String package_scope) {
		reflections = new Reflections(package_scope);
		yaml_mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public <T extends Configuration> Map<Class,T> mapBakedBeans() throws JsonParseException, JsonMappingException, IOException {
		this.configured_classes = reflections.getTypesAnnotatedWith(ConfigFile.class);
		Map<Class,T> configs = Maps.newHashMap();
		for (Class<?> configured_class : configured_classes) {
			ConfigFile cf = configured_class.getAnnotation(ConfigFile.class);
			if (cf.filetype().equals(ConfigType.YAML)) {
				
				configs.put(configured_class, (T) yaml_mapper.readValue(Resources.getResource(cf.location()), configured_class));
			}
		}
		return configs;
	}
	
}
