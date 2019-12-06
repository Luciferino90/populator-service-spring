package it.usuratonkachi.populatorservicespring.handler;

import it.usuratonkachi.populatorservicespring.data.entity.GenericDocument;
import it.usuratonkachi.populatorservicespring.response.DocResponse;
import it.usuratonkachi.populatorservicespring.service.PopulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PopulatorHandler {

    private final PopulatorService populatorService;
    private final Function<ServerRequest, String> getUsername = req -> req.pathVariable("username");

    public Mono<ServerResponse> internalDocHandler(ServerRequest serverRequest){
        return ServerResponse.ok().body(
                populatorService.internalDocService(getUsername.apply(serverRequest))
                        .map(doc -> DocResponse.builder().savedDoc(doc).build()),
                DocResponse.class
        );
    }

    public Mono<ServerResponse> publicDocHandler(ServerRequest serverRequest){
        return ServerResponse.ok().body(
                populatorService.publicDocService(getUsername.apply(serverRequest)).map(this::docToResponse),
                DocResponse.class
        );
    }

    private DocResponse docToResponse(GenericDocument doc){
        return DocResponse.builder()
                .savedDoc(doc)
                .build();
    }


}
