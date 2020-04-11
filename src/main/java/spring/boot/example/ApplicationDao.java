package spring.boot.example;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static java.time.LocalDate.now;

@Component
@RequiredArgsConstructor
public class ApplicationDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save(String type) {
        String insertScript = readScript("insert.sql");
        jdbcTemplate.update(insertScript, new MapSqlParameterSource(RatingColumns.VALUE_DATE.column(), now())
                .addValue(RatingColumns.TYPE.column(), type));
    }

    public List<Rating> getAll() {
        String selectScript = readScript("selectAll.sql");
        List<Rating> query = jdbcTemplate.query(selectScript, ApplicationDao::ratingRowMapper);
        return query;
    }

    private String readScript(String s) {
        try (InputStream stream = new ClassPathResource(s).getInputStream()) {
            Scanner scanner = new Scanner(stream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Rating ratingRowMapper(ResultSet rs, int rowNum) throws SQLException {
        return Rating.builder()
                .id(rs.getInt(RatingColumns.ID.column()))
                .valueDate(rs.getDate(RatingColumns.VALUE_DATE.column()).toLocalDate())
                .timestamp(rs.getTimestamp(RatingColumns.CREATED_AT.column()).toLocalDateTime())
                .type(rs.getString(RatingColumns.TYPE.column()))
                .build();
    }

    enum RatingColumns implements SqlParams {
        TYPE, VALUE_DATE, CREATED_AT, ID
    }
}
