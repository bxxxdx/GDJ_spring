package com.bs.spring.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendMessage {
	private String type;
	private String sender;
	private String receiver;
	private String msg;
	private String room;
}
