package com.springboot.lecture.data.repository;

import com.springboot.lecture.data.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
}
