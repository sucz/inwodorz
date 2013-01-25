package com.mmm.ztp.lvl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;

import com.mmm.ztp.util.Util;


public class LvLBuilder implements Iterable<Object> 
{

	InputStream in;
	LinkedList<String> lvls;
	List<Integer> lvlsFileIds;
	
	
	public LvLBuilder(List<Integer> lvlsFileIds, Context context)
	{
		this.lvlsFileIds = lvlsFileIds;
		
		in = context.getResources().openRawResource(lvlsFileIds.get(0));
		
		
		
		/*// Standard of reading a XML file
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setNamespaceAware(true);
	    DocumentBuilder builder;
	    Document doc = null;
	    XPathExpression expr = null;
	    builder = factory.newDocumentBuilder();
	    doc = builder.parse(configPath);
	    
	    // Create a XPathFactory
	    XPathFactory xFactory = XPathFactory.newInstance();

	    // Create a XPath object
	    XPath xpath = xFactory.newXPath();
		
	    // Compile the XPath expression
	    expr = xpath.compile("//Levels/LvLFile/text()");
	    // Run the query and get a nodeset
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    
	    // Cast the result to a DOM NodeList
	    NodeList nodes = (NodeList) result;
	    for (int i=0; i<nodes.getLength();i++){
	      Log.d("LvLReader", nodes.item(i).getNodeValue());
	    }*/
	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
			return new Iterator<Object>() {
				
				
				public boolean hasNext()
				{
					return true;
				}
				
				public Object next()
				{
					//event = eventReader.nextEvent();
					throw new UnsupportedOperationException();
				}
				
				public void remove()
				{
					throw new UnsupportedOperationException();
				}
			};
	}
	
	
	
	
	
}
