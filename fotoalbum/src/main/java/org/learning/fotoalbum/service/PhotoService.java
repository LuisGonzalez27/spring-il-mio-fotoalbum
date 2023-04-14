package org.learning.fotoalbum.service;

import org.learning.fotoalbum.exceptions.PhotoNotFoundException;
import org.learning.fotoalbum.model.Photo;
import org.learning.fotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public Photo createPhoto(Photo formPhoto) {
        Photo photoToPersist = new Photo();
        photoToPersist.setTitle(formPhoto.getTitle());
        photoToPersist.setDescription(formPhoto.getDescription());
        photoToPersist.setUrl(formPhoto.getUrl());
        photoToPersist.setVisible(formPhoto.getVisible());
        return photoRepository.save(photoToPersist);
    }

    public Photo updateBook(Photo formPhoto, Integer id) throws PhotoNotFoundException {
        Photo photoToUpdate = getById(id);
        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setUrl(formPhoto.getUrl());
        photoToUpdate.setVisible(formPhoto.getVisible());
        return photoRepository.save(photoToUpdate);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll(Sort.by("title"));
    }

    public List<Photo> getFilterPhotos(String keyword) {
        return photoRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public Optional<Photo> findById(Integer id) {
        return photoRepository.findById(id);
    }

    public Photo getById(Integer id) throws PhotoNotFoundException {
        Optional<Photo> result = photoRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PhotoNotFoundException(Integer.toString(id));
        }
    }

    public boolean deleteById(Integer id) throws PhotoNotFoundException {
        photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException(Integer.toString(id)));
        try {
            photoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidTitle(Photo photoValidate) {
        if (photoValidate.getId() == null) {
            return !photoRepository.existsByTitle(photoValidate.getTitle());
        }
        return !photoRepository.existsByTitleAndIdNot(photoValidate.getTitle(), photoValidate.getId());
    }

}
