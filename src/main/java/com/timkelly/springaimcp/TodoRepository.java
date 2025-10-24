package com.timkelly.springaimcp;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Task, ObjectId> {
    List<Task> findByName(String name);
    List<Task> findByCompletedTrue();
    List<Task> findByCompletedFalse();
}
