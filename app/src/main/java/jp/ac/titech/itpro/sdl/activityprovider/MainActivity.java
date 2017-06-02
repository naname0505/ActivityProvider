package jp.ac.titech.itpro.sdl.activityprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static int MYREQCODE = 1234;

    TextView answerView;
    RadioGroup requestGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answerView = (TextView) findViewById(R.id.answer_view);
        requestGroup = (RadioGroup) findViewById(R.id.request_group);
        requestGroup.check(R.id.request_1);
    }


    public void onClickStartButton(View view) {
        String request = null;
        switch (requestGroup.getCheckedRadioButtonId()) {
            case R.id.request_1:
                request = getString(R.string.request_1_text);
                break;
            case R.id.request_2:
                request = getString(R.string.request_2_text);
                break;
            case R.id.request_3:
                request = getString(R.string.request_3_text);
                break;
        }
//        Intent intent = new Intent(this, PublicActivity.class);
  //      intent.putExtra("request", request);
        // Create the text message with a string
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, request);
        sendIntent.setType("text/plain");
        startActivityForResult(sendIntent, MYREQCODE);
    }

    @Override
    protected void onActivityResult(int reqCode, int resCode, Intent data) {
        super.onActivityResult(reqCode, resCode, data);
        switch (reqCode) {
            case MYREQCODE:
                if (resCode == RESULT_OK) {
                    String request = data.getStringExtra("request");
                    answerView.setText(request);
                }
        }
    }
}
