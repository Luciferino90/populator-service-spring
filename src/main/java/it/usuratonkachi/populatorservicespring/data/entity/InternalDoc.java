package it.usuratonkachi.populatorservicespring.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true, fluent = true)
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "internaldocuments")
public class InternalDoc extends GenericDocument {

    private static final long serialVersionUID = 20191205L;

}
