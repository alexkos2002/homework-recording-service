package soa.labs.kosiuk.homeworkrecordingservice.model.dto.wrappers;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class HttpResponseWrapper<D> {

    D data;

    HttpStatus httpStatus;
}
