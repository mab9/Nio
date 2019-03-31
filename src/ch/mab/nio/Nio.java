package ch.mab.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Nio {

    public static void main(String[] args) throws IOException {

        Path src = Path.of("/home/mab/Desktop/test/Jit.java");
        Path dest = Path.of("/home/mab/Desktop/Jit.java");
        executeTests(src, dest);

        System.out.println("---");

        src = Path.of("/home/mab/Desktop/test/dsp.7z");
        dest = Path.of("/home/mab/Desktop/dsp.7z");
        executeTests(src, dest);
    }

    private static void executeTests(Path src, Path dest) throws IOException {
        System.out.println("File size: " + new File(src.toString()).length() / 1024 + " Kb");
        copyWithNioMappedByteBuffer(src, dest);
        copyWithNio(src, dest);
        copyWithIo(src, dest);
    }

    private static void copyWithNio(Path src, Path dest) throws IOException {
        long start = System.currentTimeMillis();
        Files.copyNio(src, dest);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("NIO Elapsed time: " + timeElapsed + "ms");
    }

    private static void copyWithIo(Path src, Path dest) throws IOException {
        long start = System.currentTimeMillis();
        Files.copyIo(src, dest);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;


        System.out.println("IO  Elapsed time: " + timeElapsed + "ms");
    }

    private static void copyWithNioMappedByteBuffer(Path src, Path dest) throws IOException {
        long start = System.currentTimeMillis();
        Files.copyNioMappedByteBuffer(src, dest);
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;


        System.out.println("NIO MappedByteBuffer  Elapsed time: " + timeElapsed + "ms");
    }
}
