package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {
    Sandwich sandwich;
    TextView  AlsoNameAs,plasceOfOrigin,ingredints,deascriptio;
    ImageView ingredientsIv;
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

         ingredientsIv =(ImageView) findViewById(R.id.image_iv);
         AlsoNameAs = (TextView)findViewById(R.id.Also_Name);
         plasceOfOrigin=(TextView)findViewById(R.id.origin_tv);
         ingredints =(TextView)findViewById(R.id.ingredients);
         deascriptio = (TextView)findViewById(R.id.description);

        Intent intent = getIntent();

        if (intent == null) {
           closeOnError();
            Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();

        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
           closeOnError();
            Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();

            return;
        }


        String[] sandwiches;
        sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

         sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();

            return;
        }




     populateUI();

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }


    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        plasceOfOrigin.setText(sandwich.getPlaceOfOrigin());
        deascriptio.setText(sandwich.getDescription());
        AlsoNameAs.setText(sandwich.getAlsoKnownAs().toString());

        ingredints.setText(sandwich.getIngredients().toString());

    }
}
