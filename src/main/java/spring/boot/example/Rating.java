package spring.boot.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rating {
    Integer id;
    String type;
    LocalDate valueDate;
    LocalDateTime timestamp;
}
