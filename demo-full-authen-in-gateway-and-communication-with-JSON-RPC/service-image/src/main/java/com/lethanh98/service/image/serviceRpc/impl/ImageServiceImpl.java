package com.lethanh98.service.image.serviceRpc.impl;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.lethanh98.service.image.entity.Image;
import com.lethanh98.service.image.serviceRpc.ImageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/18/2019 3:10 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@Service
@AutoJsonRpcServiceImpl
public class ImageServiceImpl implements ImageService {
    @Override
    public List<Image> getImages() {
        System.out.println("1");

        List<Image> images = Arrays.asList(
                new Image(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
                new Image(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
                new Image(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"));
        return images;
    }

    @Override
    public Image getImageBy(Integer id) {
        System.out.println("1");
        Optional<Image> optionalImage = getImages().stream().filter(image -> id.equals(image.getId())).findFirst();
        return optionalImage.orElseGet(Image::new);
    }
}
