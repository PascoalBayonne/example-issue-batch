package pt.bayonne.sensei.RemoteChunking.job.serde;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.integration.chunk.ChunkResponse;
import org.springframework.util.SerializationUtils;

public class ChunkResponseSerializer implements Serializer<ChunkResponse>{

    private static final Logger logger = LoggerFactory.getLogger(ChunkRequestSerializer.class);

    @Override
    public byte[] serialize(String topic, ChunkResponse data) {

        if (data == null) {
            return null;
        }

        String dataType = data.getClass().getName();
        logger.debug("--> serializing: {}",dataType);

        byte[] dataBytes = null;
        try {

            dataBytes = SerializationUtils.serialize(data);

        } catch (Exception e) {
            logger.error("Error serializing data", e);
        }
        
        return dataBytes;
    }

}
    