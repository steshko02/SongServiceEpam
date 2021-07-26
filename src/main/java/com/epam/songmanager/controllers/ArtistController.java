package com.epam.songmanager.controllers;

import com.epam.songmanager.model.Artist;
import com.epam.songmanager.model.Genre;
import com.epam.songmanager.model.dto.ArtistDto;
import com.epam.songmanager.repository.GenreRepository;
import com.epam.songmanager.service.ArtistService;
import com.epam.songmanager.utils.impl.MappingArtistUtilsArtistsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class ArtistController {

    @Autowired
    private ArtistService artistService;
    @Autowired
    private MappingArtistUtilsArtistsImpl mappingUtils;
    @Autowired
    private GenreRepository genreRepository;

    @PostMapping("/artists")
    public  Long add(@RequestBody ArtistDto artistDto){
        return artistService.add(mappingUtils.mapToEntity(artistDto));
    }

    @PutMapping("/artists/{id}")
    public  Long edit(@RequestBody ArtistDto artistDto,@PathVariable  Long id){
        return artistService.edit( mappingUtils.mapToEntity(artistDto), id);
    }

    @GetMapping("/artists/{id}")
    public ArtistDto getGenres(@PathVariable Long id) {
        return mappingUtils.mapToDto(artistService.get(id));
    }

    @DeleteMapping("/artists")
    public Long[] delete(@RequestParam Long[] ids){
        return  artistService.delete(ids);
    }

    @GetMapping("/artists")
    public Set<ArtistDto> getByFilters(@RequestParam String name, @RequestParam Long[] genres) {

        Set<ArtistDto> artistDtoSet = new HashSet<>();
        Set<Genre> forFilter = new HashSet<>();
        Arrays.stream(genres).forEach(id->forFilter.add(genreRepository.getById(id)));
        Set<Artist> artists = artistService.getByFullFilter(name,forFilter);
        artists.forEach(a->artistDtoSet.add(mappingUtils.mapToDto(a)));
        return artistDtoSet;
    }
}
