package tech.shadowland.jackson.databind.module;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

/**
 * Id Deserializer Module
 *
 * This is useful for cases such as "Write to Mongo -> Read from Elastic"
 * where a json structure will have "id" as it's property, but Mongo writes it as "_id"
 *
 * We looks for a Property of "id" in the Json Mapped entities, then create a new property for "_id"
 * which is a copy of "id" but with the new name.
 *
 * The benefit is this allows it accept both "id" and "_id" for deserialization, but the POJO will
 * return it as "id"
 *
 * To use, this needs registered to the object mapper, or self registration via the @JsonComponent annotation
 *
 * @Author Tim
 */
@JsonComponent
public class IdDeserializerModule extends SimpleModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdDeserializerModule.class);

    private static final String NAME = "IdDeserializerModule";
    private static final PropertyName PROP_ID = PropertyName.construct("id");
    private static final PropertyName PROP_U_ID = PropertyName.construct("_id");

    public IdDeserializerModule() {
        super(NAME, VersionUtil.versionFor(IdDeserializerModule.class));

        setDeserializerModifier(new BeanDeserializerModifier() {
            @Override
            public BeanDeserializerBuilder updateBuilder(DeserializationConfig config, BeanDescription beanDesc, BeanDeserializerBuilder builder) {
                SettableBeanProperty idProperty = builder.findProperty(PROP_ID);

                if(idProperty != null) {
                    SettableBeanProperty underId = idProperty.withName(PROP_U_ID);
                    builder.addProperty(underId);
                }

                return super.updateBuilder(config, beanDesc, builder);
            }
        });

        LOGGER.info("Added \"_id -> id\" Bean Modfidier");
    }
}