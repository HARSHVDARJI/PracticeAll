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
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by harsh on 13/2/17.
 */

public class EmailVerificaltion extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.email_input,container, false);
        Button mailbutton = (Button)view.findViewById(R.id.emailnext);
        final EditText email_fld = (EditText)view.findViewById(R.id.email);

        mailbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PassValid passValid = new PassValid();

                String mailadd = email_fld.getText().toString();
                if (!isValidEmail(mailadd)) {
                    email_fld.setError("Invalid Email");
                }else {

                    FragmentManager fm = getFragmentManager();
                    Bundle bundle = new Bundle();
                    FragmentTransaction ft = fm.beginTransaction();
                    bundle.putString("key", mailadd);
                    passValid.setArguments(bundle);

                    email_fld.setText("");
                    ft.replace(R.id.activity_main, passValid);
                    ft.addToBackStack("");
                    ft.commit();
                }
            }

            private boolean isValidEmail(String mailadd) {
                    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                    Matcher matcher = pattern.matcher(mailadd);
                    return matcher.matches();
            }
        });
        return view;
    }

    
}
