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
    LinearLayout llCho, llMeo, llChim, llCa, llBoSat, llKhac;
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
        llCho =  view.findViewById(R.id.llCho);
        llMeo = view.findViewById(R.id.llMeo);
        llChim = view.findViewById(R.id.llChim);
        llBoSat = view.findViewById(R.id.llBoSat);
        llCa = view.findViewById(R.id.llCa);
        llKhac = view.findViewById(R.id.llKhac);
    }
    private void setEvent() {
        llCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachThuCungActivity.class);
                intent.putExtra("Loại", "Cho");
                startActivity(intent);
            }
        });
        llMeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachThuCungActivity.class);
                intent.putExtra("Loại", "Meo");
                startActivity(intent);
            }
        });
        llChim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachThuCungActivity.class);
                intent.putExtra("Loại", "Chim");
                startActivity(intent);
            }
        });
        llCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachThuCungActivity.class);
                intent.putExtra("Loại", "Ca");
                startActivity(intent);
            }
        });
        llBoSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachThuCungActivity.class);
                intent.putExtra("Loại", "Bo sat");
                startActivity(intent);
            }
        });
        llKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachThuCungActivity.class);
                intent.putExtra("Loại", "Khac");
                startActivity(intent);
            }
        });
    }
}
