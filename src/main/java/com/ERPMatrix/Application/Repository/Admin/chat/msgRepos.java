package com.ERPMatrix.Application.Repository.Admin.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.chat.msgModel;

@Repository
public interface msgRepos extends JpaRepository<msgModel, Long> {

}
