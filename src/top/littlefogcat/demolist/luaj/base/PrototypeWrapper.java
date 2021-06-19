package top.littlefogcat.demolist.luaj.base;

import org.luaj.vm2.Prototype;

import java.io.Serializable;

public class PrototypeWrapper implements Serializable {
    private Prototype prototype;

    public PrototypeWrapper(Prototype prototype) {
        this.prototype = prototype;
    }
}
