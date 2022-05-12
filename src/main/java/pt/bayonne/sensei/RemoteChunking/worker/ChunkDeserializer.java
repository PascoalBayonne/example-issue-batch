package pt.bayonne.sensei.RemoteChunking.worker;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
public class ChunkDeserializer implements Deserializer<ChunkRequest<Object>> {
    @Override
    public ChunkRequest<Object> deserialize(String topic, byte[] data) {
        log.info("============ deserializing");
        if (data == null){
            return null;
        }

             SerializationUtils.deserialize(data);

return null;
    }
}
