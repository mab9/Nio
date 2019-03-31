package ch.mab.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public class Files {

    private static final int BUFFER = 1024;


    public static void copyNio(Path src, Path dest) throws IOException {

        FileInputStream fin = new FileInputStream(src.toString());
        FileOutputStream fout = new FileOutputStream(dest.toString());

        FileChannel channelIn = fin.getChannel();
        FileChannel channelOut = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER);

        while (channelIn.read(buffer) >= 0) {
            buffer.flip();

            channelOut.write(buffer);
            buffer.clear();
        }
    }

    public static void copyIo(Path src, Path dest) throws IOException {

        FileInputStream fin = new FileInputStream(src.toString());
        FileOutputStream fout = new FileOutputStream(dest.toString());

        byte buffer[] = new byte[BUFFER];

        while (fin.read(buffer) >= 0) {
            fout.write(buffer);
        }
    }

    public static void copyNioMappedByteBuffer(Path src, Path dest) throws IOException {

        RandomAccessFile rafS = new RandomAccessFile(src.toString(), "rw");
        RandomAccessFile rafD = new RandomAccessFile(dest.toString(), "rw");

        FileChannel channelIn = rafS.getChannel();
        FileChannel channelOut = rafD.getChannel();

        MappedByteBuffer buffer = channelIn.map(FileChannel.MapMode.READ_WRITE, 0, BUFFER);

        while (channelIn.read(buffer) >= 0) {
            buffer.flip();

            channelOut.write(buffer);
            buffer.clear();
        }
    }
}
