package development.app.reza.myapplicationdevelopemntreza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;

public class injureResult extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.injury_result_activity);
        //get rating bar object withs is invisible
        RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);
        //get text view
        TextView t=(TextView)findViewById(R.id.Result);
        //get score
        Bundle b = getIntent().getExtras();
        int score= b.getInt("Result");
        //display score
        bar.setRating(score);
        switch (score)
        {
            case 0: t.setText("you are not injured");
                break;
            case 1: t.setText("you are not injured but be carefull");
                break;
            case 2: t.setText("You are injured get some help");
                break;
            case 3:t.setText("you are very injured go see your GP");
                break;
        }
    }
}
