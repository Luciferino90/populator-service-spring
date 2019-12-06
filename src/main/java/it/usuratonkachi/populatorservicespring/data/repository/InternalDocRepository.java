package it.usuratonkachi.populatorservicespring.data.repository;

import it.usuratonkachi.populatorservicespring.data.entity.InternalDoc;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface InternalDocRepository extends ReactiveMongoRepository<InternalDoc, String> {
}
