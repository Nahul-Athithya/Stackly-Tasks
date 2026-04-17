package com.BackendTask.BackendTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BackendTask.BackendTask.entity.Students;

public interface StudentsRepo extends JpaRepository<Students, Long>{

}
