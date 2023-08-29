package br.com.oivato.todolist;

import br.com.oivato.todolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("Todo 1", "Descrição Todo 1", true, 1);

		webTestClient
			.post()
			.uri("/todos")
				.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].name").isEqualTo(todo.getName())
				.jsonPath("$[0].description").isEqualTo(todo.getDescription())
				.jsonPath("$[0].accomplished").isEqualTo(todo.isAccomplished())
				.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}

	@Test
	void testCreateTodoFailure() {
		webTestClient
			.post()
			.uri("/todos")
				.bodyValue(new Todo("", "", false, 0))
			.exchange()
			.expectStatus().isBadRequest();
	}
}
