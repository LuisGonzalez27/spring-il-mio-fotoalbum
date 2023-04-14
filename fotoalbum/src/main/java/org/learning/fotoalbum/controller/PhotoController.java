package org.learning.fotoalbum.controller;

import jakarta.validation.Valid;
import org.learning.fotoalbum.exceptions.PhotoNotFoundException;
import org.learning.fotoalbum.model.AlertMessage;
import org.learning.fotoalbum.model.Photo;
import org.learning.fotoalbum.repository.PhotoRepository;
import org.learning.fotoalbum.service.CategoryService;
import org.learning.fotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PhotoService photoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
        List<Photo> photos;
        if (keyword.isEmpty()) {
            photos = photoService.getAllPhotos();
        } else {
            photos = photoService.getFilterPhotos(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("list", photos);
        return "/photos/index";
    }

    @GetMapping("/{photoId}")
    public String show(@PathVariable("photoId") Integer id, Model model) {
        try {
            Photo photo = photoService.getById(id);
            model.addAttribute("photo", photo);
            return "/photos/show";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id " + id + " not found");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("photo", new Photo());
        model.addAttribute("categoryList", categoryService.getAll());
        return "photos/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {

        boolean hasErrors = bindingResult.hasErrors();

        if (!photoService.isValidTitle(formPhoto)) {
            bindingResult.addError(new FieldError("photo", "title", formPhoto.getTitle(), false, null, null, "Title must be unique"));
            hasErrors = true;
        }
        if (hasErrors) {
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/create";
        }
        photoService.createPhoto(formPhoto);
        return "redirect:/photos";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Photo photo = photoService.getById(id);
            model.addAttribute("photo", photo);
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/edit";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model) {

        if (!photoService.isValidTitle(formPhoto)) {
            bindingResult.addError(new FieldError("photo", "title", formPhoto.getTitle(), false, null, null, "Title must be unique"));
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", categoryService.getAll());
            return "/photos/edit";
        }
        try {
            Photo updatePhoto = photoService.updateBook(formPhoto, id);
            return "redirect:/photos/" + Integer.toString(updatePhoto.getId());
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with id " + id + " not found");
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = photoService.deleteById(id);
            if (success) {
                redirectAttributes.addFlashAttribute("message",
                        new AlertMessage(AlertMessage.AlertMessageType.SUCCESS, "Photo with id " + id + " deleted"));
            } else {
                redirectAttributes.addFlashAttribute("message",
                        new AlertMessage(AlertMessage.AlertMessageType.ERROR, "Unable to delete photo with id " + id));
            }
        } catch (PhotoNotFoundException e) {
            redirectAttributes.addFlashAttribute("message",
                    new AlertMessage(AlertMessage.AlertMessageType.ERROR, "Photo with id " + id + " not found"));
        }
        return "redirect:/photos";
    }

}
