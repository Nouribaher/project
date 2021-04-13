package se.sdaproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.assignments.ArticlesModel;
import se.assignments.ResourcesNotFoundException;
import se.assignments.SampleModelRepository;
import se.assignments.topic.AppTopic;
import se.assignments.topic.TopicModelController;

import java.util.List;
import java.util.Set;

@RestController
public  class ArticlesController {
    SampleModelRepository sampleModelRepository;
    TopicModelController topicModelController;
    @Autowired
    public ArticlesController(SampleModelRepository sampleModelRepository,TopicModelController topicModelController ) {
        this.sampleModelRepository = sampleModelRepository;
        this.topicModelController = topicModelController;
    }

    @GetMapping("/articles")
    public List<ArticlesModel> getArticlesList(){
        return sampleModelRepository.findAll();
    }


    @GetMapping("/articles/{id}")
    public ArticlesModel getArticle(@PathVariable Long id){
        ArticlesModel article = sampleModelRepository.findById(id).orElseThrow(ResourcesNotFoundException::new);
        return article;
    }

    @PostMapping("/articles")
    public ArticlesModel postArticle(@RequestBody ArticlesModel article){
        sampleModelRepository.save(article);
        return article;
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticlesModel> updateArticle(@PathVariable Long id, @RequestBody ArticlesModel updatedArticle){
        ArticlesModel existingArticle = sampleModelRepository.findById(id).orElseThrow(ResourcesNotFoundException::new);
        updatedArticle.setId(id);
        sampleModelRepository.save(updatedArticle);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id){
        ArticlesModel article = sampleModelRepository.findById(id).orElseThrow(ResourcesNotFoundException::new);
        sampleModelRepository.deleteById(id);
    }

    @GetMapping("/articles/{articleId}/topics")
    public ResponseEntity<Set<AppTopic>> getAllTopics(@PathVariable Long articleId){
        ArticlesModel article = sampleModelRepository.findById(articleId).orElseThrow(ResourcesNotFoundException::new);
        return ResponseEntity.ok(article.getTopicsList());
    }



}
