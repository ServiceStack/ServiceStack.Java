package net.servicestack.cookies;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpCookie;

//FROM: https://gist.github.com/manishk3008/2a2373c6c155a5df6326
public class SerializableCookie  implements Serializable {
    private HttpCookie cookie;
    private static final long serialVersionUID = 2532101328282342578L;
    public SerializableCookie(HttpCookie cookie) {
        this.cookie = cookie;
    }

    public HttpCookie getCookie() {
        return cookie;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(cookie.getName());
        out.writeObject(cookie.getValue());
        out.writeObject(cookie.getComment());
        out.writeObject(cookie.getCommentURL());
        out.writeBoolean(cookie.getDiscard());
        out.writeObject(cookie.getDomain());
        out.writeLong(cookie.getMaxAge());
        out.writeObject(cookie.getPath());
        out.writeObject(cookie.getPortlist());
        out.writeBoolean(cookie.getSecure());
        out.writeInt(cookie.getVersion());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        cookie = new HttpCookie(name, value);
        cookie.setComment((String) in.readObject());
        cookie.setCommentURL((String) in.readObject());
        cookie.setDiscard(in.readBoolean());
        cookie.setDomain((String) in.readObject());
        cookie.setMaxAge(in.readLong());
        cookie.setPath((String) in.readObject());
        cookie.setPortlist((String) in.readObject());
        cookie.setSecure(in.readBoolean());
        cookie.setVersion(in.readInt());
    }
}
