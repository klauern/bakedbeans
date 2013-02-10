Baked Beans
===========



.... Configuration library leveraging alot of the setup used in DropWizard
to manage bean configuration through YAML, XML, and CSV files, tying in
directly to Java POJOs.


TODO
----

Lots of things about the architecture go here:

 - Validation of beans (javax.validation.*, @Valid, @NotNull, etc.)
 - Validator (following or borrowing heavliy from com.yammer.dropwizard.validation.Validator)

 - @Annotation - based configuration? (maybe not)

 - Simple configuration
   - register list of classes to populate with a configurator of some sort
   - configure the configurator class to use a list of YAML, XML and JSON files
     to process.  Read each into some intermediate state, using Jackson to
     do object mapping to the list of classes.


### What I'd like to borrow structurally from Dropwizard

I don't think I'd be outright stealing this idea, but I would like it to be similar
to the ApplicationConfiguration that you write before you add it to the registration

```java
public class ApplicationConfiguration extends Configuration {

    @Valid  // Validation isn't part of it yet, probably a later release
    @NotNull // see above
    @JsonProperty  // Jackson does the dirty work of mapping the bean
    private LdapConfiguration ldap = new LdapConfiguration();

    public LdapConfiguration getLdapConfiguration() {
        return ldap;
    }

}
```


Then you just add your configuration to a registry class of some sort.


### RegistryClass

The Registry class should be in charge of two things:

  1. being a container for registered configuration classes
  2. reading and processing the YAML, XML and JSON files to map to configuration


