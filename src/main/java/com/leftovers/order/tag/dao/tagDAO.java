package com.leftovers.order.tag.dao;


import com.leftovers.order.tag.model.tag;
import com.leftovers.order.tag.repository.tagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class tagDAO {

    @Autowired
    tagRepository fdr;

    public boolean addTag(tag newTag) {

        try {
            fdr.save(newTag);
        }
        catch (Exception e) {
            throw e;
        }
        return true;
    }

    public Optional<tag> getTagById(Integer id) {
        var result = fdr.findById(id);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    public List<tag> getTagByName(String name) {
        var result = fdr.findByName(name);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    public List<tag> getAll() {
        var result = fdr.findAll();
        if (result != null) {
            return (List<tag>) result;
        }
        else {
            return null;
        }
    }

    public boolean UpdateTag(tag updateTag) {
        try {
            fdr.save(updateTag);
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }
        return true;
    }
}