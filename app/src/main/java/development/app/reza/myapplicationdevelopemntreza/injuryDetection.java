package development.app.reza.myapplicationdevelopemntreza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.List;

import data.injuryDB;

public class injuryDetection extends AppCompatActivity {
    //create a que
    List<Question> quesList;
    int result = 0;
    int Questions_id = 0;
    Question currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc;
    Button butNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.injury_detection_activity);
        injuryDB db = new injuryDB(this);
        quesList = db.getAllQuestions();
        currentQ = quesList.get(Questions_id);
        txtQuestion = (TextView) findViewById(R.id.textView1);
        rda = (RadioButton) findViewById(R.id.radio0);
        rdb = (RadioButton) findViewById(R.id.radio1);
        rdc = (RadioButton) findViewById(R.id.radio2);
        butNext = (Button) findViewById(R.id.button1);
        //next question cycle
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                grp.clearCheck();
                Log.d("your answer", currentQ.getANSWER() + " " + answer.getText());
                if (currentQ.getANSWER().equals(answer.getText())) {
                    result++;
                    Log.d("score", "Your score" + result);
                }
                if (Questions_id < 5) {
                    currentQ = quesList.get(Questions_id);
                    setQuestionView();
                } else {
                    Intent intent = new Intent(injuryDetection.this, injureResult.class);
                    Bundle b = new Bundle();
                    b.putInt("Result", result); //Your score
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
    //sets the questions
    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        Questions_id++;
    }
}
