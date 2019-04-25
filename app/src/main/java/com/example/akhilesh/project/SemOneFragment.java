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



public class SemOneFragment extends Fragment {

  // private TextView elect_1,elect_2,Choosed_subject;
   private CardView card_1,card_2;
   private CheckBox checkBox_e1_sem1,checkBox_e2_sem1;
   Button button;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate (R.layout.fragment_sem1,container,false);

        card_1 = rootView.findViewById (R.id.card_1);
        card_2 = rootView.findViewById (R.id.card_2);
        checkBox_e1_sem1= rootView.findViewById (R.id.checkBox_e1_sem1);
        checkBox_e2_sem1= rootView.findViewById (R.id.checkBox_e2_sem1);
        button = rootView.findViewById (R.id.next_sem_1);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/SubjectDetails/MobileAppDevlopment/Sem_Subject/Subject_listing");
        ref.child ("Sem_1").addValueEventListener (new ValueEventListener () {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot subjectsnapshot : dataSnapshot.getChildren ()) {

                    if (subjectsnapshot.getKey ().equals ("S1")) {
                        checkBox_e1_sem1.setText (subjectsnapshot.getValue ().toString ());
                    }
                    if (subjectsnapshot.getKey ().equals ("S2")) {
                        checkBox_e2_sem1.setText (subjectsnapshot.getValue ().toString ());
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

        button.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                // Toast.makeText (getContext (),"Testing",Toast.LENGTH_SHORT).show ();
                if (checkBox_e1_sem1.isChecked () && checkBox_e2_sem1.isChecked ()) {
                    Intent intent = new Intent (SemOneFragment.this.getActivity (), Submission.class);
                    intent.putExtra ("cb1", checkBox_e1_sem1.getText ().toString ());
                    intent.putExtra ("cb2", checkBox_e2_sem1.getText ().toString ());
                    startActivity (intent);
                }

            }
        });

        return rootView;
    }
}





