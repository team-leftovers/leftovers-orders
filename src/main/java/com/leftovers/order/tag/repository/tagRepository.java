package com.leftovers.order.tag.repository;

import com.leftovers.order.tag.model.tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface tagRepository extends CrudRepository<tag, Integer> {
    List<tag> findByName(String name);
    tag findById(long id);
}