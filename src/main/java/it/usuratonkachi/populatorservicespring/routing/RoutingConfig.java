package it.usuratonkachi.populatorservicespring.routing;

import it.usuratonkachi.populatorservicespring.handler.PopulatorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RoutingConfig {

    private final PopulatorHandler populatorHandler;

    @Bean
    public RouterFunction<ServerResponse> route(){
        return RouterFunctions.route()
                .GET("/private/{username}", populatorHandler::internalDocHandler)
                .GET("/insert/{username}", populatorHandler::publicDocHandler)
                .build();
    }

}
