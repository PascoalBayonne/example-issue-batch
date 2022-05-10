package pt.bayonne.sensei.RemoteChunking.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import pt.bayonne.sensei.RemoteChunking.dto.ClientDTO;

@Component
@Slf4j
public class RecordProcessor implements ItemProcessor<ClientDTO, ClientDTO> {
    @Override
    public ClientDTO process(ClientDTO item) {
        log.info("processing item: {}",item);
        return item;
    }
}
