package bakedbeans.builder.test;


import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import org.junit.Test;

import bakedbeans.core.ConfigFile;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class ConfigFileAnnotationTest {
	
	private static final String BAKED_CONFIG = "bakedbeans.example.BakedConfiguration";

	@Test
	public void shouldFindAllClassesWithConfigFileAnnotation() throws IOException, ClassNotFoundException {
		ClassPath cp = ClassPath.from(ClassLoader.getSystemClassLoader());
		ImmutableSet<ClassInfo> klasses = cp.getTopLevelClasses();
		List<String> annotated_classes = Lists.newArrayList();
		
		for (ClassInfo klass : klasses) {
			
			Annotation[] annotations = klass.getClass().getAnnotations();
			if (klass.getName().equals(BAKED_CONFIG)) {
				Annotation[] anns = Class.forName(BAKED_CONFIG).getAnnotations();
				StringBuffer buf = new StringBuffer();
				for (Annotation ann : anns) {
					buf.append(ann.toString());
				}
				System.out.println(buf.toString());
				assertThat(anns.length).isGreaterThan(0);
			}
			
			for (Annotation annotation : annotations) {
				System.out.println("annotation is : " + annotation.annotationType());
				if (annotation.annotationType().equals(ConfigFile.class)) {
					annotated_classes.add(klass.getName());
					break;
				}
			}
		}
		
		System.out.println(annotated_classes.toString());
		
		assertThat(annotated_classes).contains("bakedbeans.example.BakedConfiguration");
	}

}
