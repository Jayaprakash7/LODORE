package lodore.com.lodore.Fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import lodore.com.lodore.R;

import static android.support.design.R.id.text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactusFragment extends Fragment {

    ImageView phone_icon,email_icon;
    TextView phn_num,email_addres;
    String subject = "Please enter ur subject here";
    EditText name,email,massage;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("cname",name.getText().toString());
        outState.putString("cemail",email.getText().toString());
        outState.putString("cmassgae",massage.getText().toString());
    }



    public ContactusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactus, container, false);

        name = (EditText) view.findViewById(R.id.contactus_name);
        email = (EditText) view.findViewById(R.id.contactus_email);
        massage = (EditText) view.findViewById(R.id.contactus_massage);



        phone_icon = (ImageView) view.findViewById(R.id.phonecall_icon);
        email_icon = (ImageView) view.findViewById(R.id.email_icon);

        phn_num = (TextView) view.findViewById(R.id.phn_num);
        email_addres = (TextView) view.findViewById(R.id.email_addrs);


        if (savedInstanceState != null){
            name.setText(savedInstanceState.getString("cname"));
            email.setText(savedInstanceState.getString("cemail"));
            massage.setText(savedInstanceState.getString("cmassgae"));
        }



        phone_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:(+966)11 2147777"));
                startActivity(intent);
            }
        });

        email_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ "info@idore.com" });
                i.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                i.putExtra(android.content.Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(i, "Send email"));
            }
        });



        return view;
    }

}
