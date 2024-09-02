package com.lab365.app.fmtm3firsttask.datasource.repository;

import com.lab365.app.fmtm3firsttask.datasource.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
