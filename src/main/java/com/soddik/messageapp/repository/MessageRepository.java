package com.soddik.messageapp.repository;

import com.soddik.messageapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM message ORDER BY id DESC LIMIT :limit", nativeQuery = true)
    List<Message> findLastXMessages(@Param("limit") Integer msgLimit);
}
