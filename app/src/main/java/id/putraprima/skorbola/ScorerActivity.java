package id.putraprima.skorbola;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static id.putraprima.skorbola.MainActivity.KEY;
import static id.putraprima.skorbola.MatchActivity.DATA_KEY;

public class ScorerActivity extends AppCompatActivity {

    private EditText textScorer;

    public static final String DATA_SEND_LANJUT = "data_send_lanjut";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);

        textScorer = findViewById(R.id.editText);

    }

    public void HandleScorer(View view) {

        Bundle extras = getIntent().getExtras();
        data data = extras.getParcelable(DATA_KEY);

        String output_textScorer = textScorer.getText().toString();

//        scorer scorer_name = new scorer(output_textScorer);
        Intent intentScorer = new Intent(this, MatchActivity.class);

        data data_send = new data(data.getHomeName(), data.getAwayName(), data.getHomeUri(), data.getAwayUri(), data.getHomeScore(), data.getAwayScore(), output_textScorer);


        intentScorer.putExtra(DATA_SEND_LANJUT, data_send);
        startActivity(intentScorer);


    }
}
