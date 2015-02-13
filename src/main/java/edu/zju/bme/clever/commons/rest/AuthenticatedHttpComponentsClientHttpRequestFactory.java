package edu.zju.bme.clever.commons.rest;

import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class AuthenticatedHttpComponentsClientHttpRequestFactory extends
		HttpComponentsClientHttpRequestFactory {
	
	protected HttpHost host;
	protected String userName;
	protected String password;

	public AuthenticatedHttpComponentsClientHttpRequestFactory(HttpHost host) {
		this(host, null, null);
	}

	public AuthenticatedHttpComponentsClientHttpRequestFactory(HttpHost host,
			String userName, String password) {
		super();
		this.host = host;
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);

		// Add AuthCache to the execution context
		HttpClientContext localcontext = HttpClientContext.create();
		localcontext.setAuthCache(authCache);

		if (userName != null) {
			BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(host),
					new UsernamePasswordCredentials(userName, password));
			localcontext.setCredentialsProvider(credsProvider);
		}
		return localcontext;
	}
}
