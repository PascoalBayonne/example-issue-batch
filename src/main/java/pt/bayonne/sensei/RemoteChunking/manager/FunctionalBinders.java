//package pt.bayonne.sensei.RemoteChunking.manager;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Sinks;
//
//import java.util.function.Supplier;
//
//@Profile("!worker")
//@Configuration
//public class FunctionalBinders {
//
//
//    @Bean
//    public Sinks.Many<Object> sink() {
//        return Sinks.many()
//                .replay()
//                .latest();
//    }
//
//
//    @Bean
//    public Supplier<Flux<Object>> clientRequests() {
//        return () -> sink()
//                .asFlux()
//                .cache();
//    }
//
//}
