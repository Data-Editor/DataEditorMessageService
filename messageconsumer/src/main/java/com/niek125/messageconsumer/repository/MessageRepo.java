package com.niek125.messageconsumer.repository;

import com.niek125.messageconsumer.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepo extends JpaRepository<Message, String> {
    @Transactional
    @Modifying
    @Query("delete from message m where m.projectid = :projectid")
    void deleteByProjectid(@Param("projectid") String projectid);
}
