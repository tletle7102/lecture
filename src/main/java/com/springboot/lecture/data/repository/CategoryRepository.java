package com.springboot.lecture.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<com.springboot.lecture.data.entity.Category, Long> {
}
