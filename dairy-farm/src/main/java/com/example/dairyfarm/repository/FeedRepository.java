package com.example.dairyfarm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dairyfarm.entity.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

}
