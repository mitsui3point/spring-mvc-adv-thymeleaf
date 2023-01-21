package hello.thymeleaf.basic.domain;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private String username;
    private Integer age;

    @Builder
    private User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
}
