package com.example.eventHandler;

import com.example.helper.ServiceKeyGetter;
import com.example.service.ReplyMessageService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.*;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import java.util.Map;

@LineMessageHandler
public class MessageEventHandler {
	private final ServiceKeyGetter serviceKeyGetter;
	private final Map<String, ReplyMessageService> replyMessageServiceMap;

	public MessageEventHandler(
			ServiceKeyGetter serviceKeyGetter,
			Map<String, ReplyMessageService> replyMessageServiceMap
	) {
		this.serviceKeyGetter = serviceKeyGetter;
		this.replyMessageServiceMap = replyMessageServiceMap;
	}

	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		String key = serviceKeyGetter.getServiceKey(event.getMessage().getText());
		ReplyMessageService replyMessageService = replyMessageServiceMap.get(key);
		replyMessageService.replyText(event);
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
