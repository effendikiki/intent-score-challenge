package id.putraprima.skorbola;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static id.putraprima.skorbola.MainActivity.KEY;
import static id.putraprima.skorbola.MatchActivity.DATA_KEY;
//import static id.putraprima.skorbola.MatchActivity.SCORER;
//import static id.putraprima.skorbola.MatchActivity.WINNER_KEY;
import static id.putraprima.skorbola.MatchActivity.DATA_KEY_LAST;
//import static id.putraprima.skorbola.ScorerActivity.SCORER_KEY;

public class ResultActivity extends AppCompatActivity {


    private TextView mTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mTextView3 = findViewById(R.id.textView3);

//        scorer scorer = new scorer(SCORER);

        Bundle extras = getIntent().getExtras();
        data data = extras.getParcelable(DATA_KEY_LAST);

//        data data_send = new data(data.getHomeName(), data.getAwayName(), data.getHomeUri(), data.getAwayUri(), data.getHomeScore(), data.getAwayScore(), data.getScorerName());

        mTextView3.setText(data.getScorerName());

//        mTextView3.setText(scorer.getScorer_name());

    }
}
