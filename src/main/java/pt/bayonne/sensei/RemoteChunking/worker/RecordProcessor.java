package pt.bayonne.sensei.RemoteChunking.worker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import pt.bayonne.sensei.RemoteChunking.dto.ClientDTO;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class RecordProcessor implements ItemProcessor<ClientDTO, ClientDTO> {
    AtomicInteger integer = new AtomicInteger(0);
    @Override
    public ClientDTO process(ClientDTO item) {
        log.info("processing item: {}",item);
        log.warn("number of item processed: {}",integer.incrementAndGet());
        return item;
    }
}
