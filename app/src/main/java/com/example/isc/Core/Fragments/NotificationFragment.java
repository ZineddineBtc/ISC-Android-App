package com.example.isc.Core.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.isc.Core.MyNotification;
import com.example.isc.Core.MyUser;
import com.example.isc.R;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {


    private ListView notificationListView;
    private NotificationListAdapter notificationListAdapter;
    private ArrayList<MyNotification> notificationArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_notification, container, false);
        MyUser myUser0 = new MyUser(R.drawable.img0, "Zineddine", "Head");
        MyUser myUser1 = new MyUser(R.drawable.img0, "Mohamed", "Member");
        MyUser myUser2 = new MyUser(R.drawable.img0, "Islem", "Head");
        MyUser myUser3 = new MyUser(R.drawable.img0, "Bettouche", "Member");

        final MyNotification myNotification0 = new MyNotification(
                myUser0,
                "added a photo in the department of communication",
                "20 minutes ago"
        );
        final MyNotification myNotification1 = new MyNotification(
                myUser1,
                "added workshop idea in the department of HR",
                "7 minutes ago"
        );
        final MyNotification myNotification2 = new MyNotification(
                myUser2,
                "liked a photo in the department of logistics",
                "2 hours ago"
        );
        final MyNotification myNotification3 = new MyNotification(
                myUser3,
                "added a photo in the department of communication",
                "9 minutes ago"
        );


        notificationArrayList = new ArrayList<MyNotification>(){{
            add(myNotification0);
            add(myNotification1);
            add(myNotification2);
            add(myNotification3);
        }};

        notificationListAdapter = new NotificationListAdapter(getContext(), R.layout.activity_notification_list_adapter, notificationArrayList);
        notificationListView = fragmentView.findViewById(R.id.notificationListView);
        notificationListView.setAdapter(notificationListAdapter);
        notificationListView.setClickable(true);
        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return fragmentView;
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public NotificationFragment() {

    }
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
}