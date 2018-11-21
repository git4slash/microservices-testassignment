package subbotin.spring.microservices.testassignment.upday.article;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Persistent keyword entity with JPA markup.
 *
 * @author Oleksandr Subbotin
 */
@Entity
@NoArgsConstructor
@Table(name = "ARTICLE")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String header;

    private String shortDescription;

    private String text;

    private LocalDate publishDate;

    @NotNull
    private Long authorId;

    @ElementCollection
    private Set<Long> keywordIDs = new HashSet<>();

    public Article(String header, String shortDescription, @NotNull String text, @NotNull Long authorId, Long... keywordIDs) {
        this.header = header;
        this.shortDescription = shortDescription;
        this.text = text;
        this.authorId = authorId;
        setKeywordIDs(keywordIDs);
        this.publishDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Set<Long> getKeywordIDs() {
        return keywordIDs;
    }

    public void setKeywordIDs(Long... keywordIDs) {
        Arrays.stream(keywordIDs).forEach(this::addKeyword);
    }

    public void addKeyword(Long keywordID) {
        this.keywordIDs.add(keywordID);
    }

    public void removeKeyword(Long keywordID) {
        this.keywordIDs.remove(keywordID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeader(), getShortDescription(), getText(), getPublishDate(), getAuthorId());
    }
}
