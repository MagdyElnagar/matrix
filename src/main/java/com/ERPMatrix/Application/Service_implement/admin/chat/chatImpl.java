package com.ERPMatrix.Application.Service_implement.admin.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ERPMatrix.Application.Model.chat.chatModel;
import com.ERPMatrix.Application.Model.chat.msgModel;
import com.ERPMatrix.Application.Repository.Admin.chat.chatRepos;
import com.ERPMatrix.Application.Service.admin.chat.chatServ;

@Service
public class chatImpl implements chatServ {

	@Autowired
	private chatRepos chatrepo;

	@Override
	public void deleteChat(chatModel chatModel) {
		// TODO Auto-generated method stub
		chatrepo.delete(chatModel);

	}

	@Override
	public List<chatModel> findByisactive(boolean isActive) {
		// TODO Auto-generated method stub
		return chatrepo.findByIsactive(isActive);
	}

	@Override
	public chatModel findBysenderAndReciver(chatModel chatModel) {
		// TODO Auto-generated method stub
		return chatrepo.findBySenderAndReciver(chatModel.getSender(), chatModel.getReciver());
	}

	@Override
	public chatModel updateOrAddChat(chatModel chatModel, msgModel msg) {
		// TODO Auto-generated method stub
		// cheak if

		return chatModel;
	}

	@Override
	public void updateStatus(String username) {

		chatModel chat = chatrepo.findByUsername(username);

		if (chat.isIsactive()) {
			chat.setIsactive(false);
		} else {
			chat.setIsactive(true);

		}

		chatrepo.save(chat);

	}

}
