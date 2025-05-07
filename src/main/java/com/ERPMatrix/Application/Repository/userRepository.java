package com.ERPMatrix.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ERPMatrix.Application.Model.User.User;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByIdIsContaining(Long id);

	User findByUsername(String username);

	User findUserById(Long id);

}
