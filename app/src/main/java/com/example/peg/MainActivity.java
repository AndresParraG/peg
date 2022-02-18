package com.example.peg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private PegGame peg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peg = new PegGame();
    }

    public void onClick(View view) {
        peg.clickPeg(view);
        update();
    }

    public void reset(View view) {
        peg = new PegGame();
        update();
    }

    public void update() {
        for (int i=0; i<peg.getBoard().length; i++) {
            for (int j=0; j<peg.getBoard()[0].length; j++) {
                ImageView imageView = getImageViewID(i, j);
                if (peg.getBoard()[i][j] == 0) {
                    imageView.setImageResource(R.drawable.empty);
                } else if (peg.getBoard()[i][j] == 1) {
                    imageView.setImageResource(R.drawable.blue_peg);
                } else if (peg.getBoard()[i][j] == 2){
                    imageView.setImageResource(R.drawable.selected_peg);
                }
            }
        }
    }

    public ImageView getImageViewID(int i, int j) {
        String viewID = "view"+i+j;
        int resID = getResources().getIdentifier(viewID, "id", getPackageName());
        return findViewById(resID);
    }
}