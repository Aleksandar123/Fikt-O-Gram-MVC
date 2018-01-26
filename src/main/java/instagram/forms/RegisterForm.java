package instagram.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by aleksandar on 11.8.16.
 */
public class RegisterForm {

    private Long id;

    @NotNull
    @Size(min=2, max=30, message = "Username size should be in the range [2...30]")
    private String username;

    @NotNull
    @Size(min=6, max=50)
    private String password;

    @Size(min = 0, max = 100)
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
