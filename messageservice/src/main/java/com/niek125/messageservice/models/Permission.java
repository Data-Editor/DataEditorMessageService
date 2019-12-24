package com.niek125.messageservice.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Permission {
    private String projectid;
    private String role;
}
