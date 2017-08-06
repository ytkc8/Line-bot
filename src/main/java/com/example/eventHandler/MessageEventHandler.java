package com.example.eventHandler;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class MessageEventHandler {
    @EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		System.out.println("event: " + event);
		return new TextMessage(event.getMessage().getText());
	}

	@EventMapping
	public TextMessage handleStickerMessage(MessageEvent<StickerMessageContent> event) {
		return new TextMessage("スタンプ送信ありがとうございます！");
	}

	@EventMapping
	public TextMessage handleImageMessage(MessageEvent<ImageMessageContent> event) {
		return new TextMessage("画像送信ありがとうございます！");
	}

	@EventMapping
	public TextMessage handleVideoMessage(MessageEvent<VideoMessageContent> event) {
		return new TextMessage("動画送信ありがとうございます！");
	}

	@EventMapping
	public TextMessage handleAudioMessage(MessageEvent<AudioMessageContent> event) {
		return new TextMessage("音声送信ありがとうございます！");
	}

	@EventMapping
	public TextMessage handleFollowEvent(FollowEvent event) {
		return new TextMessage("友達追加ありがとうございます！");
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}
}
