package sse.error.sample.app

import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
class SseControllerTest {

	@Inject
	lateinit var sseClient: SseClient

	@Inject
	lateinit var sseController: SseController

	@Test
	fun testSseToken() {
		val stream = sseClient.stream().blockingFirst()
		println(sseController.outputToken)
	}

}