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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SemfourFragment extends Fragment {

    private CardView sem4_card_1,sem4_card_2;
    private CheckBox checkBox_e1_sem4,checkBox_e2_sem4;
    Button button3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate (R.layout.fragment_sem4,container,false);


        sem4_card_1 = rootView.findViewById (R.id.card_1);
        sem4_card_2 = rootView.findViewById (R.id.card_2);
        checkBox_e1_sem4= rootView.findViewById (R.id.checkBox_e1_sem4);
        checkBox_e2_sem4= rootView.findViewById (R.id.checkBox_e2_sem4);
        button3 = rootView.findViewById (R.id.next_Sem_4);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("/SubjectDetails/MobileAppDevlopment/Sem_Subject/Subject_listing");
        ref.child ("Sem_4").addValueEventListener (new ValueEventListener () {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot subjectsnapshot : dataSnapshot.getChildren ()) {

                    if (subjectsnapshot.getKey ().equals ("S1")) {
                        checkBox_e1_sem4.setText (subjectsnapshot.getValue ().toString ());
                    }
                    if (subjectsnapshot.getKey ().equals ("S2")) {
                        checkBox_e2_sem4.setText (subjectsnapshot.getValue ().toString ());
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

        button3.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (checkBox_e1_sem4.isChecked () && checkBox_e2_sem4.isChecked ()) {
                    // Toast.makeText (getContext (),"Testing",Toast.LENGTH_SHORT).show ();
                    Intent intent = new Intent (SemfourFragment.this.getActivity (), Submission.class);
                    intent.putExtra ("cb1", checkBox_e1_sem4.getText ().toString ());
                    intent.putExtra ("cb2", checkBox_e2_sem4.getText ().toString ());
                    startActivity (intent);
                }
            }
        });

        return rootView;
    }
}




