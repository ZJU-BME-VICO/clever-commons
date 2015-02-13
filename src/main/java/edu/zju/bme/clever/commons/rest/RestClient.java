package edu.zju.bme.clever.commons.rest;

import java.util.Map;

public interface RestClient {

	public <T> T get(String url, Class<T> responseType, Object... urlVariables);

	public <T> T get(String url, Class<T> responseType,
			Map<String, ?> urlVariables);

}