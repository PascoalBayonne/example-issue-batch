package pt.bayonne.sensei.RemoteChunking.job.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.SerializationUtils;

import java.io.IOException;

@Slf4j
public class ChunkSerializer implements Serializer<ChunkRequest> {
    @Override
    public byte[] serialize(String topic, ChunkRequest data) {
        if (data == null){
            log.warn("cannot serialize data because the data is null");
            return new byte[0];
        }

        return SerializationUtils.serialize(data);

    }


}
