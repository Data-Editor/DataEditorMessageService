package com.Niek125.messageservice.repository;

import com.Niek125.messageservice.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, String> {
}
