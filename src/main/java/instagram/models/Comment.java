package instagram.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by aleksandar on 9.8.16.
 */
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 140)
    private String comment;


    @ManyToOne
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User commentor;

    @ManyToOne
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Post image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getCommentor() {
        return commentor;
    }

    public void setCommentor(User commentor) {
        this.commentor = commentor;
    }

    public Post getImage() {
        return image;
    }

    public void setImage(Post image) {
        this.image = image;
    }

    public Comment() {
    }

    public Comment(Long id, String comment, User commentor, Post image) {
        this.id = id;
        this.comment = comment;
        this.commentor = commentor;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", commentor=" + commentor.getUsername() +
                ", image=" + image.getId() +
                '}';
    }
}
