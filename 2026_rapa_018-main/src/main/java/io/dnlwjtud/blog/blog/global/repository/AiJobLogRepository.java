package io.dnlwjtud.blog.blog.global.repository;

import io.dnlwjtud.blog.blog.global.model.AiJobLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiJobLogRepository extends JpaRepository<AiJobLog, Long> {
}
