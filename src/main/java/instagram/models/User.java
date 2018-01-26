package instagram.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aleksandar on 9.8.16.
 */
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 30, message = "Please enter username between 3 and 30 characters.")
    @Column(nullable = false, length = 30, unique = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String username;

    @Size(min = 6, max = 60, message = "Passwords should be between 6 and 60 characters long.")
    @Column(length = 60)
    private String password;

    @Column(length = 100)
    private String fullName;

    @Size(min = 0, max = 140, message = "About me should't be more than 140 characters.")
    @Column(length = 140)
    private String aboutMe;

    @Column(name = "authorities")
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    private String authorities;


    @Column
    private Boolean enabled = true;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uploader")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "commentor")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Comment> comments = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }






    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public User() {
    }

    public User(Long id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
    }

    public User(Long id, String username, String password, String fullName) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", authorities='" + authorities + '\'' +
                '}';
    }
}
