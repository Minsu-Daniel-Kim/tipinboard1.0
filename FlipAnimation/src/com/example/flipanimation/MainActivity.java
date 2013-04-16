package com.example.flipanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewAnimator;

import com.example.flipanimation.AnimationFactory.FlipDirection;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewAnimator viewAnimator = (ViewAnimator) this.findViewById(R.id.viewFlipper);
        viewAnimator.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				AnimationFactory.flipTransition(viewAnimator, FlipDirection.LEFT_RIGHT);
				
			}
		});
    }


}
