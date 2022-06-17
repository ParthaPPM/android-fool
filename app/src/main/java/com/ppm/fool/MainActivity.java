package com.ppm.fool;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
	int screenHeight;
	int screenWidth;
	TextView textView;
	Button yesButton;
	Button noButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// make fullscreen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getSupportActionBar().hide();
		setContentView(R.layout.activity_main);

		textView = findViewById(R.id.text);
		yesButton = findViewById(R.id.button_yes);
		noButton = findViewById(R.id.button_no);

		RelativeLayout parentLayout = findViewById(R.id.parent_layout);
		parentLayout.post(() -> setButtonSize(parentLayout.getMeasuredHeight(), parentLayout.getMeasuredWidth()));

		yesButton.setOnClickListener(v -> onYesClick());
		noButton.setOnClickListener(v -> onNoClick());
	}

	private void setButtonSize(int height, int width)
	{
		this.screenHeight = height;
		this.screenWidth = width;
		int buttonWidth = yesButton.getWidth();

		//calculating the distance between button and screen edge
		int d = (int) ((width - (2 * buttonWidth)) / 4.0);

		// setting the position of the buttons
		yesButton.setX(d);
		noButton.setX(width - d - buttonWidth);
	}

	private void onYesClick()
	{
		textView.setText("I knew it!!");
		yesButton.setVisibility(View.GONE);
		noButton.setVisibility(View.GONE);
	}

	private void onNoClick()
	{
		int maxHeight = screenHeight - yesButton.getHeight();
		int maxWidth = screenWidth - yesButton.getWidth();

		// finding random height and width
		Random random = new Random();
		int randomHeight = random.nextInt(maxHeight);
		int randomWidth = random.nextInt(maxWidth);

		// setting the position of no button
		noButton.setX(randomWidth);
		noButton.setY(randomHeight);
	}
}