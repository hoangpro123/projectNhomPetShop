package tdc.edu.vn.project.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import tdc.edu.vn.project.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThongTinUser extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_thongtinuser, container, false);
    }
}
