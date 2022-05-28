package pt.bayonne.sensei.RemoteChunking.job.serde;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.util.SerializationUtils;
import pt.bayonne.sensei.RemoteChunking.dto.ClientDTO;

public class ChunkRequestSerializer implements Serializer<ChunkRequest>{

    private static final Logger logger = LoggerFactory.getLogger(ChunkRequestSerializer.class);

    @Override
    public byte[] serialize(String topic, ChunkRequest data) {

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
    