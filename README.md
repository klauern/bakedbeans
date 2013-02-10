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
