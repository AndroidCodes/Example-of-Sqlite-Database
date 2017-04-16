package com.example.peacock.androidsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bean.Contact;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    EditText et_challan_no, et_mapping, et_party_name, et_rate, et_total, et_truck_no;
    Button btn_Save, btn_List;
    int id = 0;
    String challan_no = "", mapping, party_name, rate, total, truck_no;
    private ListItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_challan_no = (EditText) findViewById(R.id.et_challan_no);

        et_mapping = (EditText) findViewById(R.id.et_mapping);
        et_mapping.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b && !TextUtils.isEmpty(et_mapping.getText().toString()) &&
                        !TextUtils.isEmpty(et_rate.getText().toString())) {

                    et_total.setText(String.valueOf(Integer.parseInt(et_mapping.getText().
                            toString()) * Integer.parseInt(et_rate.getText().toString())));

                }
            }
        });

        et_party_name = (EditText) findViewById(R.id.et_party_name);

        et_rate = (EditText) findViewById(R.id.et_rate);
        et_rate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {

                if (!b && !TextUtils.isEmpty(et_mapping.getText().toString()) &&
                        !TextUtils.isEmpty(et_rate.getText().toString())) {

                    et_total.setText(String.valueOf(Integer.parseInt(et_mapping.getText().
                            toString()) * Integer.parseInt(et_rate.getText().toString())));

                }
            }
        });

        et_total = (EditText) findViewById(R.id.et_total);

        et_truck_no = (EditText) findViewById(R.id.et_truck_no);

        btn_Save = (Button) findViewById(R.id.btn_Save);
        btn_List = (Button) findViewById(R.id.btn_List);

        Bundle b = getIntent().getExtras();
        if (b != null) {

            btn_Save.setText("Edit");

            id = b.getInt("id");
            challan_no = b.getString("challanno");
            if (challan_no == null) {

                challan_no = "";

            }
            mapping = b.getString("mapping");
            party_name = b.getString("partyname");
            rate = b.getString("rate");
            truck_no = b.getString("truckno");

            et_challan_no.setText(challan_no);
            et_mapping.setText(mapping);
            et_party_name.setText(party_name);
            et_rate.setText(rate);
            et_total.setText(String.valueOf(Integer.parseInt(mapping) * Integer.parseInt(rate)));
            et_truck_no.setText(truck_no);

            btn_Save.setText("Update");

            /*edit_name.setText(strName);
            edit_phone.setText(strPhone);*/

            //Toast.makeText(this,""+id + strName + strPhone,Toast.LENGTH_LONG).show();
        } else {

        }

        final DatabaseHandler db = new DatabaseHandler(this);

        btn_Save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (!challan_no.equals("") && challan_no.length() > 0) {

                    Contact con = new Contact();
                    con.set_id(id);
                    con.set_Date(DatePickerFragment.getDate());
                    con.set_ChallanNo(et_challan_no.getText().toString());
                    con.set_Mapping(et_mapping.getText().toString());
                    con.set_PartyName(et_party_name.getText().toString());
                    con.set_Rate(et_rate.getText().toString());
                    con.set_Total(et_total.getText().toString());
                    con.set_TruckNo(et_truck_no.getText().toString());

                    db.updateContact(con);

                    Toast.makeText(MainActivity.this, "Record update Sucessfully.",
                            Toast.LENGTH_LONG).show();

                    et_challan_no.setText("");
                    et_mapping.setText("");
                    et_party_name.setText("");
                    et_rate.setText("");
                    et_total.setText("");
                    et_truck_no.setText("");

                } else {

                    Contact con = new Contact();
                    id = id + 1;
                    con.set_id(id);
                    con.set_Date(DatePickerFragment.getDate());
                    con.set_ChallanNo(et_challan_no.getText().toString());
                    con.set_Mapping(et_mapping.getText().toString());
                    con.set_PartyName(et_party_name.getText().toString());
                    con.set_Rate(et_rate.getText().toString());
                    con.set_Total(et_total.getText().toString());
                    con.set_TruckNo(et_truck_no.getText().toString());

                    db.addContact(con);

                    Toast.makeText(MainActivity.this, "Record added Sucessfully.",
                            Toast.LENGTH_LONG).show();

                    et_challan_no.setText("");
                    et_mapping.setText("");
                    et_party_name.setText("");
                    et_rate.setText("");
                    et_total.setText("");
                    et_truck_no.setText("");

                }
            }
        });

        btn_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(MainActivity.this, ListoneActivity.class);

                startActivity(ii);

            }
        });
    }

    public void showDatePickerDialog(View v) {

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    @Override
    public void onFocusChange(View view, boolean b) {

        switch (view.getId()) {

            case R.id.et_rate:

            case R.id.et_mapping:

                if (!b && !TextUtils.isEmpty(et_mapping.getText().toString()) &&
                        !TextUtils.isEmpty(et_rate.getText().toString())) {

                    et_total.setText(String.valueOf(Integer.parseInt(et_mapping.getText().
                            toString()) * Integer.parseInt(et_rate.getText().toString())));

                }

                break;

            default:

                break;

        }
    }
}
