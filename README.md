Baked Beans
===========

Bean Deserialization in Java using Jackson parsing, with a flexible set of options for parsing:

  - YAML
  - XML
  - JSON

Consider this a configuration-file mapping to multiple POJOs.


Why?
----
When I worked with a recent Dropwizard project, I thought the concept of storing
your configuration properties in POJOs with a large YAML file seemed incredibly
useful to me.  In the past, I've often read from a series of `.properties` files
with lots of constants:

```java
public class Something {

  public static final String PROPERTY_TO_READ = "my.property";
  public static final String AGE_PROPERTY = "someone.age";
  public static final String PROPERTY_FILE_LOC = "props/location.properties";
  ...
  
  public void something() {
    Properties props = new Properties();
    props.load(Something.class.getResourceAsStream(PROPERTY_FILE_LOC));
    String p = props.getProperty(PROPERTY_TO_READ);
    int age = Integer.parseInt(props.getProperty(AGE_PROPERTY));
    ...
    // etc., forever and ever with a lot of ridiculousness
```

With this library, I wanted to simplify it, allowing a user to have some
semblance of ease.

How to use
----------

To use this library, we're going to do 3 things (sorry):

  1. Create your list of simple POJO classes you plan on mapping to
  2. Create your configuration class to define what gets wired up where
  3. call `BakedBeanRegistry#mapBakedBeans` to create a `Map<Class,Configuration>`
     so you can reference each POJO configuration individually.

So, assuming you have a POJO like so:

```java
public class PersonDetails {
    private String first;
    private String last;
    private String description;

    // define getters and setters.
    public String getFirst() { return first; }
    public void setFirst(String first) { this.first = first; }
    public String getLast() { return last; }
    public void setLast(String last) { this.last = last; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
```

You can create a YAML file like this:

```yaml
nick:
    description: An Amazing Guy

    first: Nick
    last: Klauer
```

And then wire it in to a configuration class like so:

```java
@ConfigFile(location = "yaml/nick.yaml")
public class SimpleBakedConfiguration implements Configuration {

    @JsonProperty
    PersonDetails nick = new PersonDetails();

    public PersonDetails getNick() {
        return nick;
    }
}
```

And finally, you can use a `BeanRegistry` instance to pull out these beans:

```java
BeanRegistry registry = new BeanRegistry(); // can also pass the package-scope to the constructor
Map<Class, Configuration> configs = registry.mapBakedBeans();
SimpleBakedConfiguration c = configs.get(SimpleBakedConfiguration.class);

c.getNick().getFirst(); // returns "Nick"
```

Is this really easier?
----------------------
I don't know, but it fleshed out an idea I had about using `@Annotation`'s to
try to configure a class instance.


TODO
----

There's things I'd like to try out to make simpler and provide some larger value
than just marshalling to a Java bean instance:
 - Validation of beans (javax.validation.*, @Valid, @NotNull, etc.)


Inspiration
-----------
It should be similar in appearance to Dropwizard, which is where the initial
inspiration was formed.  I don't write just RESTful apps, but I do have to
use alot of configuration files, so the approach works well across other domains.


License
-------

Meh?  Some sort of open source thing, but it's so simple I could care less if you
outright steal it and claim it as your own, since I'm borrowing a lot of this
from others.
