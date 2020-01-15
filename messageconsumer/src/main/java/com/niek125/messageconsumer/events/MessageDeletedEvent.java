package com.niek125.messageconsumer.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MessageDeletedEvent extends DataEditorEvent {
    private String messageid;
}
