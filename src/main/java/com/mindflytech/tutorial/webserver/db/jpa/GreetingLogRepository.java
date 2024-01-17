package com.mindflytech.tutorial.webserver.db.jpa;

import com.mindflytech.tutorial.webserver.db.entity.GreetingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingLogRepository extends JpaRepository<GreetingLog, Integer> {

}
