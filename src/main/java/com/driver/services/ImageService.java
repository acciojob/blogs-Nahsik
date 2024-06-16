package com.driver.services;

import java.util.Optional;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;

    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository2.findById(blogId).get();
        image.setBlog(blog);

        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
          imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        Optional<Image> imageOpt = imageRepository2.findById(id);
        if(!imageOpt.isPresent()){
            return 0;
        }
        Image image = imageOpt.get();
        String[] imageDim = image.getDimensions().split("X");
        String[] screenDim = screenDimensions.split("X");
       return (Integer.parseInt(screenDim[0])/Integer.parseInt(imageDim[0]))
        *(Integer.parseInt(screenDim[1])/Integer.parseInt(imageDim[1]));
    }
}
