package com.nelioalves.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	// metodo para buscar todos os usuarios [FIND ALL]
	public List<User> findAll() {
		return repo.findAll();

	}

	// metodo para buscar usuarios por Id [FIND BY ID]
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	// metodo para inserir [INSERT]
	public User insert(User obj) {
		return repo.insert(obj);
	}

	// metodo para deletar [DELETE]
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	// metodo para atualizar com [PUT]
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	// metodo para atualizar com [PUT]
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
