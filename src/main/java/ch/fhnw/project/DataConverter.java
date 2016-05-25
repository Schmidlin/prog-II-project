package ch.fhnw.project;

import java.io.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fabienne on 16.05.16.
 */
interface DataConverter {

    public Data read(File file)throws IOException;

}
