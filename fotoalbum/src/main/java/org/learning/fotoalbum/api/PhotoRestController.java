package org.learning.fotoalbum.api;

import org.learning.fotoalbum.exceptions.PhotoNotFoundException;
import org.learning.fotoalbum.model.Photo;
import org.learning.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/photos")
public class PhotoRestController {
    @Autowired
    private PhotoService photoService;

    //List of all photos
    @GetMapping
    public List<Photo> list(@RequestParam(name = "q") Optional<String> search) {
        if (search.isPresent()) {
            return photoService.getFilterPhotos(search.get());
        }
        return photoService.getAllPhotos();
    }

    //Single photo
    @GetMapping("/{id}")
    public Photo getById(@PathVariable Integer id) {
        try {
            return photoService.getById(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
