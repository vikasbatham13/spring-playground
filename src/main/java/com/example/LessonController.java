package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    LessonRepository entityManagerFactory;

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.entityManagerFactory .findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.entityManagerFactory .save(lesson);
    }

    @GetMapping("/{id}")
    public Lesson getLessonFive(@PathVariable String id) {
        return this.entityManagerFactory .findOne(Long.parseLong(id));
    }

    @PatchMapping(value ="/5" ,consumes = MediaType.APPLICATION_JSON_VALUE)
      public Lesson updateLessonFive(@RequestBody Lesson lesson) {
       Lesson lesson1 =  this.entityManagerFactory .findOne(lesson.getId());
       lesson1.setTitle(lesson.getTitle());
       lesson1 = this.entityManagerFactory .save(lesson);
        return lesson1;
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable String id) {
         this.entityManagerFactory .delete(Long.parseLong(id));

    }

}
