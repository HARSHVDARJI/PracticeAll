package com.project.harsh.precticeall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by harsh on 13/2/17.
 */

public class PassValid extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pass_valid,container, false);
        Button passnext = (Button)view.findViewById(R.id.passnext);
        final EditText password = (EditText)view.findViewById(R.id.pass);
        TextView email_F = (TextView)view.findViewById(R.id.display_email);

        final String passemail = getArguments().getString("key");
        email_F.setText(passemail);

        passnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String passstr = password.getText().toString();
                if (!isValidPassword(passstr)) {
                    password.setError("Invalid Password");
                }else {
                    DataPage dataPage = new DataPage();
                    FragmentManager fm = getFragmentManager();
                    Bundle bundles = new Bundle();
                    FragmentTransaction ft = fm.beginTransaction();

                    bundles.putString("key1", passstr);
                    bundles.putString("key", passemail);
                    dataPage.setArguments(bundles);

                    password.setText("");
                    ft.replace(R.id.activity_main, dataPage);
                    ft.addToBackStack("");
                    ft.commit();
                }
            }
        });
        return view;
    }

    private boolean isValidPassword(String passstr) {
        if (passstr != null && passstr.length() > 6) {
            return true;
        }
        return false;
    }
}
