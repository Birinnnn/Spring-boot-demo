package com.studentExample.demo.controller;

import com.studentExample.demo.repository.LectureRepository;
import com.studentExample.demo.entity.Lecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/magic-api")
class LectureController {

    private final Logger log = LoggerFactory.getLogger(LectureController.class);
    private LectureRepository lectureRepository;

    public LectureController(LectureRepository groupRepository) {
        this.lectureRepository = groupRepository;
    }

    @GetMapping("/lectures")
    Collection<Lecture> lectures() {
        return lectureRepository.findAll();
    }

    @GetMapping("/lecture/{id}")
    ResponseEntity<?> getLecture(@PathVariable Long id) {
        Optional<Lecture> lecture = lectureRepository.findById(id);
        return lecture.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/lecture")
    ResponseEntity<Lecture> createLecture(@Valid @RequestBody Lecture group) throws URISyntaxException {
        log.info("Request to create group: {}", group);
        Lecture result = lectureRepository.save(group);
        return ResponseEntity.created(new URI("/api/lecture/" + result.getId()))
                .body(result);
    }

    @PutMapping("/lecture/{id}")
    ResponseEntity<Lecture> updateLecture(@Valid @RequestBody Lecture lecture) {
        log.info("Request to update lecture: {}", lecture);
        Lecture result = lectureRepository.save(lecture);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/lecture/{id}")
    public ResponseEntity<?> deleteLecture(@PathVariable Long id) {
        log.info("Request to delete lecture: {}", id);
        lectureRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}