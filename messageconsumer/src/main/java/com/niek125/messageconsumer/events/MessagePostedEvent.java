package com.niek125.messageconsumer.events;

import com.niek125.messageconsumer.models.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MessagePostedEvent extends DataEditorEvent {
    private Message message;
}
