package ypc.world.bestnote.WriteNote.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ypc.world.bestnote.R;

public class MainActivity extends AppCompatActivity implements ViewSample{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWhiteBoard();
    }
    
    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    public void toMainActivity() {

    }


    private void initWhiteBoard()
    {

    }

}
