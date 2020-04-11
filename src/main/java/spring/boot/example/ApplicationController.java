package spring.boot.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ApplicationController {
    private final ObjectMapper mapper;
    private final ApplicationProperties properties;
    private final ApplicationDao dao;

    @GetMapping("/")
    public String index() {
        return "Hello World! profile: " + properties.getValue();
    }

    @GetMapping("/save")
    public void save(@RequestParam("type") String type) {
        dao.save(type);
    }

    @GetMapping("/all")
    public List<String> getAll() {
        return dao.getAll().stream().map(r-> {
            try {
                return mapper.writeValueAsString(r).replace("\\", "");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
