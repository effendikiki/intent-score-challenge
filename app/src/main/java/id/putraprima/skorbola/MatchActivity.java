package id.putraprima.skorbola;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import static id.putraprima.skorbola.MainActivity.KEY;
import static id.putraprima.skorbola.ScorerActivity.DATA_SEND_LANJUT;

public class MatchActivity extends AppCompatActivity {

    private TextView mTxtHome;
    private ImageView mHomeLogo;
    private TextView mScoreHome;
    private TextView mTxtAway;
    private ImageView mAwayLogo;
    private TextView mScoreAway;
    private Bitmap homeBitmap, awayBitmap;
    private int homeScore = 0;
    private int awayScore = 0;

//    public static final String WINNER_KEY = "winner_key";
//    public static final String SCORER = "scorer";
    public static final String  DATA_KEY = "data_key";
    public static final String DATA_KEY_LAST = "data_key_last";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",

        mTxtHome = findViewById(R.id.txt_home);
        mHomeLogo = findViewById(R.id.home_logo);
        mScoreHome = findViewById(R.id.score_home);
        mTxtAway = findViewById(R.id.txt_away);
        mAwayLogo = findViewById(R.id.away_logo);
        mScoreAway = findViewById(R.id.score_away);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            data data = extras.getParcelable(KEY);

            mTxtHome.setText(data.getHomeName());
            mTxtAway.setText(data.getAwayName());

            try {
                homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getHomeUri());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getAwayUri());
            } catch (IOException e) {
                e.printStackTrace();
            }

            mHomeLogo.setImageBitmap(homeBitmap);
            mAwayLogo.setImageBitmap(awayBitmap);

        }

    }

    public void AddHomeScore(View view) {

        Bundle extras = getIntent().getExtras();
        data data = extras.getParcelable(KEY);

        data.setHomeScore(data.getHomeScore() + 1);
        mScoreHome.setText(Integer.toString(data.getHomeScore()));

        data data_send = new data(data.getHomeName(), data.getAwayName(), data.getHomeUri(), data.getAwayUri(), data.getHomeScore(), data.getAwayScore(), data.getScorerName());

        Intent intent = new Intent(this, ScorerActivity.class);
        intent.putExtra(DATA_KEY, data_send);
        startActivity(intent);

    }

    public void AddAwayScore(View view) {
        awayScore +=1 ;
        mScoreAway.setText(Integer.toString(awayScore));
    }






    public void handleResult(View view) {

        Bundle extras = getIntent().getExtras();
        data data = extras.getParcelable(DATA_SEND_LANJUT);

        data data_send = new data(data.getHomeName(), data.getAwayName(), data.getHomeUri(), data.getAwayUri(), data.getHomeScore(), data.getAwayScore(), data.getScorerName());


//        scorer scorer_name = extras.getParcelable(SCORER_KEY);

//        String nameHomeTeam = data.getHomeName() ;
//        String nameAwayTeam = data.getAwayName();
        String nameDrawTeam = "Draw";

//        String scorerName = scorer_name.getScorer_name();

        Intent intentResult = new Intent(this, ResultActivity.class);

        if (homeScore > awayScore ){
            intentResult.putExtra(DATA_KEY_LAST, data_send);
            startActivity(intentResult);
        }
        else if(homeScore == awayScore){
            intentResult.putExtra(DATA_KEY, nameDrawTeam);
//            intentResult.putExtra(KEY, data);
            startActivity(intentResult);
        }
        else {
//            intentResult.putExtra(WINNER_KEY, nameAwayTeam);
            intentResult.putExtra(KEY, data_send);
            startActivity(intentResult);
        }

    }
}
