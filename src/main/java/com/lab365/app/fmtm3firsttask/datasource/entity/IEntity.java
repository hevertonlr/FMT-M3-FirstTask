package com.lab365.app.fmtm3firsttask.datasource.entity;

public interface IEntity <T>{
    Long getId();
    void update(T source);
}
