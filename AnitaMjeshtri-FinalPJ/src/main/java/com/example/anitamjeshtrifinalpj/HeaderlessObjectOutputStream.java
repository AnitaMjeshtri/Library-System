package com.example.anitamjeshtrifinalpj;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
public class HeaderlessObjectOutputStream extends ObjectOutputStream {
    public HeaderlessObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    public void writeStreamHeader()
    {}
}
