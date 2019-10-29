package tdc.edu.vn.project.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import tdc.edu.vn.project.InforUserActivity;
import tdc.edu.vn.project.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ThongTinUser extends Fragment {

    Button btnChinhSua;
    TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_thongtinuser, null);
        setControl(view);
        setEvent();


        // Inflate the layout for this fragment
        return view;
    }

    private void setControl(View view) {
        btnChinhSua = (Button) view.findViewById(R.id.btnFix);
        //textView = (TextView)view.findViewById(R.id.id);
    }

    private void setEvent() {
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InforUserActivity.class);
//                String a = intent.getStringExtra("id");
//                textView.setText(a);
                startActivity(intent);
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null){
            textView.setText(bundle.getString("id"));
        }
    }
}
