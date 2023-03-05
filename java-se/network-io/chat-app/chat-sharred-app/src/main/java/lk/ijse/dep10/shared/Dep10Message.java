package lk.ijse.dep10.shared;

import java.io.Serializable;

public class Dep10Message implements Serializable { // ------------------------------3
    private Dep10Headers header;
    private  Object body ;

    public Dep10Message() {
    }

    public Dep10Message(Dep10Headers header, Object body) {
        this.header = header;
        this.body = body;
    }

    public Dep10Headers getHeader() {
        return header;
    }

    public void setHeader(Dep10Headers header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
