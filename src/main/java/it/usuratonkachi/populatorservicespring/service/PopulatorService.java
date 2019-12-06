package it.usuratonkachi.populatorservicespring.service;

import it.usuratonkachi.populatorservicespring.data.entity.Doc;
import it.usuratonkachi.populatorservicespring.data.entity.InternalDoc;
import it.usuratonkachi.populatorservicespring.data.repository.DocRepository;
import it.usuratonkachi.populatorservicespring.data.repository.InternalDocRepository;
import it.usuratonkachi.populatorservicespring.randomizer.MockRandomDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PopulatorService {

    private final DocRepository docRepository;
    private final InternalDocRepository internalDocRepository;

    public Flux<InternalDoc> internalDocService(String username){
        return Flux.fromStream(IntStream.range(0, 50).boxed())
                .flatMap(counter -> internalDocRepository.save(MockRandomDocument.generateInternalDoc(username)));
    }

    public Mono<Doc> publicDocService(String username){
        return docRepository.save(MockRandomDocument.generateDoc(username));
    }

}
