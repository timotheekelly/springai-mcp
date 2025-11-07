package com.timkelly.springaimcp;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Task, ObjectId> {
    @Update("{ '$set' : { 'completed' : ?1 } }")
    void updateCompletedByName(String name, boolean completed);
    List<Task> findByCompletedTrue();
    List<Task> findByCompletedFalse();
}
