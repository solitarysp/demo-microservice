package com.lethanh98.service.gallery.controller;

import com.lethanh98.service.gallery.entity.Gallery;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/")
    public String home() {
        // This is useful for debugging
        // When having multiple instance of gallery service running at different ports.
        // We load balance among them, and display which instance received the request.
        return "Hello from Gallery Service running at port: " + env.getProperty("local.server.port");
    }

    @RequestMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallback")
    public Gallery getGallery(@PathVariable final int id, HttpServletRequest servletRequest) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-gallery");
        System.out.println(Thread.currentThread().getName());
        //   System.out.println(serviceInstance.getUri().toString());
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);
        // get list of available images
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", servletRequest.getHeader("Authorization"));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        List<Object> images = (List<Object>) restTemplate.exchange("http://service-image/images/", HttpMethod.GET, entity, Object.class).getBody();
        gallery.setImages(images);
        return gallery;
    }

    // -------- Admin Area --------
    // This method should only be accessed by users with role of 'admin'
    // We'll add the logic of role based auth later
    @RequestMapping("/admin")
    public String homeAdmin() {
        return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
    }

    // a fallback method to be called if failure happened
    public Gallery fallback(int galleryId, HttpServletRequest servletRequest, Throwable hystrixCommand) {
        System.out.printf(hystrixCommand.getMessage());
        return new Gallery(galleryId);
    }
}
