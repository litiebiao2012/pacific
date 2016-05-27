package com.pacific.common.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpClientResponse {

	private HttpResponse httpResponse;
	private boolean hasException;
    private InputStream bodyInputStream;
	private Exception ex;
	
	HttpClientResponse(HttpResponse httpResponse, InputStream bodyInputStream, Exception exception) {
		super();
		this.httpResponse = httpResponse;
		if( exception!=null){
			this.hasException =true;
		}
		this.bodyInputStream=bodyInputStream;
		this.ex=exception;
	}

	
    public String getBody() {
		return getBody(Consts.UTF_8.toString());
	}

	public String getBody(String charset) {
		if(bodyInputStream==null){
			return null;
		}
		try {
			String body =  IOUtils.toString(bodyInputStream, charset);
			return body;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public InputStream getInputStream(){
		return bodyInputStream;
	}
	
	public int status(){
		if(httpResponse==null){
			return -1;
		}
		return httpResponse.getStatusLine().getStatusCode();
	}
	
	public byte[] getBytes(){
		InputStream in=getInputStream();
		if(in==null){
			return null;
		}
		try {
			return IOUtils.toByteArray(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 如果这个请求发生异常，则抛出，如果没有，则什么都不做
	 * @throws Exception 
	 */
	public HttpClientResponse ifHasExceThrow() throws Exception{
		if(ex!=null){
			throw ex;
		}
		return this;
	}
	public Map<String,String> getHeaderMaps(){
		if(httpResponse==null){
			return null;
		}
		Header[] headers=httpResponse.getAllHeaders();
		Map<String,String> headerMap=new HashMap<String, String>();
		for(Header header:headers){
			headerMap.put(header.getName(), header.getValue());
		}
		return headerMap;
	}
	
	public Header[] getHeaders(){
		if(httpResponse==null){
			return null;
		}
		Header[] headers=httpResponse.getAllHeaders();
		return headers;
	}
	
	public long getContentLength(){
		if(httpResponse==null){
			return -1;
		}
		HttpEntity httpEntity = httpResponse.getEntity();
		return httpEntity.getContentLength();
	}
	
	

	public boolean isHasException() {
		return hasException;
	}

	public static Builder custom() {
		return new Builder();
	}

	public static class Builder {
		private HttpResponse httpResponse;
		private Exception ex;
		private InputStream bodyInputStream;

		public HttpResponse getHttpResponse() {
			return httpResponse;
		}

		public Builder setHttpResponse(HttpResponse httpResponse) {
			this.httpResponse = httpResponse;
			return this;
		}

	
		public Exception getEx() {
			return ex;
		}

		public void setEx(Exception ex) {
			this.ex = ex;
		}

		public HttpClientResponse build() {
			return new HttpClientResponse(httpResponse,bodyInputStream, ex);
		}

        public InputStream getBodyInputStream() {
            return bodyInputStream;
        }

        public void setBodyInputStream(InputStream bodyInputStream) {
            this.bodyInputStream = bodyInputStream;
        }

        
        
	}
}
