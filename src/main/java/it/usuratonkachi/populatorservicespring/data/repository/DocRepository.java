package it.usuratonkachi.populatorservicespring.data.repository;

import it.usuratonkachi.populatorservicespring.data.entity.Doc;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DocRepository extends ReactiveMongoRepository<Doc, String> {
}
