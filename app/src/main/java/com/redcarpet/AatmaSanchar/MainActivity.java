package com.redcarpet.AatmaSanchar;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Bitmap mActionCallIcon, mActionCallLightIcon, mActionBackIcon;
    public String url = "http://158.144.55.73:3478";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        Resources resources = getResources();
        mActionCallIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_action_call);
        mActionCallLightIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_action_call_light);
        mActionBackIcon = BitmapFactory.decodeResource(resources, R.drawable.ic_action_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChromeTab();
            }
        });

        Button credits = (Button) findViewById(R.id.button2);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent secPg = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(secPg);
            }
        });

        TextView secondTextView = (TextView) findViewById(R.id.heading);
        TextPaint paint = secondTextView.getPaint();
        float width = paint.measureText("Aatmanirbhar");

        Shader textShader = new LinearGradient(0, 0, width, secondTextView.getTextSize(),
                new int[]{
                        Color.parseColor("#8446CC"),
                        Color.parseColor("#478AEA"),
//                        Color.parseColor("#64B678"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#F97C3C"),

                }, null, Shader.TileMode.CLAMP);
        secondTextView.getPaint().setShader(textShader);
        secondTextView.setTextColor(Color.parseColor("#8446CC"));
    }


    private void openChromeTab() {

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        // Set the Toolbar color

        builder.setToolbarColor(Color.parseColor("#000000"));

        // Display custom back button

        builder.setCloseButtonIcon(mActionBackIcon);

        // Add custom entrance/exit animations

        builder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        // Add action button

//        Intent actionIntent = new Intent(Intent.ACTION_DIAL);
//        actionIntent.setData(Uri.parse("tel: 1234567890"));
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, actionIntent, 0);
//        builder.setActionButton(mActionCallIcon, "Call", pendingIntent);

        // Add custom menu items

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out Aatmanirbhar-Sanchar: " + url);
        PendingIntent menuIntent = PendingIntent.getActivity(this, 0, shareIntent, 0);
        builder.addMenuItem("Share App", menuIntent);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));



    }

}
