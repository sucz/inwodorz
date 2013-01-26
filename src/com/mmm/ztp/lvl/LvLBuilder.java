package com.mmm.ztp.lvl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;

import com.mmm.ztp.util.Util;


public class LvLBuilder implements Iterable<Object> 
{
	Context context;
	LinkedList<String> lvls;
	List<Integer> lvlsFileIds;
	XMLReader xmlReader;
	LvLHandler lvlHandler;
	
	
	public LvLBuilder(List<Integer> lvlsFileIds, Context context)
	{
		this.lvlsFileIds = lvlsFileIds;
		this.context = context;
		
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			xmlReader = saxParser.getXMLReader();
			
			lvlHandler = new LvLHandler();
			xmlReader.setContentHandler(lvlHandler);
			
			
		} catch (ParserConfigurationException e) {
			Log.e("LvLBuilder -> ParserConfigurationException", e.getMessage());
			e.printStackTrace();
		} catch (SAXException e) {
			Log.e("LvLBuilder -> SAXException", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
			return new Iterator<Object>() {
				
				private Iterator it = lvlsFileIds.iterator();
				private InputStream in;
				
				public boolean hasNext()
				{
					return it.hasNext();
				}
				
				public Object next()
				{
					try {	
						in = context.getResources().openRawResource((Integer) it.next());
						xmlReader.parse(new InputSource(in));
					} catch (IOException e) {
						Log.e("LvLBuilder -> IOException", e.getMessage());
						e.printStackTrace();
					} catch (SAXException e) {
						Log.e("LvLBuilder -> SAXException", e.getMessage());
						e.printStackTrace();
					}
					
					return lvlHandler.getLevel();
				}
				
				public void remove()
				{
					throw new UnsupportedOperationException();
				}
			};
	}	
	
}
