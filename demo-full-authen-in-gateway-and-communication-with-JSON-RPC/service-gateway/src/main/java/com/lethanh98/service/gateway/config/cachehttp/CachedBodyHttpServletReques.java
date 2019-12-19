package com.lethanh98.service.gateway.config.cachehttp;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.InputStream;

/**
 * author lethanh9398 <p>
 * email lethanh9398@gmail.com <p>
 * create Date 12/19/2019 7:51 PM  <p>
 * document : <p>
 * <p>- vi :
 * <p>- en :
 */
public class CachedBodyHttpServletReques extends HttpServletRequestWrapper {

    private byte[] cachedBody;

    public CachedBodyHttpServletReques(HttpServletRequest request) throws IOException {
        super(request);
        InputStream requestInputStream = request.getInputStream();
        this.cachedBody = StreamUtils.copyToByteArray(requestInputStream);
    }

    public byte[] getCachedBody() {
        return cachedBody;
    }

    public void setCachedBody(byte[] cachedBody) {
        this.cachedBody = cachedBody;
    }
    @Override
    public ServletInputStream getInputStream() {
        return new CachedBodyServletInputStream(this.cachedBody);
    }
}
