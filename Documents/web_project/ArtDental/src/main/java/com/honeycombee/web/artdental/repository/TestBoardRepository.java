package com.honeycombee.web.artdental.repository;

import com.honeycombee.web.artdental.domain.TestBoard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by gizmo4u on 15. 6. 1..
 */
public interface TestBoardRepository extends MongoRepository<TestBoard, String> {

    public List<TestBoard> findByTitle(String title);
    public List<TestBoard> findByContent(String content);
}
