package pt.bayonne.sensei.RemoteChunking.job.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.batch.integration.chunk.ChunkResponse;
import org.springframework.util.SerializationUtils;

public class ChunkResponseDeserializer implements Deserializer<ChunkResponse> {
    @Override
    public ChunkResponse deserialize(String s, byte[] bytes) {
        return (ChunkResponse) SerializationUtils.deserialize(bytes);
    }
}
