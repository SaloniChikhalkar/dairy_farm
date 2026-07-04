package com.example.dairyfarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dairyfarm.entity.Feed;
import com.example.dairyfarm.service.FeedService;

@RestController
@RequestMapping("/api/feed")
@CrossOrigin(origins = "*")
public class FeedController {

    @Autowired
    private FeedService feedService;

    // Add Feed
    @PostMapping
    public Feed saveFeed(@RequestBody Feed feed) {
        return feedService.saveFeed(feed);
    }

    // Get All Feed
    @GetMapping
    public List<Feed> getAllFeed() {
        return feedService.getAllFeed();
    }

    // Get Feed By ID
    @GetMapping("/{id}")
    public Feed getFeed(@PathVariable Long id) {
        return feedService.getFeedById(id);
    }

    // Update Feed
    @PutMapping("/{id}")
    public Feed updateFeed(@PathVariable Long id,
                           @RequestBody Feed feed) {
        return feedService.updateFeed(id, feed);
    }

    // Delete Feed
    @DeleteMapping("/{id}")
    public String deleteFeed(@PathVariable Long id) {

        feedService.deleteFeed(id);

        return "Feed Deleted Successfully";
    }
}
