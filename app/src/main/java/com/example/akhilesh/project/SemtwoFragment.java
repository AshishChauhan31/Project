package com.example.akhilesh.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SemtwoFragment extends Fragment {

    private TextView sem2_elect_1,sem2_elect_2;
    private CardView sem2card_1,sem2card_2;
    private CheckBox checkBox_e1_sem2,checkBox_e2_sem2;
    Button button1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate (R.layout.fragment_sem2,container,false);

        //sem2_elect_1 = rootView.findViewById (R.id.elect_1);
       // sem2_elect_2 = rootView.findViewById (R.id.elect_2);
        sem2card_1 = rootView.findViewById (R.id.card_1);
        sem2card_2 = rootView.findViewById (R.id.card_2);
        checkBox_e1_sem2= rootView.findViewById (R.id.checkBox_e1_sem2);
        checkBox_e2_sem2= rootView.findViewById (R.id.checkBox_e2_sem2);
        button1 = rootView.findViewById (R.id.next_sem_2);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/SubjectDetails/MobileAppDevlopment/Sem_Subject/Subject_listing");
        ref.child ("Sem_2").addValueEventListener (new ValueEventListener () {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot subjectsnapshot : dataSnapshot.getChildren ()) {

                    if (subjectsnapshot.getKey ().equals ("S1")) {
                                checkBox_e1_sem2.setText (subjectsnapshot.getValue ().toString ());
                    }
                    if (subjectsnapshot.getKey ().equals ("S2")) {
                                checkBox_e2_sem2.setText (subjectsnapshot.getValue ().toString ());
                    }

                }


                    }

                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

            });

        SharedPreferences preferences_object= getActivity ().getPreferences (Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences_object.edit ();
                editor.putBoolean ("checked",true).commit ();
                editor.putBoolean ("checked",true).commit ();

                button1.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText (getContext (),"Testing",Toast.LENGTH_SHORT).show ();
                        if (checkBox_e1_sem2.isChecked () && checkBox_e2_sem2.isChecked ()) {
                            Intent intent = new Intent (SemtwoFragment.this.getActivity (), Submission.class);
                            intent.putExtra ("cb1", checkBox_e1_sem2.getText ().toString ());
                            intent.putExtra ("cb2", checkBox_e2_sem2.getText ().toString ());
                            startActivity (intent);
                        }

                    }
                });

        return rootView;
        }
}