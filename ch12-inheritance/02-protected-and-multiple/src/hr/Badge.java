package hr;

public interface Badge {
    default String code() {
        return "B-42";
    }
}
