package com.niranisugar.android.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.niranisugar.android.Models.ResellSugarAvailable;
import com.niranisugar.android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mispc1 on 8/29/17.
 */

public class AdapterResellSugarAvailble extends BaseAdapter {

    private Context mContext;
    LayoutInflater inflater;
    SharedPreferences prefUserData;
    String USERID = "";
//    ApiInterface apiService;
    ArrayList<ResellSugarAvailable> arrResellSugarAvailable = new ArrayList<>();
//    KProgressHUD hud;
    boolean isOfficeTime;
//    DashboardActivity thisAct;
//    ResellSugarAvailableFragment fragment;
//    ResellSugarAvailbleActivity activity;

    public AdapterResellSugarAvailble(Context context, ArrayList<ResellSugarAvailable> arrResellSugarAvailable, String USERID, boolean isOfficeTime) {
        this.mContext = context;
        this.arrResellSugarAvailable = arrResellSugarAvailable;
        this.inflater = LayoutInflater.from(context);
        prefUserData = context.getSharedPreferences("USERDATA", MODE_PRIVATE);
        this.USERID = USERID;
        this.isOfficeTime = isOfficeTime;
//        thisAct = (DashboardActivity) context;
//        this.fragment = fragment;
//        this.activity = activity;
//        hud = KProgressHUD.create(context)
//                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setCancellable(false)
//                .setAnimationSpeed(2)
//                .setDimAmount(0.5f);
    }

    public int getCount() {
        return 10;
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0L;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = this.inflater.inflate(R.layout.raw_reseller_sugar_availble, (ViewGroup) null);
        }

//        final com.jkenterprises.jksugars.Adapter.AdapterResellSugarAvailble.Cell cell = com.jkenterprises.jksugars.Adapter.AdapterResellSugarAvailble.Cell.from(view);
//        final ResellSugarAvailable object = arrResellSugarAvailable.get(position);
//
//        if (object.getAuthorized().equals("Authortised")) {
//            cell.tvSendAuthorizeRequest.setVisibility(View.GONE);
//            cell.layoutQty.setVisibility(View.VISIBLE);
//        } else {
//            if (object.getAuthorized().equals("Waiting For Approval")) {
//                cell.tvSendAuthorizeRequest.setText("Pending Request");
////                cell.tvSendAuthorizeRequest.setEnabled(false);
//            } else {
//                cell.tvSendAuthorizeRequest.setEnabled(true);
//            }
//            cell.layoutQty.setVisibility(View.GONE);
//            cell.tvSendAuthorizeRequest.setVisibility(View.VISIBLE);
//        }
//        cell.tvSendAuthorizeRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (cell.tvSendAuthorizeRequest.getText().toString().trim().equals("Pending Request")) {
//                    setAlert("Your Trade Request to " + object.getAuthorizedName() +" " + object.getAuthorizedDistrict() + " has been Already sent. Please Wait for their Approval or You May Please Call To Mobile " + object.getAuthorizedContact() + ".", cell);
//                } else {
////                    if (isOfficeTime) {
//                    cell.tvSendAuthorizeRequest.setEnabled(false);
//                    hud.show();
//                    CallSendAuthorizedRequest(object.getClientID(), cell);
////                    } else {
////                        Toast.makeText(mContext, "eMarketplace timings are 10:00 AM to 10:00 PM", Toast.LENGTH_SHORT).show();
////                    }
//                }
//            }
//        });
//
////        if (!isOfficeTime) {
////            cell.edtQty.setEnabled(false);
////        } else {
//        cell.edtQty.setEnabled(true);
////        }
//
//        cell.btnDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (isOfficeTime) {
//                int EnteredQty = 1;
//                if (cell.edtQty.getText().toString().trim().isEmpty()) {
//                    Toast.makeText(mContext, "Please Enter Qty At least 1", Toast.LENGTH_SHORT).show();
//                } else {
//                        EnteredQty = Integer.parseInt(cell.edtQty.getText().toString().trim());
//                        int abcde = EnteredQty % 5;
//                        if ((abcde % 5) == 0) {
//                            String number  = object.getQtyRemain().replaceAll("[^0-9]", "");
//                            float AvailbleQty = Float.parseFloat(number);
//                            if (EnteredQty > AvailbleQty) {
//                                Toast.makeText(mContext, "This Item is not Available More than " + AvailbleQty, Toast.LENGTH_SHORT).show();
//                            } else {
//                                cell.btnDone.setEnabled(false);
//                                hud.show();
//                                CallBuyAPI(object, String.valueOf(EnteredQty), cell);
//                            }
//                        } else {
//                            Toast.makeText(mContext, "Please Enter Correct Quantity. Ex. 5,10,15...", Toast.LENGTH_SHORT).show();
//                        }
//                }
////                }
//            }
//        });
//        cell.LiftingDate.setText("P : " + object.getLiftingDate());
//        cell.PaymentDate.setText("L : " + object.getPaymentDate());
//        cell.MillFullNameZone.setText(object.getMillFullName() + ", " + object.getZone());
//        cell.TenderIDShortName.setText(object.getTenderId() + " - " + object.getMillShortName());
//        cell.GSP.setText(object.getGrade() + " - " + object.getSeason() + " - " + object.getPacking());
//        cell.Rate.setText(object.getRate() + "+" + object.getGST() + "(GST) = " + object.getNetRate());
//
//        Glide.with(mContext).load(object.getImage()).into(cell.SellerImage);


