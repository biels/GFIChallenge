package gfi.gfichallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdit   = (EditText)findViewById(R.id.code);
        Button scan = (Button) findViewById(R.id.scan);
        scan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan:
                final Intent intent = new Intent(this, FullscreenActivity.class);
                intent.putExtra("pos", mEdit.getText().toString());
                startActivity(intent);
        }
    }

}
