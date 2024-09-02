package com.lab365.app.fmtm3firsttask.datasource.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity(name="answer")
@DynamicUpdate
@Table(name="answer")
public class Answer extends BaseEntity<Answer>{
    @ManyToOne
    @JoinColumn(name = "id_sugestion", nullable = false)
    private Sugestion sugestion;

    @Column()
    private String text;

    @Override
    public void update(Answer source) {
        if (!source.getText().isBlank()) this.setText(source.getText());
        if (source.getSugestion() != null) this.setSugestion(source.getSugestion());
    }
}
