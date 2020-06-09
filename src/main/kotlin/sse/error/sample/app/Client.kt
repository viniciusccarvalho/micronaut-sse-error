package sse.error.sample.app

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import io.micronaut.http.sse.Event
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Client("/")
interface SseClient {

	@Get(value = "/stream", processes = [MediaType.TEXT_EVENT_STREAM])
	fun stream() : Flowable<Event<Map<String, Any>>>
}

@Singleton
class TokenFilter : HttpClientFilter {
	private val logger = LoggerFactory.getLogger(TokenFilter::class.java)
	override fun doFilter(request: MutableHttpRequest<*>, chain: ClientFilterChain): Publisher<out HttpResponse<*>> {
		request.parameters.add("token","fooToken")
		logger.info("uri is ${request.uri}")
		return chain.proceed(request)
	}


}