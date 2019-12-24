package com.niek125.messageservice.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "message")
public class Message {
    @Id
    private String messageid;
    private String projectid;
    private String senderid;
    private String content;
    private String sendtime;
}
