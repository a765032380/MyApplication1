package com.example.gll.myapplication.activity;


import com.example.gll.myapplication.base.AppActivity;
import com.example.gll.myapplication.base.BaseFragment;
import com.example.gll.myapplication.fragment.HomeFragment;

public class MainActivity extends AppActivity {

    @Override
    protected BaseFragment getFirstFragment() {
        return HomeFragment.newInstance();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
