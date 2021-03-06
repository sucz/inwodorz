package com.mmm.ztp.lvl;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import com.mmm.ztp.R;
import com.mmm.ztp.drawable.impl.TexturedObjectFactory;
import com.mmm.ztp.gameobjects.ships.EnemyShip;

public class LvLHandler extends DefaultHandler{
	
	private Level lvl = null;
	private Stage stage = null;
	private EnemyShip enemyShip = null;
	
//	private Boolean bInStage = false;
//	private Boolean bInEnemyShip = false;

	
	public Level getLevel()
	{
		return lvl;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		Log.d("LvLHandler", "characters");
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		Log.d("LvLHandler", "end document");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		Log.d("LvLHandler", "end element: "+localName);
		
		if(localName.equals("Stage"))
		{
			lvl.getStages().add(stage);
			//bInStage = false;
		}
		else if(localName.equals("EnemyShip"))
		{
			stage.getShips().add(enemyShip);
			//bInEnemyShip = false;
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		Log.d("LvLHandler", "start document");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		Log.d("LvLHandler", "start element: "+localName);
		
		if(localName.equals("Level"))
		{
			lvl = new Level();
			lvl.setName(attributes.getValue("name"));
		}
		else if(localName.equals("Stage"))
		{
			stage = new Stage();
			stage.setName(attributes.getValue("name"));
			//bInStage = true;
		}
		else if(localName.equals("EnemyShip"))
		{
			//bInEnemyShip = true;
			
			Integer coordX = Integer.parseInt(attributes.getValue("coordX"));
			Integer coordY = Integer.parseInt(attributes.getValue("coordY"));
			Integer hp = Integer.parseInt(attributes.getValue("hp"));
			Float speed = Float.parseFloat(attributes.getValue("moveSpeed"));
			Float size = Float.parseFloat(attributes.getValue("size"));
			Integer dmg = Integer.parseInt(attributes.getValue("dmg"));
			Integer moveId = Integer.parseInt(attributes.getValue("move"));
			
			enemyShip = new EnemyShip(TexturedObjectFactory.get(R.drawable.enemy_scout, size.intValue()));
			enemyShip.setMovement(moveId);
			enemyShip.setCoordinates(coordX, coordY, 0);
			enemyShip.setSpeed(speed);
			enemyShip.setSize(size);
			enemyShip.setHp(hp);
			
			
			String bonusType = null;
			if((bonusType = attributes.getValue("bonusType")) != null)
			{
				Integer bonusValue = Integer.parseInt(attributes.getValue("bonusValue"));
				enemyShip.setBonusType(bonusType);
				enemyShip.setBonusValue(bonusValue);
			}
		}
	}
}
