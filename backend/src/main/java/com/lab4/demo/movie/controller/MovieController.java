package com.lab4.demo.movie.controller;


import com.lab4.demo.movie.model.dto.GenreDTO;
import com.lab4.demo.movie.model.dto.MovieDTO;
import com.lab4.demo.movie.service.MovieService;
import com.lab4.demo.report.CSVReportService;
import com.lab4.demo.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(MOVIES)
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final CSVReportService csvReportService;

    @PostMapping()
    public MovieDTO create(@RequestBody MovieDTO movieDTO) {
        return movieService.create(movieDTO);
    }

    @GetMapping
    public List<MovieDTO> findAll() {
        return movieService.findAll();
    }

    @GetMapping(ENTITY)
    public MovieDTO getMovie(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PutMapping(ENTITY)
    public MovieDTO edit(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        return movieService.edit(id, movieDTO);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id){ movieService.delete(id);}

    @DeleteMapping()
    public void deleteAll(){ movieService.deleteAll();}

    @PatchMapping(ENTITY)
    public MovieDTO changePrice(@PathVariable Long id, @RequestBody float price){
        return movieService.changePrice(id, price);
    }

    @PatchMapping(ENTITY+"/incRev")
    public MovieDTO increaseNrReviews(@PathVariable Long id){
        return movieService.increaseNrReviews(id);
    }

    @GetMapping("/byName"+NAME)
    public String findLinkByName(@PathVariable String name) {
        return movieService.findLinkByName(name);
    }

    @GetMapping("/byId/"+ENTITY)
    public String findLinkById(@PathVariable Long id) {
        return movieService.findLinkById(id);
    }

    @GetMapping(GENRES+ENTITY)
    public List<GenreDTO> findGenres(@PathVariable Long id) {
        return movieService.findGenres(id);
    }

    @GetMapping(EXPORT_REPORT)
    public ResponseEntity<Resource> exportReport(@PathVariable ReportType type) throws IOException {

        System.out.println("Export "+type);
        File file = csvReportService.export();

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}