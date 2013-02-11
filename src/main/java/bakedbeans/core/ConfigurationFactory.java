package bakedbeans.core;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationFactory<T> {

    private ObjectMapper mapper;
    private final Class<T> klass;
    List<T> configurations;

    public ConfigurationFactory(ObjectMapper mapper, List<T> configs,
	    Class<T> klass) {
	this.mapper = mapper;
	this.configurations = configs;
	this.klass = klass;
    }

    public List<T> mapBeans(File source) throws JsonParseException,
	    JsonMappingException, IOException {
	for (T conf : configurations) {
	    conf = mapper.readValue(source, klass);
	}
	return configurations;
    }

}
