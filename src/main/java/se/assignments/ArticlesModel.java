package se.assignments;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import se.assignments.comment.CommentModel;
import se.assignments.topic.AppTopic;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class ArticlesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String authorName;
    private String Body;

    public ArticlesModel(String title, String body, String authorName) {
        this.title = title;
        this.authorName = authorName;
        Body = body;
    }
    public ArticlesModel(){
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getBody() {
        return Body;
    }
    public void setBody(String body) {
        Body = body;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void updateArticle(ArticlesModel updatedArticle){
        this.title = updatedArticle.title;
        this.authorName=updatedArticle.authorName;
        this.Body=updatedArticle.Body;
    }

    @OneToMany(mappedBy = "commentedArticle")
    private List<CommentModel> articleCommentList;
    public List<CommentModel> getArticleCommentsList() {
        return articleCommentList;
    }

    public void setArticleCommentsList(List<CommentModel> articleCommentList) {
        this.articleCommentList = articleCommentList;
    }
    @ManyToMany(mappedBy = "articlesList")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<AppTopic> topicList;
    public Set<AppTopic> getTopicsList() {
        return topicList;
    }
    public void setTopicsList(Set<AppTopic> topicList) {
        this.topicList = topicList;
    }


}