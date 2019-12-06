package it.usuratonkachi.populatorservicespring.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Accessors(chain = true, fluent = true)
@AllArgsConstructor
@Document(collection = "documents")
public class Doc extends GenericDocument {

    private static final long serialVersionUID = 20191205L;

}
