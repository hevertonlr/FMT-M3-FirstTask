package com.lab365.app.fmtm3firsttask.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Data
@Entity(name="sugestion")
@DynamicUpdate
@Table(name = "sugestion")
public class Sugestion extends BaseEntity<Sugestion> {
    @Column()
    private String title;

    @Column()
    private String description;


    @JsonIgnoreProperties("sugestion")
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "sugestion", fetch = FetchType.EAGER)
    private List<Answer> answers;

    @Override
    public void update(Sugestion source) {
        if (!source.getTitle().isBlank()) this.setTitle(source.getTitle());
        if (!source.getDescription().isBlank()) this.setDescription(source.getDescription());
    }
}
