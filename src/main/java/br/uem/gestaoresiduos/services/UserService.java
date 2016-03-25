package br.uem.gestaoresiduos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uem.gestaoresiduos.entities.Role;
import br.uem.gestaoresiduos.entities.User;
import br.uem.gestaoresiduos.repositories.UserRepository;


@Service
@Transactional
public class UserService{
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleService roleService;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User findUserById(int id) {
		return userRepository.findOne(id);
	}

	public User login(String email, String password) {
		return userRepository.findByEmailAndPassword(email,password);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(int id) {
		userRepository.delete(id);
	}

	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	public List<User> findByRole(String role) {
		Role roleObj = roleService.findByRoleName(role);
		return userRepository.findByRoles(roleObj);
	}
}
