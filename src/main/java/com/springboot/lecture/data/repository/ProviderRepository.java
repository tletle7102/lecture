package com.springboot.lecture.data.repository;

import com.springboot.lecture.data.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
