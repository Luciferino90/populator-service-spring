package it.usuratonkachi.populatorservicespring.response;

import it.usuratonkachi.populatorservicespring.data.entity.GenericDocument;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DocResponse implements Serializable {

    private static final long serialVersionUID = 20191205L;
    private GenericDocument savedDoc;

}
