package com.mmm.ztp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.mmm.ztp.counter.Counter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class GameOver extends Activity {
	
	 public static final String MY_PREFERENCES = "ZTP";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final GameOver obj=this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gameover);
		Button save=(Button) this.findViewById(R.id.Button01);
		final SharedPreferences preferences = getSharedPreferences(MY_PREFERENCES, Activity.MODE_PRIVATE);;
		final SharedPreferences.Editor edit=preferences.edit();
		final String[] top10=new String[10];
		final Integer[] top10val=new Integer[10];
		for(int i=0;i<10;i++)
		{
			top10[i]=(preferences.getString(String.valueOf(i), "Nikt"));
			top10val[i]=(preferences.getInt("V"+String.valueOf(i), 0));
		}
		final ListView lista=(ListView)this.findViewById(R.id.listView1);
		final ArrayList<String> dane = new ArrayList<String>();  
		dane.addAll(Arrays.asList(top10));
		
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, dane);
		
		final EditText name=(EditText)this.findViewById(R.id.editText1);
		
		lista.setAdapter(listAdapter);
		
		final long stan=Counter.getInstance().get();
		 
		save.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				for(int i=0;i<10;i++)
					if(top10val[i]<stan)
					{
						Log.d("GameOver", String.valueOf(stan));
						top10val[i]=(int) stan;
						top10[i]=name.getText().toString();
						lista.refreshDrawableState();
						i=10;
					}
				for(int i=0;i<10;i++)
				{
					edit.putString(String.valueOf(i), top10[i]);
					edit.putInt("V"+String.valueOf(i), top10val[i]);
					edit.apply();
				}
				dane.clear();
				dane.addAll(Arrays.asList(top10));
				
			}
			
		}
		);
		 
		
	}


}
