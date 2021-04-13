package se.assignments.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.assignments.ArticlesModel;
import se.assignments.SampleModelRepository;
import se.assignments.ResourcesNotFoundException;

import java.util.List;
import java.util.Set;

public class TopicModelController {

    TopicModelRepository topicRepository;
    SampleModelRepository articleRepository;

    @Autowired
    public TopicModelController(TopicModelRepository topicRepository, SampleModelRepository articleRepository){
        this.topicRepository = topicRepository;
        this.articleRepository = articleRepository;
    }


    @PostMapping("/topics")
    public ResponseEntity<AppTopic> createTopic(@RequestBody AppTopic topicParam){
        return ResponseEntity.status(HttpStatus.CREATED).body(topicRepository.save(topicParam));
    }


    @GetMapping("/topics")
    public ResponseEntity<List<AppTopic>> getAllTopics(){
        return ResponseEntity.ok(topicRepository.findAll());
    }


    @GetMapping("/topics/{topicId}/articles")
    public ResponseEntity<Set<ArticlesModel>> getAllArticlesAssociatedWithTopic(@PathVariable Long topicId){
        AppTopic topic = topicRepository.findById(topicId).orElseThrow(ResourcesNotFoundException::new);
        return ResponseEntity.ok(topic.getArticlesList());
    }


    @PutMapping("/topics/{topicId}")
    public ResponseEntity<AppTopic> updateTopic(@PathVariable Long topicId, @RequestBody AppTopic topicParam){
        AppTopic existingTopic = topicRepository.findById(topicId).orElseThrow(ResourcesNotFoundException::new);
        topicParam.setId(existingTopic.getId());
        topicRepository.save(topicParam);
        return ResponseEntity.ok(topicParam);
    }


    @DeleteMapping("/topics/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable Long topicId) {
        AppTopic topic = topicRepository.findById(topicId).orElseThrow(ResourcesNotFoundException::new);
        topicRepository.delete(topic);
    }


    @PostMapping("/articles/{articleId}/topics")
    public ResponseEntity<AppTopic> associatesTopicWithArticle(@PathVariable Long articleId, @RequestBody AppTopic topicParam){
        ArticlesModel article = articleRepository.findById(articleId).orElseThrow(ResourcesNotFoundException::new);
        boolean doesExist = true;
        if(topicRepository.findById(topicParam.getId()).isEmpty()){
            topicRepository.save(topicParam);
            doesExist=false;
        }
        topicParam.getArticlesList().add(article);
        topicRepository.save(topicParam);
        if(!doesExist){
            return ResponseEntity.status(HttpStatus.CREATED).body(topicParam);
        }else{
            return ResponseEntity.ok(topicParam);
        }
    }


    @DeleteMapping("/articles/{articleId}/topics/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopic(@PathVariable Long articleId, @PathVariable Long topicId) {
        ArticlesModel article = articleRepository.findById(articleId).orElseThrow(ResourcesNotFoundException::new);
        AppTopic topic = topicRepository.findById(topicId).orElseThrow(ResourcesNotFoundException::new);
        if (topic.getArticlesList().contains(article)) {
            topic.getArticlesList().remove(article);
            topicRepository.save(topic);
        } else{
            throw new ResourcesNotFoundException();
        }
    }


}
