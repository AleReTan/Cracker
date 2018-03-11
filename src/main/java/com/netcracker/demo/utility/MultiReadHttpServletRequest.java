package com.netcracker.demo.utility;

import org.apache.commons.compress.utils.BoundedInputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

//Думала как обойти запрет с получением тела запроса
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    public static final Integer MAX_BYTE_SIZE = 1_048_576; // 1 MB

    private String body;

    public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        body = "";

        InputStream bounded = new BoundedInputStream(request.getInputStream(), MAX_BYTE_SIZE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bounded));

        String line;
        while ((line = bufferedReader.readLine()) != null){
            body += line;
        }
    }

    @Override
    public ServletInputStream getInputStream() throws       IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());

        return new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}