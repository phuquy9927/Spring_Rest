package vn.hoidanit.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.todo.entity.Todo;
import vn.hoidanit.todo.repository.TodoRepository;

@Service
public class TodoService {
	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public Todo getTodoById(Long id) {
		Optional<Todo> todoOptional = this.todoRepository.findById(id);
		return todoOptional.isPresent() ? todoOptional.get() : null;
	}

	public Todo handleCreateTodo(Todo todo) {

		Todo createdTodo = this.todoRepository.save(todo);
		return createdTodo;
	}

	public List<Todo> handleGetTodo() {
		return this.todoRepository.findAll();
	}

	public void handleUpdateTodo(Long id, Todo inputTodo) {

		Optional<Todo> todoOptional = this.todoRepository.findById(id);
		if (todoOptional.isPresent()) {
			Todo currentTodo = todoOptional.get();

			currentTodo.setCompeleted(inputTodo.isCompeleted());
			currentTodo.setUsername(inputTodo.getUsername());

			this.todoRepository.save(currentTodo);
		}
	}

	public void DeleteATodo(Long id) {
		this.todoRepository.deleteById(id);

	}
}
