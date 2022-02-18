package dev_allgot.understand.doitmission_20;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ArticleParser {
    public ArrayList<Item> parse(InputStream inStream) throws XmlPullParserException, IOException {
        try {
            ArrayList<Item> items = new ArrayList<Item>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(inStream, null);

            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "rss");

            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, "channel");

            while(parser.next() != XmlPullParser.END_TAG) {
                if(parser.getEventType() != XmlPullParser.START_TAG) continue;
                String name = parser.getName();
                if(name.equals("item")) items.add(readItem(parser));
                else skip(parser);
            }

            return items;
        } finally {
            inStream.close();
        }
    }

    private Item readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "item");
        String title = null;
        String description = null;
        while(parser.next() != XmlPullParser.END_TAG) {
            if(parser.getEventType() != XmlPullParser.START_TAG) continue;
            String name = parser.getName();
            if(name.equals("title")) title = readTitle(parser);
            else if(name.equals("description")) description = readDescription(parser);
            else skip(parser);
        }

        return new Item(title, description);
    }

    private String readTitle(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "title");
        String title = null;
        if(parser.next() == XmlPullParser.TEXT) {
            title = parser.getText();
            parser.nextTag();
        }
        parser.require(XmlPullParser.END_TAG, null, "title");

        return title;
    }

    private String readDescription(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "description");
        String description = null;
        if(parser.next() == XmlPullParser.TEXT) {
            description = parser.getText();
            parser.nextTag();
        }
        parser.require(XmlPullParser.END_TAG, null, "description");

        return description;
    }

    private void skip(XmlPullParser parser) throws  XmlPullParserException, IOException {
        if(parser.getEventType() != XmlPullParser.START_TAG) throw new IllegalStateException();

        int depth = 1;
        while(depth != 0) {
            switch(parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;

                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
