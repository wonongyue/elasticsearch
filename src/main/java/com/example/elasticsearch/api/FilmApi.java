package com.example.elasticsearch.api;

import com.example.elasticsearch.common.ApiResult;
import com.example.elasticsearch.impl.FilmService;
import com.example.elasticsearch.model.FilmEntity;
import com.google.common.base.Strings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class FilmApi {

    @Autowired
    private FilmService filmService;

    @PostMapping("update")
    public ApiResult update(@RequestBody FilmEntity filmEntity) {
        filmEntity.setCreated(new Date());
        filmService.save(filmEntity);
        return ApiResult.prepare().success("处理成功");
    }

    @RequestMapping("del")
    public ApiResult del(Long id) {
        filmService.delete(id);
        return ApiResult.prepare().success("处理成功");
    }

    @RequestMapping("findById")
    public ApiResult findById(Long id) {
         FilmEntity filmEntity = filmService.findById(id);
        return ApiResult.prepare().success(filmEntity);
    }

    /**
     * Search api result.
     *
     * @param name the name
     * @return the api result
     */
    @GetMapping("/search/{name}")
    public ApiResult search(@PathVariable String name) {
        if (!Strings.isNullOrEmpty(name)) {
            return ApiResult.prepare().success(filmService.search(name, ""));
        }
        return null;
    }

    @GetMapping("searchName")
    public ApiResult search1(@RequestParam(name = "name") String name) {
        if (!Strings.isNullOrEmpty(name)) {
            return ApiResult.prepare().success(filmService.search(name, ""));
        }
        return null;
    }

    /**
     * 精确查询
     * @param name
     * @return
     */
    @GetMapping("accurateSearch")
    public ApiResult accurateSearch(@RequestParam(name = "name") String name) {
        if (!Strings.isNullOrEmpty(name)) {
            return ApiResult.prepare().success(filmService.accurateQuery(name));
        }
        return null;
    }

    /**
     * Search highlight api result.
     *
     * @param name the name
     * @return the api result
     */
    @GetMapping("/search/highlight/{name}")
    public ApiResult searchHighlight(@PathVariable String name) {
        if (!Strings.isNullOrEmpty(name)) {
            return ApiResult.prepare().success(filmService.searchHinghlight(name, ""));
        }
        return null;
    }

    /**
     * Save api result.
     *
     * @param filmEntity the film entity
     * @return the api result
     */
    @PostMapping("save")
    public ApiResult save(@RequestBody FilmEntity filmEntity) {
        filmEntity.setCreated(new Date());
        filmService.save(filmEntity);
        return ApiResult.prepare().success("处理成功");
    }

    /**
     * Gets analyze result.
     *
     * @param content     the content
     * @param analyzeType the analyze type
     * @return the analyze result
     */
    @GetMapping("/getAnalyzeResult")
    public ApiResult getAnalyzeResult(@RequestParam(name = "content") String content, @RequestParam(name = "analyzeType", required = false) String analyzeType) {
        return ApiResult.prepare().success(filmService.getAnalyzeResult(content, analyzeType));
    }
}