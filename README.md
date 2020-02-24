# Sample Jackson Modules
This is a repository holding some useful module(s) for Jackson Json Parsing

### IdDeserializerModule 
This is to handle the specific case of Mongo ``_id`` fields when our defined Entity objects have the field as ``id``
In the case where you have the objects in ``MongoDB directly replicated to an index in ``ElasticSearch``, instead of the expected field of ``id`` this will have the underscore
If using the ``MongoDB`` repository connection directly from java, this is not an issue.

This module lets the deserializer treat ``_id`` as ``id``.
Supporting both, manual registration of the module to a Jackson Parser, or letting the ``@JsonComponent`` automatically add it to a SpringBoot instantiated one

##### Sample Entity:
```Java
public class Sample {

    @JsonProperty("id")
    String id;

    @JsonProperty("key")
    String key;

    @JsonProperty("value")
    String value;

    @JsonProperty("nested")
    SampleNested nested;
    
    ...
}
```

##### JSON as it appears in the index (with the ``_id`` field) / Input to the Deserializer:
```json
{
  "_id": "idvalue",
  "key": "keyvalue",
  "value": "valuevalue"
}
```

##### Original Input / Desired Output:
```json
{
  "id": "idvalue",
  "key": "keyvalue",
  "value": "valuevalue"
}
```

##### Explanation:
Without this module added on, the input to the deserializer will miss the ``id`` field, since it ``_id`` doesn't match
There is a unit test ``MarshallApplicationTests.newMapperWithout()`` which shows this use case.
The input parsed directly will result in the POJO: ``Input: Sample{id='null', key='keyvalue', value='valuevalue'}``

The other two unit tests, covering both Auto Registration of the Module, and manual Registration of the Module, will
return the correctly parsed: ``Input: Sample{id='idvalue', key='keyvalue', value='valuevalue'}``

