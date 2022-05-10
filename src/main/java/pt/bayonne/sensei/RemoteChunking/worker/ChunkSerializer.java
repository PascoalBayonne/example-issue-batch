package pt.bayonne.sensei.RemoteChunking.worker;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.core.serializer.DefaultSerializer;

import java.io.IOException;

@Slf4j
public class ChunkSerializer implements Serializer<Object> {
    @Override
    public byte[] serialize(String topic, Object data) {
        try {
            if (data == null){
                log.warn("cannot serialize data because the data is null");
                return new byte[0];
            }

            return new DefaultSerializer().serializeToByteArray(data);
        } catch (IOException e) {
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }

    }


}
