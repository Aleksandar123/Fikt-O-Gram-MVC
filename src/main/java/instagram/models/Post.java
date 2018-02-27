package instagram.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String imgLocation;

    @Column(nullable = true, length = 140)
    private String description;

    @ManyToOne
    private User uploader;

    @Column(nullable = false)
    private Date date = new Date();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String image;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "image")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Post() {
    }

    public Post(Long id, String description,String imgLocation, User uploader) {
        this.id = id;
        this.imgLocation = imgLocation;
        this.description = description;
        this.uploader = uploader;
    }

    public Post(Long id, String imgLocation, String description, User uploader, Set<Comment> comments) {
        this.id = id;
        this.imgLocation = imgLocation;
        this.description = description;
        this.uploader = uploader;
        this.comments = comments;
    }

    public Post(Long id, String imgLocation, String description, User uploader, String image, Set<Comment> comments) {
        this.id = id;
        this.imgLocation = imgLocation;
        this.description = description;
        this.uploader = uploader;
        this.image = image;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", imgLocation='" + imgLocation + '\'' +
                ", description='" + description + '\'' +
                ", uploader=" + uploader +
                ", date=" + date +
                ", image=" + image +
                ", comments=" + comments +
                '}';
    }
}
