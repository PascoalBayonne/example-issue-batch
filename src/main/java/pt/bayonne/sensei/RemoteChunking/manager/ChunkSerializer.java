package pt.bayonne.sensei.RemoteChunking.manager;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.batch.integration.chunk.ChunkRequest;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.util.SerializationUtils;
import pt.bayonne.sensei.RemoteChunking.dto.ClientDTO;

import java.io.IOException;

@Slf4j
public class ChunkSerializer implements Serializer<Object> {
    @Override
    public byte[] serialize(String topic, Object data) {

            if (data == null){
                log.warn("cannot serialize data because the data is null");
                return new byte[0];
            }
            byte [] dataBytes = null;
            try{
              dataBytes =  SerializationUtils.serialize(data);
            }catch (Exception e){
                e.printStackTrace();
                log.error(e.getMessage());
            }
            return dataBytes;

    }


}
