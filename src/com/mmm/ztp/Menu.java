package com.mmm.ztp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final Menu obj=this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		Button back=(Button)findViewById(R.id.btnBack);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				obj.finish();
			}
		});
		
		
	}


}
