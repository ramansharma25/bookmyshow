package com.raman.TheatreService.repository;

import com.raman.TheatreService.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowTime, Long>
{}
