package android.in.th.ungexchang.fragment;

import android.content.DialogInterface;
import android.in.th.ungexchang.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by masterung on 3/12/2017 AD.
 */

public class MainFragment extends Fragment{

    //    Explicit หรือ การประกาศตัวแปร
    private RadioGroup radioGroup;
    private RadioButton usdRadioButton, thbRadioButton;
    private EditText editText;
    private Button button;
    private String moneyString;
    private double factorADouble;
    private static double usdADouble = 33;
    private String unitString;
    private static String string = " thb";


//    สร้างเมธอดหลัก ที่ใชัในการจัดการ การทำงาน
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Initial View
        initialView();

//        Click USD to THB First
        clickUSDToTHBFirst();

//        USD Controller
        USDController();

//        THB Controller
        THBController();

//        Exchange Controller
        exchangeController();

//        Rate Controller
        rateController();

    }   // Main Method

    private void rateController() {

        TextView textView = getView().findViewById(R.id.txtRateExchange);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new ShowRateExchangeFragment())
                        .addToBackStack(null).commit();

            }
        });

    }


    private void exchangeController() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Get Value From EditText
                moneyString = editText.getText().toString().trim();

//                Check Space
                if (moneyString.isEmpty()) {
//                    Have Space
                    myAlertDialog("Have Space",
                            "Please Fill All Blank");
                } else {
//                    NO Space
                    calculate();

                }


            }   // onClick
        });
    }

    private void calculate() {

        String tag = "3DecV1";
        Log.d(tag, "Factor ==> " + factorADouble);

        double answerADouble = Double.parseDouble(moneyString) * factorADouble;
        myAlertDialog("Answer", "Money = " + String.format("%.2f", answerADouble) + unitString);




    }   // calculate

    private void myAlertDialog(String strTitle, String strMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();



    }   // myAlert

    private void THBController() {
        thbRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setHint(getString(R.string.thb));
                factorADouble = 1 / usdADouble;
                unitString = " usd";
            }
        });
    }

    private void USDController() {
        usdRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.setHint(getString(R.string.usd));
                factorADouble = usdADouble;
                unitString = string;

            }   // onClick
        });
    }

    private void clickUSDToTHBFirst() {
        usdRadioButton.setChecked(true);
        factorADouble = usdADouble;
        unitString = string;
    }

    private void initialView() {
        radioGroup = getView().findViewById(R.id.ragMoney);
        usdRadioButton = getView().findViewById(R.id.radUSD);
        thbRadioButton = getView().findViewById(R.id.radTHB);
        editText = getView().findViewById(R.id.edtMoney);
        button = getView().findViewById(R.id.btnExchange);
    }

    //    สร้างหน้ากาก Fragment
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container, false);

        return view;
    }

}   // Main Class นี่คือ คลาสหลัก
