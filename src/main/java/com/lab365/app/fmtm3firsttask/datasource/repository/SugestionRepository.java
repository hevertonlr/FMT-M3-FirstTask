package com.lab365.app.fmtm3firsttask.datasource.repository;

import com.lab365.app.fmtm3firsttask.datasource.entity.Sugestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SugestionRepository extends JpaRepository<Sugestion, Long> {
    List<Sugestion> findAllByOrderByUpdatedAtDesc();

}
