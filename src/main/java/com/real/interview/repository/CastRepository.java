package com.real.interview.repository;

import com.real.interview.entity.CastEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRepository extends JpaRepository<CastEntity, Long> {
    @Query("SELECT c FROM CastEntity c WHERE c.name = :name")
    CastEntity getCastByName(@Param("name") String name);
}
