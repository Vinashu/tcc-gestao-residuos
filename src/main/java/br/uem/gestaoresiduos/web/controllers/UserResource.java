package br.uem.gestaoresiduos.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.uem.gestaoresiduos.entities.User;
import br.uem.gestaoresiduos.services.UserService;

/**
 * @author Siva
 *
 */
@Controller
@RequestMapping("api/users/")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="user", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Principal user(Principal user){
		return user;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> findAll()
	{
		return userService.findAll();
	}
	
	@RequestMapping(value="{role}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> findAllTecnicosColeta(@PathVariable("role") String role){
		return userService.findByRole(role);
	}
	
	@RequestMapping(value="user/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User findUser(@PathVariable("id") int id) {
		return userService.findUserById(id);
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.create(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User savedUser = userService.update(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
}