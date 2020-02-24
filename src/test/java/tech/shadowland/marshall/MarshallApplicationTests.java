package tech.shadowland.marshall;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tech.shadowland.jackson.databind.module.IdDeserializerModule;
import tech.shadowland.marshall.model.Sample;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MarshallApplication.class)
class MarshallApplicationTests {

    Logger logger = LoggerFactory.getLogger(MarshallApplicationTests.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test Case for when the Spring MVC Instantiated Mapper is avaiable
     *
     * @throws Exception
     */
    @Test
    void autowiredMapper() throws Exception {

        URL input = MarshallApplicationTests.class.getResource("input.json");
        URL output = MarshallApplicationTests.class.getResource("output.json");

        Sample objInput = objectMapper.readValue(input, Sample.class);
        Sample objOutput = objectMapper.readValue(output, Sample.class);

        logger.info("Input: {}", objInput);
        logger.info("Output: {}", objOutput);

        assertThat(objInput).isEqualTo(objOutput);

        logger.info("Test Complete");
    }

    /**
     * Test Case for when a new ObjectMapper is created
     *
     * This requires us registering a new instance of the Module
     *
     * @throws Exception
     */
    @Test
    void newMapper() throws Exception {

        URL input = MarshallApplicationTests.class.getResource("input.json");
        URL output = MarshallApplicationTests.class.getResource("output.json");

        ObjectMapper om = new ObjectMapper();

        //Additional Configuration, ignore extra properties in the json
        //and register the module
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.registerModule(new IdDeserializerModule());

        Sample objInput = om.readValue(input, Sample.class);
        Sample objOutput = om.readValue(output, Sample.class);

        logger.info("Input: {}", objInput);
        logger.info("Output: {}", objOutput);

        assertThat(objInput).isEqualTo(objOutput);

        logger.info("Test Complete");
    }

    /**
     * Test Case for when a new ObjectMapper is created
     *
     * This requires us registering a new instance of the Module
     *
     * @throws Exception
     */
    @Test
    void newMapperWithout() throws Exception {

        URL input = MarshallApplicationTests.class.getResource("input.json");
        URL output = MarshallApplicationTests.class.getResource("output.json");

        ObjectMapper om = new ObjectMapper();

        //Additional Configuration, ignore extra properties in the json
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Sample objInput = om.readValue(input, Sample.class);
        Sample objOutput = om.readValue(output, Sample.class);

        logger.info("Input: {}", objInput);
        logger.info("Output: {}", objOutput);

        assertThat(objInput).isNotEqualTo(objOutput);

        logger.info("Test Complete");
    }

}
