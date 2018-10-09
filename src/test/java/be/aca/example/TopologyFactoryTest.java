package be.aca.example;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.test.ConsumerRecordFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TopologyFactoryTest {

	private TopologyTestDriver testDriver;

	private ConsumerRecordFactory<String, String> recordFactory = new ConsumerRecordFactory<>(new StringSerializer(), new StringSerializer());

	@BeforeEach
	public void setUp() {
		Properties props = new Properties();
		props.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "some-processor");
		props.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
		props.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		testDriver = new TopologyTestDriver(new TopologyFactory().createTopology(), props);
	}

	@Test
	public void pushDataToStream() {
		testDriver.pipeInput(recordFactory.create("fromTopic", "key", "value"));
	}

	@AfterEach
	public void tearDown() {
		if(testDriver != null) {
			testDriver.close();
		}
	}

}
