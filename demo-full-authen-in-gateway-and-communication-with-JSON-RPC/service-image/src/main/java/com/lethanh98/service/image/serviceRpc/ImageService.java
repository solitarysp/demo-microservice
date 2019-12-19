package com.lethanh98.service.image.serviceRpc;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import com.lethanh98.service.image.entity.Image;

import java.util.List;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/18/2019 3:09 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
@JsonRpcService("/image")
public interface ImageService {
    List<Image> getImages();

    Image getImageBy(@JsonRpcParam(value = "id") Integer id);
}
