package gpxLib;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GPSFileLoader {

    private final String DATE_FORMAT = "YYYY-MM-DDTHH:MM:SS.000Z";

    private GPSFile gpsfile;
    private ArrayList<Trackpoint> trackpoints;

    private Time startTime;
    private GregorianCalendar date;

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
    public GPSFileLoader(File file) throws InvalidFileException{
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
    public GPSFileLoader(String filepath) throws InvalidFileException{
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
            String timeString = trkpt.getElementsByTagName("time").item(0).getTextContent();
            Time time = new Time(timeString);
            Trackpoint trackpoint = new Trackpoint(lat, lon, ele, time);

            //Add generated trackpoint to list
            trackpoints.add(trackpoint);
        }
        Element trkpt = (Element) trkpts.item(0);
        parseDateAndStartTime(trkpt.getElementsByTagName("time").item(0).getTextContent());
        connectTrackpoints();
    }

    private void parseDateAndStartTime(String date){
        this.startTime = new Time(date);
        int year = 44;
        int month = 44;
        int day = 44;
        final String DATA_PATTERN = "[\\d]{4}[-][\\d]{2}[-][\\d]{2}";
        final String YYYY_PATTERN = "[\\d]{4}";
        final String MMDD_PATTERN = "[\\d]{2}";
        Pattern datePattern = Pattern.compile(DATA_PATTERN);
        Matcher dateMatcher = datePattern.matcher(date);
        if(dateMatcher.find()){
            String date_String = dateMatcher.group(0);
            String[] split = date_String.split("\\-");
            year = Integer.parseInt(split[0]);
            month = Integer.parseInt(split[1]);
            day = Integer.parseInt(split[2]);
        }
        System.out.println(""+year+month+day);
        this.date = new GregorianCalendar(year, month, day);
    }

    /**
     * Helper method, to connect trackpoints with each other.
     */
    private void connectTrackpoints() {
        for(int i = 0; i < trackpoints.size()-1; i++)
            trackpoints.get(i).setNext(trackpoints.get(i+1));
        writeGPSFile();
    }

    /**
     * Helper method to write the gpsfile.
     */
    private void writeGPSFile(){
        gpsfile = new GPSFile(trackpoints, date);
    }

    /**
     * Returns an Arraylist of Trackpoints from the file loaded.
     * @return      An Arraylist of Trackpoints from the file loaded.
     */
    public GPSFile getGPSFile(){
        return this.gpsfile;
    }
}
