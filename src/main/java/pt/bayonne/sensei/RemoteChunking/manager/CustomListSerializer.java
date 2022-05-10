package pt.bayonne.sensei.RemoteChunking.manager;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.batch.integration.chunk.ChunkRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomListSerializer extends StdSerializer<ChunkRequest> {

    protected CustomListSerializer(Class<ChunkRequest> t) {
        super(t);
    }

    @Override
    public void serialize(ChunkRequest value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        value.getStepContribution().getStepExecution().getJobExecution();
    }
}