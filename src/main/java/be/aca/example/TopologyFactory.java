package be.aca.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

public class TopologyFactory {

	private static final String FROM_TOPIC = "fromTopic";
	private static final String TO_TOPIC = "toTopic";

	public Topology createTopology() {
		//creates kafka stream to join multiple files
		StreamsBuilder builder = new StreamsBuilder();

		KStream<String, String> aStream = builder.stream(FROM_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));
		aStream.to(TO_TOPIC, Produced.with(Serdes.String(), Serdes.String()));

		return builder.build();
	}
}
