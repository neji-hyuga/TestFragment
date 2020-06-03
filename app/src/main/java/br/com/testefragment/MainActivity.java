package br.com.testefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachFragments(new FirstFragment(), new SecondFragment());

        Button firstButton = findViewById(R.id.first_fragment_button);
        Button secondButton = findViewById(R.id.second_fragment_button);
        Button thirdButton = findViewById(R.id.invert_button);

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showsFirstFragment();
            }
        });
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showsSecondFragment();
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invertFragments();
            }
        });
    }

    private void showsSecondFragment() {
        attachFragments(new SecondFragment(), new SecondFragment());
    }

    private void showsFirstFragment() {
        attachFragments(new FirstFragment(), new FirstFragment());
    }

    private void invertFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        Fragment fragmentSuperior = getFragmentInvert(fragments.get(0));
        Fragment fragmentInferior = getFragmentInvert(fragments.get(1));

        attachFragments(fragmentSuperior, fragmentInferior);
    }

    private Fragment getFragmentInvert(Fragment fragment) {
        if (fragment instanceof FirstFragment) {
            return new SecondFragment();
        } else {
            return new FirstFragment();
        }
    }

    private void attachFragments(Fragment fragmentSuperior, Fragment fragmentInferior) {

        //creates manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        //begin transaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //put fragment on screen
        transaction.replace(R.id.container_id, fragmentSuperior);
        transaction.replace(R.id.outro_container_id, fragmentInferior);

        //start
        transaction.commit();
    }

}
