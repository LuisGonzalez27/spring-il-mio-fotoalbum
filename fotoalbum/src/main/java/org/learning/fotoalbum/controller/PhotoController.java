package org.learning.fotoalbum.controller;

import org.learning.fotoalbum.model.Photo;
import org.learning.fotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
        List<Photo> photos;
        if (keyword.isEmpty()) {
            photos = photoRepository.findAll(Sort.by("title"));
        } else {
            photos = photoRepository.findByTitleContainingIgnoreCase(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("list", photos);
        return "/photos/index";
    }

    @GetMapping("/{photoId}")
    public String show(@PathVariable("photoId") Integer id, Model model) {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("photo", result.get());
            return "/photos/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo witch id " + id + " not found");
        }
    }

}
