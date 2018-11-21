package subbotin.spring.microservices.testassignment.upday.keyword;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection
    private Set<Long> articleIDs = new HashSet<>();

    public Keyword(String name) {this.name = name;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getArticleIDs() {
        return articleIDs;
    }

    public void setArticleIDs(Set<Long> articleIDs) {
        this.articleIDs = articleIDs;
    }

    public void addArticleId(Long articleId) {
        this.articleIDs.add(articleId);
    }

    public void removeArticleId(Long articleId) {
        this.articleIDs.remove(articleId);
    }
}
