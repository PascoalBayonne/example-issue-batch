package pt.bayonne.sensei.RemoteChunking.worker;

import org.mapstruct.Mapper;
import org.springframework.batch.integration.chunk.ChunkRequest;
import pt.bayonne.sensei.RemoteChunking.dto.ClientDTO;

@Mapper(componentModel = "spring")
public interface CustomMapperUtils {
    ChunkRequest<ClientDTO> fromObject(Object ob);
}
