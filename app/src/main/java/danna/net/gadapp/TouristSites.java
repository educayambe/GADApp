package danna.net.gadapp; /**
 * Created by javier on 10/18/2014.
 */

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TouristSites {
    private static final String ns = null;
    List<Site> entries = new ArrayList<Site>();
    public List<Site> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }
    private List<Site> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {


        parser.require(XmlPullParser.START_TAG, ns, "Sites");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the Site tag
            if (name.equals("Site")) {
                entries.add(readSite(parser));
            }
        }
        return entries;
    }
    // This class represents a single Site (post) in the XML feed.
    // It includes the data members "title," "link," and "summary."

    // Parses the contents of an Site. If it encounters a title, summary, or link tag, hands them
    // off
    // to their respective &quot;read&quot; methods for processing. Otherwise, skips the tag.
    private Site readSite(XmlPullParser parser) throws XmlPullParserException, IOException {
          Site nuevo ;
         parser.require(XmlPullParser.START_TAG, ns, "Site");
         String Name="";
         String Desc="";
         String Location="";
         String How="";
         String[] Services={""};
         String[] Contacts={""};
         String Image_name="";
         Integer Sector=0;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("Name")) {
                Name = readName(parser);
            } else if (name.equals("Desc")) {
                Desc = readDesc(parser);
            } else if (name.equals("Location")) {
                Location = readLocation(parser);
            }else if (name.equals("How")) {
                   How = readHow(parser);
            } else if (name.equals("Services")) {
                    Services = readServices(parser);
            } else if (name.equals("Contacts")) {
                Contacts = readContacts(parser);
            } else if (name.equals("Image")) {
                Image_name = readImage(parser);
            }
            else if (name.equals("Sector")) {
                Sector = readSector(parser);
            }
        }
        nuevo = new Site(Name, Desc, Location,How,Services,Contacts, Image_name,Sector);
        return nuevo;
    }
    private String readName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Name");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Name");
        return title;
    }
    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    private String readDesc(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Desc");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Desc");
        return title;
    }

    private String readHow(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "How");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "How");
        return title;
    }
    private String readLocation(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Location");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Location");
        return title;
    }private String[] readServices(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Services");
        String Detail = readText(parser);
        String detalles[] = Detail.split(Pattern.quote("|"));
        parser.require(XmlPullParser.END_TAG, ns, "Services");
        return detalles;

    }
    private String[] readContacts(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Contacts");

        String Detail = readText(parser);
        String detalles[] = Detail.split(Pattern.quote("|"));
        parser.require(XmlPullParser.END_TAG, ns, "Contacts");
        return detalles;
    }
    private String readImage(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Image");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Image");
        return title;
    }
    private Integer readSector(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "Sector");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "Sector");
        return Integer.parseInt(title);
    }

    public ArrayList<Site> GetSitesbySector(Integer Sector){
        ArrayList<Site> sites =  new ArrayList<Site>();

        for (Integer i=0;i<entries.size();i++){
            if(entries.get(i).getSector()==Sector) {
                sites.add(entries.get(i));
            }
        }
        return sites;
    }
}
