package gpxLib;

import org.w3c.dom.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.*;
import java.io.*;

public class GPXFile {

    private NodeList waypoints;

    private File file;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document document;
    private Element root;

    public GPXFile(File file) throws InvalidFileException{
        this.file = file;
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (Exception e){
            throw new InvalidFileException("Invalid File!");
        }
        document.getDocumentElement().normalize();
        root = document.getDocumentElement();
        readFile();
    }

    private void readFile(){
        NodeList trkpts = document.getElementsByTagName("trkpt");
        for(int i = 0; i < trkpts.getLength(); i++) {
            Node nNode = trkpts.item(i);
            Element trkpt = (Element) trkpts.item(i);
            String lat = trkpt.getAttribute("lat");
            System.out.println("\nCurrent Element :" + nNode.getNodeName() + " Lat: " + lat);
        }
    }

    public static void main(String[] args){
        File myFile = new File("/Users/nicolasmuller/Downloads/Nicolas_Mller_2018-04-26_18-51-17.gpx");
        GPXFile gpxfile = new GPXFile(myFile);
    }

}
