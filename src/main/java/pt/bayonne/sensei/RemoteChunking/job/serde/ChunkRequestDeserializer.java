package pt.bayonne.sensei.RemoteChunking.job.serde;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.util.SerializationUtils;

@Slf4j
public class ChunkRequestDeserializer implements Deserializer<ChunkRequest> {


    @Override
    public ChunkRequest deserialize(String topic, byte[] data) {

        log.debug("------------------->deserialize");
        
            if (data == null) {
            return null;
        }

        return (ChunkRequest) SerializationUtils.deserialize(data);
    }

}