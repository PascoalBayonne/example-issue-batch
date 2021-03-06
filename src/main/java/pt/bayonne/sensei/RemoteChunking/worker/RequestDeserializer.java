package pt.bayonne.sensei.RemoteChunking.worker;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.messaging.Message;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;

@Slf4j
public class RequestDeserializer implements Deserializer<Object> {
    @Override
    public Object deserialize(String topic, byte[] data) {
        log.info("============ deserializing");
        if (data == null){
            return null;
        }

        return  SerializationUtils.deserialize(data);
    }
}
