package com.roomfindingsystem.repository;

import com.roomfindingsystem.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("NewsRepository")
public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {
    List<NewsEntity> findAll();

    @Query
    NewsEntity getNewsEntitiesByNewsid(int sliderId);
//    @Query
//    SliderEntity save();
    @Query("select count(*) from NewsEntity ")
    int countNews();

}