        return view;
    }

    public void AddItem(ResellSugarAvailable object) {

        arrResellSugarAvailable.add(object);
        notifyDataSetChanged();
    }

    static class Cell {
        public TextView TenderIDShortName, MillFullNameZone, GSP, Rate, PaymentDate, LiftingDate;
        ImageView SellerImage;
        TextView tvSendAuthorizeRequest, btnDone;
        EditText edtQty;
        LinearLayout layoutQty;

        Cell() {
        }

//        static com.jkenterprises.jksugars.Adapter.AdapterResellSugarAvailble.Cell from(View view) {
//            if (view == null) {
//                return null;
//            } else if (view.getTag() == null) {
//                com.jkenterprises.jksugars.Adapter.AdapterResellSugarAvailble.Cell cell = new com.jkenterprises.jksugars.Adapter.AdapterResellSugarAvailble.Cell();
//                cell.TenderIDShortName = (TextView) view.findViewById(R.id.TenderIdMillShortName);
//                cell.MillFullNameZone = (TextView) view.findViewById(R.id.MillFullNameZone);
//                cell.GSP = view.findViewById(R.id.GSP);
//                cell.Rate = view.findViewById(R.id.Amount);
//                cell.SellerImage = view.findViewById(R.id.imageSeller);
//                cell.tvSendAuthorizeRequest = view.findViewById(R.id.SendAuthorizeRequest);
//                cell.btnDone = view.findViewById(R.id.btnSend);
//                cell.edtQty = view.findViewById(R.id.edtQty);
//                cell.PaymentDate = view.findViewById(R.id.PaymentDate);
//                cell.LiftingDate = view.findViewById(R.id.LiftingDate);
//                cell.layoutQty = view.findViewById(R.id.LayoutQty);
//                view.setTag(cell);
//                return cell;
//            } else {
//                return (com.jkenterprises.jksugars.Adapter.AdapterResellSugarAvailble.Cell) view.getTag();
//            }
//        }
    }

//    public void CallSendAuthorizedRequest(String ClientID, final com.jkenterprises.jksugars.Adapter.AdapterResellSugarAvailble.Cell cell) {
//
//        final JsonObject jobjdata = new JsonObject();
//        jobjdata.addProperty("client_id", ClientID);
//        jobjdata.addProperty("user_id", USERID);
//
//        apiService = ApiClient.getClient().create(ApiInterface.class);
//        Call<String> callResellSugarAvailable = apiService.SendAuthorizedRequest(jobjdata.toString());
//        callResellSugarAvailable.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                // Toast.makeText(ds, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
//                if (response.code() == 200) {
//                    try {
//                        hud.dismiss();
//                        final JSONObject jsonObject = new JSONObject(response.body());
//                        setAlert(jsonObject.getString("msg"), cell);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }

//    public void CallBuyAPI(final ResellSugarAvailable Object, final String Qty, final com.jkenterprises.jksugars.Adapter.AdapterResellSugarAvailble.Cell cell) {
//
//        final JsonObject jobjdata = new JsonObject();
//        jobjdata.addProperty("client_id", Object.getClientID());
//        jobjdata.addProperty("user_id", USERID);
//        jobjdata.addProperty("tender_id", Object.getTenderId());
//        jobjdata.addProperty("tender_detail_id", Object.getTenderDetailID());
//        jobjdata.addProperty("tradecode", Object.getTenderNumber());
//        jobjdata.addProperty("qty", Qty);
//
//        apiService = ApiClient.getClient().create(ApiInterface.class);
//        Call<String> callResellSugarAvailable = apiService.BuyTender(jobjdata.toString());
//        callResellSugarAvailable.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                hud.dismiss();
//                // Toast.makeText(ds, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
//                if (response.code() == 200) {
//                    try {
//                        final JSONObject jsonObject = new JSONObject(response.body());
//                        Toast.makeText(mContext, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
//                        cell.btnDone.setEnabled(true);
//                        cell.edtQty.setText("");
//                        cell.edtQty.setHint(String.valueOf(Float.parseFloat(Object.getQtyRemain()) - Integer.parseInt(Qty)));
//                        if(fragment == null){
//                            activity.FromStartIn =0;
//                            activity.LastPosition = 20;
//                            activity.APICALLING = false;
//                            activity.callAPI(true,0);
//
//                        }else{
//                            fragment.FromStartIn =0;
//                            fragment.LastPosition = 20;
//                            fragment.APICALLING = false;
//                            fragment.callAPI(0);
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
//    }
//
//    public void setAlert(String Message, final com.jkenterprises.jksugars.Adapter.AdapterResellSugarAvailble.Cell cell) {
//
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
//// ...Irrelevant code for customizing the buttons and title
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        View dialogView = inflater.inflate(R.layout.activity_textview_dialog, null);
//        dialogBuilder.setView(dialogView);
//        final AlertDialog alertDialog = dialogBuilder.create();
//
//        final TextView etComments = (TextView) dialogView.findViewById(R.id.etComments);
//        final TextView tvAlert = (TextView) dialogView.findViewById(R.id.tvTitle);
//        tvAlert.setText("Authorised Trade Request");
//        etComments.setText(Message);
//        TextView btnOk = (TextView) dialogView.findViewById(R.id.btnOK);
//        btnOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        alertDialog.show();
//    }

}
