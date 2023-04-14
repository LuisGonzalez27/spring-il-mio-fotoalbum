package org.learning.fotoalbum.controller;

import jakarta.validation.Valid;
import org.learning.fotoalbum.exceptions.CategoryNotFoundException;
import org.learning.fotoalbum.model.AlertMessage;
import org.learning.fotoalbum.model.Category;
import org.learning.fotoalbum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(@RequestParam("id") Optional<Integer> idParam, Model model) {
        model.addAttribute("list", categoryService.getAll());
        if (idParam.isPresent()) {
            model.addAttribute("categoryList", categoryService.getById(idParam.get()));
        } else {
            model.addAttribute("categoryList", new Category());
        }
        return "/categories/index";
    }

    @PostMapping("/save")
    public String doSave(@Valid @ModelAttribute(name = "categoryList") Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", categoryService.getAll());
            return "/categories/index";
        }
        if (category.getId() != null) {
            categoryService.update(category);
        } else {
            categoryService.create(category);
        }
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = categoryService.deleteById(id);
            if (success) {
                redirectAttributes.addFlashAttribute("message",
                        new AlertMessage(AlertMessage.AlertMessageType.SUCCESS, "Category with id " + id + " deleted"));

            } else {
                redirectAttributes.addFlashAttribute("message",
                        new AlertMessage(AlertMessage.AlertMessageType.ERROR, "Unable to delete category with id " + id));
            }

        } catch (CategoryNotFoundException e) {
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            redirectAttributes.addFlashAttribute("message",
                    new AlertMessage(AlertMessage.AlertMessageType.ERROR, "Category with id " + id + " not found"));
        }
        return "redirect:/categories";
    }
}
