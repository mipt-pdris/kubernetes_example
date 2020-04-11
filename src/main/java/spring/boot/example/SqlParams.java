package spring.boot.example;

public interface SqlParams {
    String name();

    default String column() {
        return name().toLowerCase();
    }
}
