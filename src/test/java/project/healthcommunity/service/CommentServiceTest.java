package project.healthcommunity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import project.healthcommunity.repository.CommentRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CommentServiceTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    void join() {

    }

    @Test
    void update() {
    }

    @Test
    void findOne() {
    }

    @Test
    void comments() {
    }
}