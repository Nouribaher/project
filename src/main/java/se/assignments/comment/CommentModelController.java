package se.assignments.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.assignments.ArticlesModel;
import se.assignments.SampleModelRepository;
import se.assignments.ResourcesNotFoundException;


import java.util.List;
@RestController
public class CommentModelController {

    CommentModelRepository commentRepository;
    SampleModelRepository articleRepository;

    @Autowired
    public CommentModelController(CommentModelRepository commentRepository, SampleModelRepository articleRepository){
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }


    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentModel> createComment(@PathVariable Long articleId, @Validated @RequestBody CommentModel comment){
        ArticlesModel article = articleRepository.findById(articleId).orElseThrow(ResourcesNotFoundException::new);
        comment.setCommentedArticle(article);
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);

    }


    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<CommentModel>> getArticlesCommentsList(@PathVariable Long articleId){
        ArticlesModel article = articleRepository.findById(articleId).orElseThrow(ResourcesNotFoundException::new);
        return ResponseEntity.ok(article.getArticleCommentsList());
    }


    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentById(@PathVariable Long id){
        commentRepository.deleteById(id);
    }


    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentModel> updateComment(@PathVariable Long id, @Validated @RequestBody CommentModel commentParams){
        CommentModel existingComment = commentRepository.findById(id).orElseThrow(ResourcesNotFoundException::new);
        commentParams.setId(id);
        commentRepository.save(commentParams);
        return ResponseEntity.ok(commentParams);
    }


    @GetMapping(value = "/comments", params = {"authorName"})
    public ResponseEntity<List<CommentModel>> viewAllCommentsMadeByAuthor(@RequestParam String authorName) {
        return ResponseEntity.ok(commentRepository.findByAuthorName(authorName));
    }



}
