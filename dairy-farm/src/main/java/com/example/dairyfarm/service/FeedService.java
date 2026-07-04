package com.example.dairyfarm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dairyfarm.entity.Feed;
import com.example.dairyfarm.repository.FeedRepository;

@Service
public class FeedService {

    @Autowired
    private FeedRepository feedRepository;

    // Save Feed
    public Feed saveFeed(Feed feed) {
        return feedRepository.save(feed);
    }

    // Get All Feed
    public List<Feed> getAllFeed() {
        return feedRepository.findAll();
    }

    // Get Feed By ID
    public Feed getFeedById(Long id) {
        return feedRepository.findById(id).orElse(null);
    }

    // Update Feed
    public Feed updateFeed(Long id, Feed feed) {

        Feed existing = getFeedById(id);

        if (existing != null) {

            existing.setFeedName(feed.getFeedName());
            existing.setFeedType(feed.getFeedType());
            existing.setQuantity(feed.getQuantity());
            existing.setUnit(feed.getUnit());
            existing.setPurchasePrice(feed.getPurchasePrice());
            existing.setSupplier(feed.getSupplier());
            existing.setPurchaseDate(feed.getPurchaseDate());
            existing.setExpiryDate(feed.getExpiryDate());
            existing.setStatus(feed.getStatus());

            return feedRepository.save(existing);
        }

        return null;
    }

    // Delete Feed
    public void deleteFeed(Long id) {
        feedRepository.deleteById(id);
    }
}
