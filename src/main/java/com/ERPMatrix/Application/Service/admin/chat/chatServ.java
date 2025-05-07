package com.ERPMatrix.Application.Service.admin.chat;

import java.util.List;

import com.ERPMatrix.Application.Model.chat.chatModel;
import com.ERPMatrix.Application.Model.chat.msgModel;

public interface chatServ {

	void deleteChat(chatModel chatModel);

	List<chatModel> findByisactive(boolean isActive);

	chatModel findBysenderAndReciver(chatModel chatModel);

	chatModel updateOrAddChat(chatModel chatModel, msgModel msg);

	public void updateStatus(String username);

}
