package gpxLib;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Class to load a .GPX File into a list of trackpoints. GPX is written in xml and DOM library is used to parse.
 *
 * @see GPXTrackpoint
 */
public class GPXFile {

    private ArrayList<Trackpoint> trackpoints;

    private File file;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private Element root;

    /**
     * Loads the given GPX file into trackpoints.
     *
     * @param file                      The file that should be loaded
     * @throws InvalidFileException     Thrown if file is invalid
     */
    public GPXFile(File file) throws InvalidFileException{
        this.file = file;
        trackpoints = new ArrayList<Trackpoint>();
        parseXML();
    }

    /**
     * Loads the File at the given Filepath into trackpoints.
     *
     * @param filepath                  filepath to the file that should be loaded.
     * @throws InvalidFileException     Thrown if file is invalid
     */
    public GPXFile(String filepath) throws InvalidFileException{
        File file = new File(filepath);
        trackpoints = new ArrayList<Trackpoint>();
        parseXML();
    }

    /**
     * Helper method to parse the xml file.
     */
    private void parseXML() {
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (Exception e){
            throw new InvalidFileException("Invalid File!");
        }
        document.getDocumentElement().normalize();
        root = document.getDocumentElement();
        writeTrackPoints();
    }

    /**
     * Helper method to write DOM into Trackpoints
     */
    private void writeTrackPoints(){
        NodeList trkpts = document.getElementsByTagName("trkpt");
        for(int i = 0; i < trkpts.getLength(); i++) {
            Element trkpt = (Element) trkpts.item(i);
            double lat = Double.parseDouble(trkpt.getAttribute("lat"));
            double lon = Double.parseDouble(trkpt.getAttribute("lon"));
            double ele = 0;
            if(trkpt.getElementsByTagName("ele").getLength() > 0)
                ele = Double.parseDouble(trkpt.getElementsByTagName("ele").item(0).getTextContent());
            Time time = new Time(trkpt.getElementsByTagName("time").item(0).getTextContent());
            Trackpoint trackpoint = new Trackpoint(lat, lon, ele, time);
            trackpoints.add(trackpoint);
        }
        connectTrackpoints();
    }

    private void connectTrackpoints() {
        for(int i = 0; i < trackpoints.size()-1; i++)
            trackpoints.get(i).setNext(trackpoints.get(i+1));
    }

    /**
     * Returns an Arraylist of Trackpoints from the file loaded.
     * @return      An Arraylist of Trackpoints from the file loaded.
     */
    public ArrayList<Trackpoint> getTrackpoints(){
        return trackpoints;
    }

}
