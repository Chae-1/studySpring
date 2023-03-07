package springEx.springEx.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String password;
    private Integer login;
    private Integer recommend;

    private Level level;

    public User() {
    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String id, String name, String password, Integer login, Integer recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
        this.recommend = recommend;
    }

    public User(String id, String name, String password, Integer login, Integer recommend, Level level) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
        this.recommend = recommend;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }
}
