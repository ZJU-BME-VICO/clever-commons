package edu.zju.bme.clever.commons.rest;

import java.util.Map;

import org.apache.http.HttpHost;
import org.springframework.web.client.RestTemplate;

public class RestClientSpringImpl implements RestClient {

	private final RestTemplate template;
	private static final String HTTP_PREFIX = "http://";

	private String userName;
	private String password;
	private String server;
	private int port;
	private String contextPath;

	public RestClientSpringImpl(String server, int port, String contextPath,
			String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.server = server;
		this.port = port;
		this.contextPath = contextPath;
		HttpHost host = new HttpHost(this.server, this.port, "http");
		AuthenticatedHttpComponentsClientHttpRequestFactory factory = new AuthenticatedHttpComponentsClientHttpRequestFactory(
				host, this.userName, this.password);
		factory.setConnectTimeout(60000);
		factory.setReadTimeout(120000);
		this.template = new RestTemplate(factory);
	}

	private String getFullUrl(String url) {
		return HTTP_PREFIX + this.server + ":" + this.port + "/"
				+ this.contextPath + url;
	}

	@Override
	public <T> T get(String url, Class<T> responseType, Object... urlVariables) {
		return this.template.getForObject(this.getFullUrl(url), responseType,
				urlVariables);
	}

	@Override
	public <T> T get(String url, Class<T> responseType,
			Map<String, ?> urlVariables) {
		return this.template.getForObject(this.getFullUrl(url), responseType,
				urlVariables);
	}

	@Override
	public <T> T post(String url, Object data, Class<T> responseType) {
		return this.template.postForEntity(this.getFullUrl(url), data,
				responseType).getBody();
	}

}
