package com.lab365.app.fmtm3firsttask.datasource.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

@Data
@DynamicUpdate
@MappedSuperclass
public abstract class BaseEntity<T> extends Auditable implements Serializable, IEntity<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
