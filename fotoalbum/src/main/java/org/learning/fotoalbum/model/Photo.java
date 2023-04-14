package org.learning.fotoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Il campo Titolo deve essere compilato")
    @NotEmpty(message = "Il campo Titolo deve essere compilato")
    @Column(nullable = false, unique = true)
    private String title;

    @NotNull(message = "Il campo Description deve essere compilato")
    @NotEmpty(message = "Il campo Description deve essere compilato")
    @Size(min = 3, max = 250)
    private String description;
    private String url;

    @Column(nullable = false)
    private Boolean visible;

    @ManyToMany
    @JoinTable(
            name = "photo_category",
            joinColumns = @JoinColumn(name = "photo_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    public Photo() {
        super();
    }

    public Photo(String title, String description, String url, Boolean visible) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.visible = visible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
