package sse.error.sample.app

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.sse.Event
import io.reactivex.Flowable

@Controller
class SseController {

	var outputToken: String? = ""

	@Get("/stream", processes = [MediaType.TEXT_EVENT_STREAM])
	fun stream(@QueryValue("token") token: String?) : Flowable<Event<Map<String, Long>>> {
		this.outputToken = token
		return Flowable.just(Event.of(mapOf("time" to System.currentTimeMillis())))
	}

}