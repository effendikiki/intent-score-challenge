package id.putraprima.skorbola;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText mHomeTeam;
    private EditText mAwayTeam;
    private ImageView mHomeLogo;
    private ImageView mAwayLogo;
    private Bitmap homeBitmap, awayBitmap;
    private Uri homeUri, awayUri;

    public static final String KEY = "key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO Done
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
        mHomeTeam = findViewById(R.id.home_team);
        mAwayTeam = findViewById(R.id.away_team);
        mHomeLogo = findViewById(R.id.home_logo);
        mAwayLogo = findViewById(R.id.away_logo);

    }

    public void HandleNext(View view) {

        String output_mHomeTeam = mHomeTeam.getText().toString();
        String output_mAwayTeam = mAwayTeam.getText().toString();

        if (!(output_mHomeTeam).equals("") && !(output_mAwayTeam).equals("")) {
            data data_send = new data(output_mHomeTeam, output_mAwayTeam, homeUri, awayUri, 0, 0, null);
            Intent intent = new Intent(this, MatchActivity.class);

            intent.putExtra(KEY, data_send);
            startActivity(intent);

        }
        else {
            Toast.makeText(this, "Isi semua Data!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    homeUri = data.getData();
                    homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
                    mHomeLogo.setImageBitmap(homeBitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == 2){
            if (data != null) {
                try {
                    awayUri = data.getData();
                    awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
                    mAwayLogo.setImageBitmap(awayBitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    public void handleChangeHomeTeamImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleChangeAwayTeamImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);

    }


}
