package it.usuratonkachi.populatorservicespring.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class GenericDocument implements Serializable {

    private static final long serialVersionUID = 20191205L;

    @Id
    private String id;
    private String username;
    private String doctypeid;
    private String doctypename;
    private Integer status;
    private Long size;
    private String mimetype;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
    private LocalDateTime uploaddate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT")
    private LocalDateTime archiveddate;
    private String filename;

}
