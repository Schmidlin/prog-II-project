package ch.fhnw.project.DataHandling;

import java.io.*;

/**
 * Created by fabienne on 16.05.16.
 */
public interface DataConverter {

    public Data read(File file)throws IOException;

}
