package vn.hoidanit.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vn.hoidanit.todo.entity.User;
import vn.hoidanit.todo.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	// fake
	@Mock
	private UserRepository userRepository;

	// UserRepository(fake) => userSerive
	@InjectMocks
	private UserService userSerive;

	@Test
	public void createUser_shouldReturnUser_whenEmailValid() {
		// arrange
		User inputUser = new User(null, "eric", "qui@gmail.com");
		User outputUser = new User(1L, "eric", "qui@gmail.com");

		when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(false);

		when(this.userRepository.save(any())).thenReturn(outputUser);

		// act
		User result = this.userSerive.createUser(inputUser);

		// assert:so sánh
		assertEquals(1L, result.getId());
	}

	@Test
	public void createUser_shouldThrowException_whenEmailInvalid() {
		// arrange
		User inputUser = new User(null, "eric", "qui@gmail.com");

		when(this.userRepository.existsByEmail(inputUser.getEmail())).thenReturn(true);

		// act
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			this.userSerive.createUser(inputUser);
		});

		// assert:so sánh
		assertEquals("Email already exists", ex.getMessage());

	}

	@Test
	public void getAllUsers_shouldReturnAllUsers() {
		// arrange
		List<User> outputUsers = new ArrayList<>();
		outputUsers.add(new User(1L, "eric", "test@gmail.com"));
		outputUsers.add(new User(2L, "eric", "test123@gmail.com"));

		when(this.userRepository.findAll()).thenReturn(outputUsers);

		// act
		List<User> result = this.userSerive.getAllUsers();

		// assert:so sánh
		assertEquals(2, result.size());
		assertEquals("test@gmail.com", result.get(0).getEmail());

	}

	@Test
	public void getUserById_shouldReturnOptionalUser() {
		// arrange
		Long inputId = 1L;
		User inputUser = new User(1L, "eric", "hoidanit@gmail.com");
		Optional<User> optionalOutput = Optional.of(inputUser);

		when(this.userRepository.findById(inputId)).thenReturn(optionalOutput);

		// act
		Optional<User> result = this.userSerive.getUserById(inputId);

		// assert:so sánh
		assertEquals(true, result.isPresent());

	}

	@Test
	public void deleteUser_shouldReturnVoid_whenUserExist() {
		// arrange
		Long inputId = 1L;
		when(this.userRepository.existsById(inputId)).thenReturn(true);

		// act
		this.userSerive.deleteUser(inputId);

		// assert:so sánh
		verify(this.userRepository).deleteById(inputId);

	}

	@Test
	public void updateUser_shouldReturnUser_whenValid() {
		// arrange
		Long inputId = 1L;
		User inputUser = new User(1L, "old name", "old@gmail.com");
		User outputUser = new User(1L, "new name", "new@gmail.com");

		when(this.userRepository.findById(inputId)).thenReturn(Optional.of(inputUser));
		when(this.userRepository.save(any())).thenReturn(outputUser);

		// act
		User result = this.userSerive.updateUser(inputId, inputUser);

		// assert:so sánh
		assertEquals("new name", result.getName());

	}

}
