package com.example.elasticsearch.dao;


import com.example.elasticsearch.model.FilmEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * The interface Film dao.
 *
 * @author
 */
public interface FilmDao extends ElasticsearchRepository<FilmEntity, Long> {

}