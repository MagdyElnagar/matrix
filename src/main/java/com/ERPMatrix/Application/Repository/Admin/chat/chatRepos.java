package com.ERPMatrix.Application.Repository.Admin.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.chat.chatModel;

@Repository
public interface chatRepos extends JpaRepository<chatModel, Long> {

	List<chatModel> findByIsactive(boolean status);

	chatModel findBySenderAndReciver(String sender, String reciver);

	chatModel findByUsername(String username);

}
