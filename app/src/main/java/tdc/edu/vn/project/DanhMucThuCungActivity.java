package tdc.edu.vn.project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class DanhMucThuCungActivity extends Fragment {
    LinearLayout llCho;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_danh_muc_thu_cung);
//
}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_danh_muc_thu_cung, null);
        setControl(view);
        setEvent();

        return view;
    }

    private void setControl(View view) {
        llCho =  (LinearLayout) view.findViewById(R.id.llCho);
    }
    private void setEvent() {
        llCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachThuCungActivity.class);
                startActivity(intent);
            }
        });
    }
}
