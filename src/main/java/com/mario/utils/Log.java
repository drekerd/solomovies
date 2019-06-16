package com.mario.utils;

import sun.java2d.pipe.SpanShapeRenderer;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    public Logger logger;
    FileHandler fileHandler;


    public Log(String fileName) throws IOException {

        File file = new File(fileName);

        if(!file.exists()){
            file.createNewFile();
        }

        fileHandler = new FileHandler(fileName, true);
        logger = Logger.getLogger("test");
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    public Log() throws IOException {

        logger = Logger.getLogger("test");
    }
}
