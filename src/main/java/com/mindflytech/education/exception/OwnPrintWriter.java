package com.mindflytech.education.exception;

import java.io.*;
import java.nio.charset.Charset;

import static com.mindflytech.util.Utils.percentChance;

public class OwnPrintWriter extends PrintWriter {
    public OwnPrintWriter(Writer out) {
        super(out);
    }

    public OwnPrintWriter(Writer out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public OwnPrintWriter(OutputStream out) {
        super(out);
    }

    public OwnPrintWriter(OutputStream out, boolean autoFlush) {
        super(out, autoFlush);
    }

    public OwnPrintWriter(OutputStream out, boolean autoFlush, Charset charset) {
        super(out, autoFlush, charset);
    }

    public OwnPrintWriter(String fileName) throws FileNotFoundException {
        super(fileName);
        boolean throwException = percentChance(0.2d);
        if(throwException) {
            throw new FileNotFoundException("-- exception thrown in OwnPrintWriter.<init> just " +
                    "because for the sake of it");
        }
    }

    public OwnPrintWriter(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, csn);
    }

    public OwnPrintWriter(String fileName, Charset charset) throws IOException {
        super(fileName, charset);
    }

    public OwnPrintWriter(File file) throws FileNotFoundException {
        super(file);
    }

    public OwnPrintWriter(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
        super(file, csn);
    }

    public OwnPrintWriter(File file, Charset charset) throws IOException {
        super(file, charset);
    }

    @Override
    public void close() {
        System.out.println("-- AutoClosable.close called on OwnPrintWriter");
        super.close();
    }
}
