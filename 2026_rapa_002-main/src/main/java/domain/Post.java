package domain;

public record Post(
        Long id,
        String title,
        String contents,
        Long BoardId
) {
}
